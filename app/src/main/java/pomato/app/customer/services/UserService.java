package pomato.app.customer.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pomato.app.customer.Entity.UserRoles;
import pomato.app.customer.Entity.Users;
import pomato.app.customer.dto.Request.LoginRequestDto;
import pomato.app.customer.dto.Response.LoginResponseDto;
import pomato.app.customer.repository.UserRepository;
import pomato.app.security.JwtService;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }
    public Users createUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.Customer);
        return userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto request){

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
                );


        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();


        String token =
                jwtService.generateToken(userDetails);




        Users user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        return new LoginResponseDto(
                "Login successful",
                user.getEmail(),
                user.getRole().name(),
                user.getUserId(),
                token
        );
    }

}
