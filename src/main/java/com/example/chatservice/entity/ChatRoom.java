package com.example.chatservice.entity;

import com.example.chatservice.dto.ChatRoomDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "CHATROOM")
@NoArgsConstructor
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    private String roomName;

    public ChatRoom(String roomName) {
        this.roomId = String.valueOf(UUID.randomUUID());
        this.roomName = roomName;
    }

    public ChatRoomDto toDto() {
        return ChatRoomDto.builder()
                .roomId(roomId)
                .roomName(roomName)
                .build();
    }
}
