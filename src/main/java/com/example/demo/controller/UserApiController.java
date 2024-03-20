package com.example.demo.controller;

import com.example.demo.domain.dto.user.UpdateUserRequest;
import com.example.demo.domain.dto.user.UserInfoResponse;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

//    현재 로그인된 사용자 정보 반환
    @GetMapping("/api/userinfo")
    public ResponseEntity<UserInfoResponse> getUserInfo(Principal principal) {

        String username = principal.getName();

        System.out.println(username);

        User user = userService.findByEmail(username);
        UserInfoResponse userInfoResponse = new UserInfoResponse(username, user.getNickname(), user.getAgeGroup());

        return ResponseEntity.ok()
                .body(userInfoResponse);
    }

//        사용자 정보 추가(수정)
    @PutMapping("/api/userinfo")
    public ResponseEntity<User> updateArticle(@RequestBody UpdateUserRequest request, Principal principal){

        System.out.println("사용자 정보를 추가하는 컨트롤러");
        String username = principal.getName();
        User user = userService.findByEmail(username);
        User UpdateUser = userService.update(user, request);

        return ResponseEntity.ok()
                .body(UpdateUser);
    }

}
