package com.example.demo.domain.dto.user;

import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String username;
    private String nickname;
    private String agegroup;


    public UserInfoResponse(String username, String nickname, String agegroup) {

        this.username = username;
        this.nickname = nickname;
        this.agegroup = agegroup;
    }
}
