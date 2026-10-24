package pomato.app.customer.globalExceptionHandeler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pomato.app.customer.dto.Response.ErrorResponseDto;
import pomato.app.customer.exception.EmailAlreadyExistsException;
import pomato.app.customer.exception.PhoneAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.putIfAbsent(error.getField(), error.getDefaultMessage())
        );

        ErrorResponseDto response = new ErrorResponseDto();
        response.setStatus("400");
        response.setMessage("Please correct the highlighted fields.");
        response.setErrors(errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentials(BadCredentialsException exception) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setStatus("401");
        response.setMessage("Invalid email or password.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UsernameNotFoundException exception) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setStatus("401");
        response.setMessage("Invalid email or password.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExists(EmailAlreadyExistsException exception) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setStatus("409");
        response.setMessage(exception.getMessage());
        response.setErrors(Map.of("email", exception.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlePhoneAlreadyExists(PhoneAlreadyExistsException exception) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setStatus("409");
        response.setMessage(exception.getMessage());
        response.setErrors(Map.of("phone", exception.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        ErrorResponseDto response = new ErrorResponseDto();
        response.setStatus("409");
        response.setMessage("A record with these details already exists. Check the email and phone number.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
