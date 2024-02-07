package com.example.demo.controller;

import com.example.demo.domain.dto.article.AddArticleRequest;
import com.example.demo.domain.dto.article.UpdateArticleRequest;
import com.example.demo.domain.entity.Article;
import com.example.demo.domain.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 글 추가 성공")
    @Test
    public void addArticle() throws Exception{
//        given
        final String url = "/api/articles";
        final String title = "망고 고르는 법";
        final String content = "표면을 손가락으로 살짝눌러서 조금 들어가면 알맞게 익은것입니다.";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

//        JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

//        when - 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

//        then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);

    }

    @DisplayName("findArticle : 글 조회 전체 성공")
    @Test
    public void findAllArticles() throws Exception {
//        given
        final String url = "/api/articles";
        final String title = "망고 고르는 법";
        final String content = "표면을 손가락으로 살짝눌러서 조금 들어가면 알맞게 익은것입니다.";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

//        when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

//        then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @DisplayName("findArticle : 글 조회 하나 성공")
    @Test
    public void findArticle() throws Exception{
//        given
        final String url = "/api/articles/{id}";
        final String title = "망고 고르는 법";
        final String content = "표면을 손가락으로 살짝눌러서 조금 들어가면 알맞게 익은것입니다.";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

//        when
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

//        then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));

    }

    @DisplayName("deleteArticle : 글 삭제 성공")
    @Test
    public void deleteArticle() throws Exception{
//        given
        final String url = "/api/articles/{id}";
        final String title = "망고의 비밀";
        final String content = "망고는 사실 과일이다.";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

//        when
        mockMvc.perform(delete(url, savedArticle.getId()))
                .andExpect(status().isOk());

//        then
        List<Article> articles = blogRepository.findAll();

        assertThat(articles.isEmpty()).isTrue();
    }

    @DisplayName("updateArticle : 글 수정 성공")
    @Test
    public void updateArticle() throws Exception{
//        given
        final String url = "/api/articles/{id}";
        final String title = "망고의 비밀";
        final String content = "망고는 사실 과일이다.";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent);

//        when
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

//        then
        result.andExpect(status().isOk());

        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);


    }



}