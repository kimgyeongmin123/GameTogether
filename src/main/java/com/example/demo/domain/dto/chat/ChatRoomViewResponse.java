package com.example.demo.domain.dto.chat;

import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import lombok.Getter;

@Getter
public class ChatRoomViewResponse {

    private Long id;
    private Long articleId;
    private String author;
    private String authorNickname;
    private String selectedGame;
    private String username; //대화신청자
    private String nickname;

    public ChatRoomViewResponse(ChatRoom chatRoom){
        this.id = chatRoom.getId();
        this.articleId = chatRoom.getArticleId();
        this.author = chatRoom.getAuthor();
        this.authorNickname = chatRoom.getAuthorNickname();
        this.selectedGame = chatRoom.getSelectedGame();
        this.username = chatRoom.getUserName();
        this.nickname = chatRoom.getNickname();
    }
}
