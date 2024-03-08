package com.example.demo.controller;

import com.example.demo.domain.dto.chat.ChatMessage;
import com.example.demo.domain.entity.Chat;
import com.example.demo.domain.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/{roomId}")
    @SendTo("/room/{roomId}")
    public ChatMessage chat(@DestinationVariable Long roomId, ChatMessage chatmessage){

        Chat chat = chatService.saveChat(roomId, chatmessage);
        return ChatMessage.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message(chat.getMessage())
                .build();
    }
}
