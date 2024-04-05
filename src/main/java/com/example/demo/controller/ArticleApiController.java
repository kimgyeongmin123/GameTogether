package com.example.demo.controller;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.article.ArticleResponse;
import com.example.demo.domain.dto.article.ArticleViewResponse;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final BlogService blogService;

//    글 작성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal){

        Article savedArticle = blogService.save(request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

//    글 전체 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findArticles(){

        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

//    글 하나 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id){

        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

//    글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

//    글 수정 (수정 기능 삭제)
//    @PutMapping("/api/articles/{id}")
//    public ResponseEntity<Article> updateArticle(@PathVariable long id,
//                                                 @RequestBody UpdateArticleRequest request){
//        Article updatedArticle = blogService.update(id, request);
//
//        return ResponseEntity.ok()
//                .body(updatedArticle);
//    }

    @GetMapping("/api/myArticle")
    public ResponseEntity<List<ArticleViewResponse>> myArticle(Principal principal){

        List<ArticleViewResponse> myArticles = blogService.findByAuthor(principal.getName())
                .stream()
                .map(ArticleViewResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(myArticles);
    }
}
