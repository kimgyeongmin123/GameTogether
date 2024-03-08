package com.example.demo.controller;

import com.example.demo.domain.dto.article.ArticleViewResponse;
import com.example.demo.domain.dto.chat.ChatRoomViewResponse;
import com.example.demo.domain.entity.Article;
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

@Controller
@RequiredArgsConstructor
public class ChatViewController {

    @Autowired
    private final ChatService chatService;

    @GetMapping("/chatRoom/{id}")
    public String chatGET(@PathVariable long id, Model model, Principal principal){
        System.out.println("@ChatController, chat GET() : " + id);

        ChatRoom chatRoom = chatService.findById(id);
        System.out.println("채팅룸 불러오는 컨트롤러에서.. 채팅룸 정보 : " + chatRoom);
        model.addAttribute("chatRoom", new ChatRoomViewResponse(chatRoom, null));


        return "chater";
    }
}