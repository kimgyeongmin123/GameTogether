package com.example.demo.domain.service;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.article.UpdateArticleRequest;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

//    블로그 글 추가
    public Article save(AddArticleRequest request, String userName){
        return blogRepository.save(request.toEntity(userName));
    }

//    글 전체 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

//    글 하나 조회
    public Article findById(long id){
        System.out.println("글하나조회 서비스"+id);
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: " + id));
    }

//    글 삭제
    public void delete(long id){

        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

//    글 수정
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

//    본인이 작성했는지 확인
    private static void authorizeArticleAuthor(Article article){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!article.getAuthor().equals(userName)){
            throw new IllegalArgumentException("not authorized");
        }
    }
}
