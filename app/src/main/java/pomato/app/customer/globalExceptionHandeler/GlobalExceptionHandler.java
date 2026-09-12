package pomato.app.customer.globalExceptionHandeler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pomato.app.customer.dto.Response.ErrorResponseDto;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto>  globalExceptionHandeler(MethodArgumentNotValidException e){

        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach((error)->errors.put(error.getField(),error.getDefaultMessage()));


        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setStatus("400");
        errorResponseDto.setMessage("Validation Failed");
        errorResponseDto.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}
