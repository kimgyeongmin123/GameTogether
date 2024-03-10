package com.example.demo.controller;

import com.example.demo.domain.dto.article.ArticleViewResponse;
import com.example.demo.domain.dto.chat.AddChatRoomRequest;
import com.example.demo.domain.dto.chat.ChatRoomResponse;
import com.example.demo.domain.dto.chat.ChatRoomViewResponse;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.ChatService;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ChatApiController {

    @Autowired
    private final ChatService chatService;

    @PostMapping("/api/ChatRoom")
    public ResponseEntity<ChatRoom> addChatRoom(@RequestBody AddChatRoomRequest request, Principal principal) {

        System.out.println("채팅룸 컨트롤러 : " + request.getAuthor() + request.getSelectedGame());

        if(Objects.equals(request.getAuthor(), principal.getName())){
            System.out.println("작성자와 현재 로그인한 유저가 동일하다.");
            return ResponseEntity.badRequest().body(null);
        }

        //방번호, 본인 닉네임으로 방이 존재하는지 확인(true/false)
        boolean hasChatRoom = chatService.hasChatRoom(request, principal.getName());
        System.out.println("방 존재 확인 : " + hasChatRoom);

        if(hasChatRoom){
            //방이 이미 존재한다면 채팅방 객체 찾아서 반환
            ChatRoom chatRoom = chatService.findByIdAndUsername(request, principal.getName());
            System.out.println("chatRoom : " + chatRoom);
            return ResponseEntity.ok()
                    .body(chatRoom);
        }else{
            //방이 존재하지 않는다면 방 생성
            ChatRoom savedChatRoom = chatService.saveRoom(request, principal.getName());
            System.out.println("chatRoom : " + savedChatRoom);
            //채팅방 Entity 반환
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedChatRoom);
        }
    }

    @GetMapping("/api/ChatRoom")
    public ResponseEntity<List<ChatRoomViewResponse>> myArticle(Principal principal){
        System.out.println("내 채팅방 조회 컨트롤러 누구? : " + principal.getName());
        List<ChatRoomViewResponse> myChatRoom = chatService.findByUserIdInChat(principal.getName())
                .stream()
                .map(ChatRoomViewResponse::new)
                .toList();

        System.out.println("찾은 채팅방 : " + myChatRoom);

        return ResponseEntity.ok()
                .body(myChatRoom);
    }


}
