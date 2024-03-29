package com.example.demo.domain.entity;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "articleId", nullable = false)
    private Long articleId;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "authorNickname", nullable = false)
    private String authorNickname;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "selectedGame", nullable = false)
    private String selectedGame;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Builder    /*빌더 패턴*/
    public ChatRoom(Long articleId, String author, String authorNickname, String userName, String selectedGame, String nickname){
        this.articleId = articleId;
        this.author = author;
        this.authorNickname = authorNickname;
        this.userName = userName;
        this.selectedGame = selectedGame;
        this.nickname = nickname;
    }

}
