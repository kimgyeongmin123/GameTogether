package com.example.demo.domain.service;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.example.demo.config.oauth.OAuth2SuccessHandler;
import com.example.demo.domain.dto.user.WebLoginRequest;
import com.example.demo.domain.entity.RefreshToken;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.RefreshTokenRepository;
import com.example.demo.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenProvider tokenProvider;

    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserService userService;


    @Transactional
    public String login(WebLoginRequest request) {
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

    public String makeRefreshToken(WebLoginRequest request){
        //ID 체크
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + request.getEmail()));

//        리프레시 토큰 생성
        String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(14));
        System.out.println("refreshToken : " + refreshToken);
//        데이터베이스에 저장
        RefreshToken DBrefreshToken = refreshTokenRepository.findByUserId(user.getId())
                .map(entity -> entity.update(refreshToken))
                .orElse(new RefreshToken(user.getId(), refreshToken));
        refreshTokenRepository.save(DBrefreshToken);
//        쿠키에 저장


        return refreshToken;
    }

}
