package com.example.demo.controller;

import com.example.demo.domain.dto.chat.ChatMessage;
import com.example.demo.domain.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

//    private final NotificationService notificationService;
//
//    @MessageMapping("/{userId}")
//    @SendTo("/notification/{userId}")
//    public ChatMessage chat(@DestinationVariable String userId, ChatMessage chatmessage){
//
//        Chat chat = chatService.saveChat(roomId, chatmessage);
//        return ChatMessage.builder()
//                .roomId(roomId)
//                .sender(chat.getSender())
//                .message(chat.getMessage())
//                .build();
//    }
}
