package com.example.demo.domain.dto.chat;

import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import lombok.Getter;

@Getter
public class ChatRoomViewResponse {

    private Long id;
    private Long articleId;
    private String author;
    private String selectedGame;
    private String userName;

    public ChatRoomViewResponse(ChatRoom chatRoom){
        this.id = chatRoom.getId();
        this.articleId = chatRoom.getArticleId();
        this.author = chatRoom.getAuthor();
        this.selectedGame = chatRoom.getSelectedGame();
        this.userName = chatRoom.getUserName();
    }
}
