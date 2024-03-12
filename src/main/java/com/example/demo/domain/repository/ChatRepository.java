package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByRoomId(Long id);
}
