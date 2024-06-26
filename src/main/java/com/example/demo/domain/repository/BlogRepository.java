package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long>{

    List<Article> findByAuthor(String author);

    List<Article> findBySelectedGame(String selectedGame);

    List<Article> findByTitleContaining(String input);

    List<Article> findByContentContaining(String input);

    List<Article> findByNicknameContaining(String input);

}
