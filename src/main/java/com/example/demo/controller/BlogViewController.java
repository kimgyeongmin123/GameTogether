package com.example.demo.controller;

import com.example.demo.domain.dto.article.ArticleListViewResponse;
import com.example.demo.domain.dto.article.ArticleViewResponse;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.BlogService;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    private final UserService userService;

//     글 전체목록
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);

        return "articleList";
    }

//    글 상세조회
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable long id, Model model){

        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

//    생성/수정 화면
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){

        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        } else{
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
