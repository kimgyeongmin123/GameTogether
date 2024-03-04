package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "ageGroup", nullable = false)
    private String ageGroup;

    @Builder
    public User(String email, String password, String nickname, String ageGroup){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.ageGroup = ageGroup;
    }

//    사용자 이름 변경
    public User update(String nickname){
        this.nickname = nickname;

        return this;
    }

    @Override   //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){

        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override   //id 반환
    public String getUsername(){
        return email;
    }

    @Override   //password 반환
    public String getPassword(){
        return password;
    }

    @Override   //계정 만료 여부 반환
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override   //계정 잠금 여부 반환
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override   //패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override   //계정 사용 가능 여부 반환
    public boolean isEnabled(){
        return true;
    }
}
