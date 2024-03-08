package com.example.demo.domain.dto.user;

import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String username;


    public UserInfoResponse(String username) {
        this.username = username;
    }
}
