package pomato.app.customer.globalExceptionHandeler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pomato.app.customer.dto.Response.ErrorResponseDto;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> globalExceptionHandler(
            MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setStatus("400");
        errorResponseDto.setMessage("Validation Failed");
        errorResponseDto.setErrors(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }


    // Wrong password
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentials(
            BadCredentialsException ex) {

        ErrorResponseDto responseDto = new ErrorResponseDto();

        responseDto.setStatus("401");
        responseDto.setMessage("Invalid email or password");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(responseDto);
    }


    // User/email not found
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(
            UsernameNotFoundException ex) {

        ErrorResponseDto responseDto = new ErrorResponseDto();

        responseDto.setStatus("401");
        responseDto.setMessage("Invalid email or password");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(responseDto);
    }
}