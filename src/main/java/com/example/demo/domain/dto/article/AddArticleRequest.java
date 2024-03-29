package com.example.demo.domain.dto.article;

import com.example.demo.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String selectedGame;
    private String title;
    private String content;

    //    DTO를 엔티티로
    public Article toEntity(String author, String nickname){
        return Article.builder()
                .selectedGame(selectedGame)
                .title(title)
                .content(content)
                .author(author)
                .nickname(nickname)
                .build();
    }
}