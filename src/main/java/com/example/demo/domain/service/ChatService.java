package com.example.demo.domain.service;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.chat.AddChatRoomRequest;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(AddChatRoomRequest request, String userNickname){
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + userName));
        return blogRepository.save(request.toEntity(user.getNickname()));
    }
}
