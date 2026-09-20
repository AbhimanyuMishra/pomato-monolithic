package pomato.app.customer.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pomato.app.customer.Entity.UserRoles;
import pomato.app.customer.Entity.Users;
import pomato.app.customer.dto.Request.LoginRequestDto;
import pomato.app.customer.dto.Request.UserRequestDto;
import pomato.app.customer.dto.Response.LoginResponseDto;
import pomato.app.customer.dto.Response.UserResponseDto;
import pomato.app.customer.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/users/sign-up")
    public ResponseEntity<UserResponseDto> UserRegistration(@Valid @RequestBody UserRequestDto request){
        Users user= new Users();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhone());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setRole(UserRoles.Customer);

        Users saveduser=userService.createUser(user);

        UserResponseDto response = new UserResponseDto();
        response.setUserId(saveduser.getUserId());
        response.setUserFirstName(saveduser.getFirstName());
        response.setUserLastName(saveduser.getLastName());
        response.setUserEmail(saveduser.getEmail());
        response.setMessage("User created successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

    @PostMapping("users/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request){
        LoginResponseDto response = userService.login(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }
}
