package com.flight.dto;

import lombok.Data;

@Data
public class UserInfoResponse {
    private String username;
    private String email;
    private Integer membership;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String username, String email, Integer membership) {
        this.username = username;
        this.email = email;
        this.membership = membership;
    }
}
