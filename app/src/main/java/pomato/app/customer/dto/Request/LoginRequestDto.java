package pomato.app.customer.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDto {
    @Email
    @NotBlank( message = "email cannot be blank!")
    private String email;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, message = "password must contain atleast 8 characters")
    private String password;

    public LoginRequestDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
