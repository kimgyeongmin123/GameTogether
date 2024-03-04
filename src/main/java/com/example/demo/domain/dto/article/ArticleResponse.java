package com.example.demo.domain.dto.article;

import com.example.demo.domain.entity.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {

    private final String selectedGame;
    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.selectedGame = article.getSelectedGame();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
