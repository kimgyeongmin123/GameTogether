package com.example.demo.domain.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.domain.dto.user.WebLoginRequest;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenProvider tokenProvider;

    @Transactional
    public String login(WebLoginRequest request) {
        System.out.println("로그인 서비스 email : " + request.getEmail());
        //ID 체크
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + request.getEmail()));
        //PW 체크
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("not found password");
        }

        //로그인 성공시 jwt 토큰 생성

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
