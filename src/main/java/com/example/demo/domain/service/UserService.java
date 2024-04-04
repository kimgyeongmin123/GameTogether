package com.example.demo.domain.service;

import com.example.demo.domain.dto.user.AddUserRequest;
import com.example.demo.domain.dto.user.UpdateUserRequest;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .ageGroup(dto.getAgeGroup())
                .build()).getId();
    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    @Transactional
    public User update(User user, UpdateUserRequest request){

        user.update_nickname(request.getNickname());
        user.update_agegroup(request.getAgegroup());

        return user;
    }

    //이메일중복조회
    public boolean emailExist(String email) {
        System.out.println("들어와버린 이메일 중복 조회 서비스");
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println("찾은 유저 정보 : "+user+"존재 유무"+user.isPresent());
        return user.isPresent();
    }
}
