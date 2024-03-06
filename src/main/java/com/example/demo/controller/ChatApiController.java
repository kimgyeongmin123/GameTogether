package com.example.demo.controller;

import com.example.demo.domain.dto.chat.AddChatRoomRequest;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.ChatService;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/api/createChatRoom")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody AddChatRoomRequest request, Principal principal) {

        // 현재 로그인한 사용자의 ID를 가져옵니다.
        String userName = principal.getName();
        User user = userService.findByEmail(userName);
        System.out.println("userNickname : " + user.getNickname());

        // 요청으로부터 게시물 ID와 작성자를 가져옵니다.
        String author = request.getAuthor();
        String selectedGame = request.getSelectedGame();

        // 채팅방 생성을 시도합니다.
        ChatRoom savedChatRoom = chatService.createChatRoom(request, user.getNickname());

        // 생성된 채팅방의 ID를 클라이언트에 반환합니다.
        return ResponseEntity.ok(new CreateChatRoomResponse(savedChatRoom));
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
