package com.example.demo.controller;

import com.example.demo.domain.dto.article.ArticleListViewResponse;
import com.example.demo.domain.dto.article.ArticleViewResponse;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.service.BlogService;
import com.example.demo.domain.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class WebViewController {

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

//    글 게임별 조회
    @GetMapping("/articles/list/{selectedGame}")
    public String getArticleByGame(@PathVariable String selectedGame, Model model){

        System.out.println("게임별 조회 게임 : " + selectedGame);

        List<ArticleListViewResponse> articles = blogService.findByGame(selectedGame).stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);
        model.addAttribute("selectedGame", selectedGame);
        return "articleListByGame";
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

    @GetMapping("/my-article")
    public String myArticle(){
        return "myArticle";
    }

    @GetMapping("/chat-room")
    public String chatRoom(){
        return "chatRoom";
    }

    @GetMapping("/additional_info")
    public String additional_info(){ return "additional_info";}
}
