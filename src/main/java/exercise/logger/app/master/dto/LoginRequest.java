package exercise.logger.app.master.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;


public class LoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Constructors
    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}