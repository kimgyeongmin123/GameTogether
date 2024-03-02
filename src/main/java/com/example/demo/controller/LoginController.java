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
import java.util.Map;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid WebLoginRequest request){
        System.out.println("로그인 컨드롤러 로그인 : " + request.getEmail());
        String token = loginService.login(request);
        System.out.println("토큰 : " + token);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid WebLoginRequest request) {
//        System.out.println("로그인 컨트롤러 : " + request.getEmail());
//        String token = loginService.login(request);
//        System.out.println("토큰 : " + token);
//
//        // 응답 데이터에 토큰을 포함하여 반환
//        Map<String, String> response = new HashMap<>();
//        response.put("access_Token", token);
//
//        return ResponseEntity.ok(response);
//    }
}
