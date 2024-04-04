package com.example.demo.controller;

import com.example.demo.domain.dto.user.AddUserRequest;
import com.example.demo.domain.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){

        Cookie myCookie = new Cookie("refresh_token", null);
        myCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0
        myCookie.setPath("/"); // 모든 경로에서 삭제
        response.addCookie(myCookie);

        return "redirect:/login";
    }

//    회원가입 시 이메일 중복체크
    @PostMapping("/emailExist")
    @ResponseBody
    public Map<String, String> emailExist(@RequestBody Map<String, String> emailMap) {

        Map<String, String> result = new HashMap<>();
        String email = emailMap.get("email");
        if (userService.emailExist(email)) {
            result.put("message", "이미 사용중인 아이디입니다.");
            result.put("status", "fail");
        } else {
            result.put("message", "사용 가능한 아이디입니다.");
            result.put("status", "success");
        }
        return result;
    }

    //    회원가입 시 닉네임 중복체크
    @PostMapping("/nicknameExist")
    @ResponseBody
    public Map<String, String> nicknameExist(@RequestBody Map<String, String> nicknameMap) {

        Map<String, String> result = new HashMap<>();
        String nickname = nicknameMap.get("nickname");
        if (userService.nicknameExist(nickname)) {
            result.put("message", "이미 사용중인 닉네임입니다.");
            result.put("status", "fail");
        } else {
            result.put("message", "사용 가능한 닉네임입니다.");
            result.put("status", "success");
        }
        return result;
    }
}
