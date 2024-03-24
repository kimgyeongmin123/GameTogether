package com.example.demo.domain.service;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.article.UpdateArticleRequest;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.BlogRepository;
import com.example.demo.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    private final UserRepository userRepository;

//    블로그 글 추가
    public Article save(AddArticleRequest request, String userName){

        Optional<User> user = userRepository.findByEmail(userName);
        return blogRepository.save(request.toEntity(userName, user.map(User::getNickname).orElse("Default Nickname")));
    }

//    글 전체 조회
    public List<Article> findAll(){
        List<Article> articles = blogRepository.findAll();
        Collections.reverse(articles);
        return articles;
    }

//    글 하나 조회
    public Article findById(long id){
        System.out.println("글하나조회 서비스"+id);
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

//    글 게임별 조회
    public List<Article> findByGame(String selectedGame){
        List<Article> articles = blogRepository.findBySelectedGame(selectedGame);
        Collections.reverse(articles);
        return articles;
    }

//    글 삭제
    public void delete(long id){

        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

//    글 수정
//    @Transactional
//    public Article update(long id, UpdateArticleRequest request){
//        Article article = blogRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
//
//        authorizeArticleAuthor(article);
//        article.update(request.getTitle(), request.getContent());
//
//        return article;
//    }

//    본인이 작성했는지 확인
    private static void authorizeArticleAuthor(Article article){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("userName, article.getAuthor()" + userName + article.getAuthor());

        if(!article.getAuthor().equals(userName)){
            throw new IllegalArgumentException("not authorized");
        }
    }

    public List<Article> findByAuthor(String author){

        List<Article> articles = blogRepository.findByAuthor(author);
        Collections.reverse(articles);
        return articles;
    }
}
