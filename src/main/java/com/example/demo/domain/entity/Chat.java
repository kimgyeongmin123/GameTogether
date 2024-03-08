package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "roomId", nullable = false)
    private Long roomId;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "message")
    private String message;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Builder
    public Chat(Long roomId, String sender, String message){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
