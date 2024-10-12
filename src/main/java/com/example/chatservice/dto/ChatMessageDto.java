package com.example.chatservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime time;

    @Builder
    public ChatMessageDto(String roomId, String sender, String message, LocalDateTime time) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
    }
}
