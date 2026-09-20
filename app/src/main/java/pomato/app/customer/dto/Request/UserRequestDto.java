package pomato.app.customer.dto.Request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserRequestDto {

    @NotBlank(message = "First name can't be blank.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    @Pattern(
            regexp = "^[a-zA-Z ]+$",
            message = "First name can contain only letters and spaces."
    )
    private String firstName;


    @NotBlank(message = "Last name can't be blank.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    @Pattern(
            regexp = "^[a-zA-Z ]+$",
            message = "Last name can contain only letters and spaces."
    )
    private String lastName;


    @NotBlank(message = "Email can't be blank.")
    @Email(message = "Please provide a valid email address.")
    private String email;


    @NotBlank(message = "Password can't be blank.")
    @Size(
            min = 8,
            max = 100,
            message = "Password must be between 8 and 100 characters."
    )
    private String password;


    @NotBlank(message = "Phone number can't be blank.")
    @Pattern(
            regexp = "^[6-9][0-9]{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number."
    )
    private String phone;


    @NotNull(message = "Date of birth can't be null.")
    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;

    public UserRequestDto(LocalDate dateOfBirth, String email, String firstName, String lastName, String password, String phone) {
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
    }

// getters and setters

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}