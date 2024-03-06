package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*자동으로 1씩 증가*/
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "selectedGame", nullable = false)
    private String selectedGame;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author", nullable = false)
    private String author;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Builder    /*빌더 패턴*/
    public Article(String author, String selectedGame, String title, String content){
        this.author = author;
        this.selectedGame = selectedGame;
        this.title = title;
        this.content = content;
    }

//    public void update(String title, String content){
//        this.title = title;
//        this.content = content;
//    }
}
