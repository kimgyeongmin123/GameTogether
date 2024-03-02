package com.example.demo.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebLoginRequest {
    private String email;
    private String password;
}
