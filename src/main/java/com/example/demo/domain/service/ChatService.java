package com.example.demo.domain.service;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.chat.AddChatRoomRequest;
import com.example.demo.domain.dto.chat.ChatMessage;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.Chat;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.ChatRepository;
import com.example.demo.domain.repository.ChatRoomRepository;
import com.example.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    public ChatRoom saveRoom(AddChatRoomRequest request, String userName){
        return chatRoomRepository.save(request.toEntity(userName));
    }

    //채팅방이 존재하는지 확인
    public boolean hasChatRoom(AddChatRoomRequest request, String userName){
        return chatRoomRepository.existsByArticleIdAndUserName(request.getArticleId(), userName);
    }

//  게시글ID 와 userName 으로 채팅방 객체 반환
    public ChatRoom findByIdAndUsername(AddChatRoomRequest request, String userName){
        return chatRoomRepository.findByArticleIdAndUserName(request.getArticleId(), userName);
    }

    public ChatRoom findById(long id){
        System.out.println("채팅룸 찾기 서비스 : " + id);
        return chatRoomRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

    //메시지저장
    public Chat saveChat(Long roomId, ChatMessage chatmessage){
        return chatRepository.save(chatmessage.toEntity(roomId));
    }
}
