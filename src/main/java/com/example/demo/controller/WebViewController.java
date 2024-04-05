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

        List<ArticleListViewResponse> articles = blogService.findByGame(selectedGame).stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);
        model.addAttribute("selectedGame", selectedGame);
        return "articleListByGame";
    }

    //    글 검색 조회
    @GetMapping("/articles/search/{input}")
    public String getArticleBySearch(@PathVariable String input, Model model){

        List<ArticleListViewResponse> articlesByTitle = blogService.findByTitle(input).stream()
                .map(ArticleListViewResponse::new)
                .toList();

        List<ArticleListViewResponse> articlesByContent = blogService.findByContent(input).stream()
                .map(ArticleListViewResponse::new)
                .toList();

        List<ArticleListViewResponse> articlesByNickname = blogService.findByNickname(input).stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articlesByTitle", articlesByTitle);
        model.addAttribute("articlesByContent", articlesByContent);
        model.addAttribute("articlesByNickname", articlesByNickname);
        model.addAttribute("input", input);

        return "articleListBySearch";
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
