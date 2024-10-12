package com.example.chatservice.entity;

import com.example.chatservice.dto.ChatMessageDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "MESSAGE")
@NoArgsConstructor
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime time;

    public ChatMessage(String roomId, String sender, String message, LocalDateTime time) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
    }

    public ChatMessage(ChatMessageDto dto) {
        this.roomId = dto.getRoomId();
        this.sender = dto.getSender();
        this.message = dto.getMessage();
        this.time = LocalDateTime.now();
    }

    public ChatMessageDto toDto() {
        return ChatMessageDto.builder()
                .roomId(roomId)
                .sender(sender)
                .message(message)
                .time(time)
                .build();
    }
}