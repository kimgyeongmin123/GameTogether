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
}
