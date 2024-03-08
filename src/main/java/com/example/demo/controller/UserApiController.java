package com.example.demo.controller;

import com.example.demo.domain.dto.user.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    @GetMapping("/api/userinfo")
    public ResponseEntity<UserInfoResponse> getUserInfo(Principal principal) {

        String username = principal.getName();

        System.out.println(username);

        UserInfoResponse userInfoResponse = new UserInfoResponse(username);

        return ResponseEntity.ok()
                .body(userInfoResponse);
    }
}
