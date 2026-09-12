package pomato.app.customer.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CustomerRequestDto {
    @NotEmpty (message = "Name cannot be blank")
    private String name;

    @Email (message = "Invalid Email")
    @NotEmpty(message= "Email cannot be blank")
    private String email;
    @NotEmpty (message = "Password is required")
    @Size(min =8,message = "Password must contain at least 8 characters")
    private String password;
    @Positive(message = "age must be greater than 0")
    private int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
