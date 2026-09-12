package pomato.app.customer.dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

@JsonPropertyOrder({
        "status",
        "message",
        "errors"
})
public class ErrorResponseDto {

    private String status;
    private String message;
    private Map<String, String> errors;

    public ErrorResponseDto() {};

    public ErrorResponseDto(Map<String, String> errors, String status, String message) {
        this.errors = errors;
        this.status = status;
        this.message = message;

    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
