package pomato.app.customer.dto.Response;

public class LoginResponseDto {
   private Long userid;
    private String message;
    private String role;
    private String email;
    private String token;


    public LoginResponseDto(String email, String message, String role, Long userid,String token) {
        this.email = email;
        this.message = message;
        this.role = role;
        this.userid = userid;
        this.token = token;
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
