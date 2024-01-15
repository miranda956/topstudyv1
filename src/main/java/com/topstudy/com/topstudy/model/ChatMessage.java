package com.topstudy.com.topstudy.model;

import com.topstudy.com.topstudy.model.ChatRoom;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom chatRoom;

    @Column(nullable = false)
    private String content;

    // Constructors, getters, and setters
}
