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
    public Map<String, String> checkUserId(@RequestBody Map<String, String> emailMap) {
        System.out.println("이메일 중복체크를 하는 컨트롤러");
        Map<String, String> result = new HashMap<>();
        String email = emailMap.get("email");
        System.out.println("정보 result : "+result+" / email : "+email);
        if (userService.emailExist(email)) {
            result.put("message", "이미 사용중인 아이디입니다.");
            result.put("status", "fail");
        } else {
            result.put("message", "사용 가능한 아이디입니다.");
            result.put("status", "success");
        }
        return result;
    }
}
