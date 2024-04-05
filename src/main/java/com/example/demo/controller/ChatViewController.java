package com.example.demo.controller;

import com.example.demo.domain.dto.article.ArticleViewResponse;
import com.example.demo.domain.dto.chat.ChatRoomViewResponse;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.Chat;
import com.example.demo.domain.entity.ChatRoom;
import com.example.demo.domain.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatViewController {

    @Autowired
    private final ChatService chatService;

    @GetMapping("/chatRoom/{id}")
    public String chatGET(@PathVariable long id, Model model){

        ChatRoom chatRoom = chatService.findById(id);

        model.addAttribute("chatRoom", new ChatRoomViewResponse(chatRoom));

        List<Chat> chatList = chatService.findChatById(id);
        System.out.println(id + "번 방에서 찾은 대화 내역 : " + chatList);

        model.addAttribute("chatList", chatList);

        return "chater";
    }
}