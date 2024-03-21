package com.example.demo.config.oauth;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    private void saveOrUpdate(OAuth2User oAuth2User){

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        User user = userRepository.findByEmail(email)
                .orElse(User.builder()
                        .email(email)
                        .build());

        userRepository.save(user);
    }
}
