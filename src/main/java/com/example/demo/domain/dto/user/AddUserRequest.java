package com.example.demo.domain.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;
    private String ageGroup;
}
