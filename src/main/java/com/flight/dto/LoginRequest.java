package com.flight.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String email;

    // Temporary manual getter until Lombok is properly configured
    public String getUsername() {
        return username;
    }

    // Temporary manual getter until Lombok is properly configured
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
