package pomato.app.customer.dto.Response;

public class UserResponseDto {
    Long UserId;
    String userFirstName;
    String userLastName;
    String UserEmail;
    String message;

    public UserResponseDto() {}

    public UserResponseDto(Long userId, String userEmail, String userFirstName, String userLastName, String message) {
        this.UserId = userId;
        this.UserEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
