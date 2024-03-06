package com.example.demo.controller;

import com.example.demo.domain.dto.chat.AddChatRoomRequest;
import com.example.demo.domain.dto.chat.ChatRoomResponse;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.ChatService;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ChatApiController {

    @Autowired
    private final ChatService chatService;

    @Autowired
    private final UserService userService;

    @PostMapping("/api/ChatRoom")
    public ResponseEntity<ChatRoom> addChatRoom(@RequestBody AddChatRoomRequest request, Principal principal) {

        System.out.println("채팅룸 생성 컨트롤러 : " + request.getAuthor() + request.getSelectedGame());
        String author = request.getAuthor();
        String selectedGame = request.getSelectedGame();

        ChatRoom savedChatRoom = chatService.save(request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedChatRoom);
    }

    static class ChatRequest {
        private Long articleId;
        private String articleAuthor;

        // getters and setters
    }

    static class CreateChatRoomResponse {
        private Long chatRoomId;

        public CreateChatRoomResponse(Long chatRoomId) {
            this.chatRoomId = chatRoomId;
        }

        // getters and setters
    }

}
