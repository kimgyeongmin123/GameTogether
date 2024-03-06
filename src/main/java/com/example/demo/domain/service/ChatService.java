package com.example.demo.domain.service;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.chat.AddChatRoomRequest;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.ChatRoomRepository;
import com.example.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final UserRepository userRepository;

    public ChatRoom save(AddChatRoomRequest request, String userName){
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + userName));
        return chatRoomRepository.save(request.toEntity(user.getNickname()));
    }
}
