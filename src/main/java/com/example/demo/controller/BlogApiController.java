package com.example.demo.controller;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.article.ArticleResponse;
import com.example.demo.domain.dto.article.UpdateArticleRequest;
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
public class BlogApiController {

    private final BlogService blogService;

//    글 작성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal){
        System.out.println("글등록 컨트롤러 제목 : " + request.getTitle());
        System.out.println("userName : " + principal.getName());
        Article savedArticle = blogService.save(request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

//    글 전체 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findArticles(){
        System.out.println("글전체조회 컨트롤러");
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
        System.out.println("글하나조회 컨트롤러"+id);
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

//    글 수정 (기능 삭제)
//    @PutMapping("/api/articles/{id}")
//    public ResponseEntity<Article> updateArticle(@PathVariable long id,
//                                                 @RequestBody UpdateArticleRequest request){
//        Article updatedArticle = blogService.update(id, request);
//
//        return ResponseEntity.ok()
//                .body(updatedArticle);
//    }
}
