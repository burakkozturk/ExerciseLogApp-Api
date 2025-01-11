package exercise.logger.app.master.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

    // Getters and Setters
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Constructors
    public TokenRefreshRequest() {
    }

    public TokenRefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}