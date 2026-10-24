package pomato.app.customer.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pomato.app.customer.Entity.UserRoles;

public class LoginRequestDto {
    @Email
    @NotBlank(message = "Email cannot be blank.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    private String password;

    @NotNull(message = "Choose the portal you want to log in to.")
    private UserRoles role;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public UserRoles getRole() { return role; }
    public void setRole(UserRoles role) { this.role = role; }
}
