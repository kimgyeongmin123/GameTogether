package com.example.demo.domain.dto.chat;

import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddChatRoomRequest {

    private String author;
    private String selectedGame;

    //    DTO를 엔티티로
    public ChatRoom toEntity(String currentUser){
        return ChatRoom.builder()
                .selectedGame(selectedGame)
                .currentUser(currentUser)
                .build();
    }
}
