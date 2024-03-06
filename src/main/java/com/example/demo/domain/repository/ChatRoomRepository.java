package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Article;
import com.example.demo.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
