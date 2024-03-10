package com.example.demo.domain.dto.chat;

import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.Chat;
import com.example.demo.domain.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessage {

    private Long roomId;
    private String sender;
    private String receiver;
    private String message;


    public Chat toEntity(Long roomId){
        return Chat.builder()
                .roomId(roomId)
                .sender(sender)
                .receiver(receiver)
                .message(message)
                .build();
    }

    @Builder
    public ChatMessage(Long roomId, String sender, String receiver, String message){
        this.roomId = roomId;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }


}
