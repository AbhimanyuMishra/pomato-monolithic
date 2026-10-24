package pomato.app.customer.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pomato.app.customer.Entity.UserRoles;
import pomato.app.customer.Entity.Users;
import pomato.app.customer.dto.Request.LoginRequestDto;
import pomato.app.customer.dto.Response.LoginResponseDto;
import pomato.app.customer.exception.EmailAlreadyExistsException;
import pomato.app.customer.exception.PhoneAlreadyExistsException;
import pomato.app.customer.repository.UserRepository;
import pomato.app.security.JwtService;

import java.util.Locale;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Users createUser(Users user, UserRoles requestedRole) {
        validatePublicSignupRole(requestedRole);
        user.setEmail(user.getEmail().trim().toLowerCase(Locale.ROOT));

        var existingAccount = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (existingAccount.isPresent()) {
            Users account = existingAccount.get();
            if (!passwordEncoder.matches(user.getPassword(), account.getPassword())) {
                throw new BadCredentialsException("Sign in with the existing account password to add a portal.");
            }
            if (account.getRoles().contains(requestedRole)) {
                throw new EmailAlreadyExistsException("This account already has this portal role.");
            }
            account.addRole(requestedRole);
            return userRepository.save(account);
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new PhoneAlreadyExistsException("This phone number is already associated with an account.");
        }
        user.addRole(requestedRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void validatePublicSignupRole(UserRoles role) {
        if (role != UserRoles.Customer
                && role != UserRoles.Restaurant
                && role != UserRoles.DeliveryBoy) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Public signup supports customer, restaurant, and delivery partner roles only."
            );
        }
    }

    public LoginResponseDto login(LoginRequestDto request) {
        request.setEmail(request.getEmail().trim().toLowerCase(Locale.ROOT));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users user = userRepository.findByEmailIgnoreCase(request.getEmail()).orElseThrow();
        if (!user.getRoles().contains(request.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This account does not have access to the selected portal."
            );
        }

        String token = jwtService.generateToken(userDetails);
        return new LoginResponseDto(
                user.getEmail(),
                "Login successful",
                request.getRole().name(),
                user.getUserId(),
                token,
                user.getRoles().stream().map(Enum::name).sorted().toList()
        );
    }
}
