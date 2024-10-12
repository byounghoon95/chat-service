package com.example.chatservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomDto {
    private String roomId;
    private String roomName;

    @Builder
    public ChatRoomDto(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }
}
