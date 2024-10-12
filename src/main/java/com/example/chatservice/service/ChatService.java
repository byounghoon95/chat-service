package com.example.chatservice.service;

import com.example.chatservice.dto.ChatRoomDto;
import com.example.chatservice.entity.ChatRoom;
import com.example.chatservice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> findAllRoom() {
        return chatRoomRepository.findAll().stream()
                .map(chatRoom -> chatRoom.toDto())
                .collect(Collectors.toList());
    }

    public void createRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(name);
        chatRoomRepository.save(chatRoom);
    }
}