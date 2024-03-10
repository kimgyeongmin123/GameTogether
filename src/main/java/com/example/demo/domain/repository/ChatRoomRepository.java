package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    boolean existsByArticleIdAndUserName(Long articleId, String userName);

    ChatRoom findByArticleIdAndUserName(Long articleId, String userName);

    @Query("SELECT R FROM ChatRoom R WHERE R.id IN " +
            "(SELECT C.roomId FROM Chat C WHERE C.sender = :username OR C.receiver = :username)")
    List<ChatRoom> findByUserIdInChat(String username);
}
