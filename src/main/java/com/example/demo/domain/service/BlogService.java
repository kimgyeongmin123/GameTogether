package com.example.demo.domain.service;

import com.example.demo.domain.dto.AddArticleRequest;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

//    블로그 글 추가
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

//    글 전체 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

//    글 하나 조회
    public Article findById(long id){
        System.out.println("글하나조회 서비스"+id);
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
    }
}
