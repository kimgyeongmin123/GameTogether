package com.example.demo.controller;

import com.example.demo.domain.dto.user.WebLoginRequest;
import com.example.demo.domain.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid WebLoginRequest request){

        String token = loginService.login(request);

        String refreshToken = loginService.makeRefreshToken(request);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
