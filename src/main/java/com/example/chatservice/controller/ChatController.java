package com.example.chatservice.controller;

import com.example.chatservice.dto.ChatMessageDto;
import com.example.chatservice.dto.ChatRoomDto;
import com.example.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage(@Payload ChatMessageDto chatMessage) {
        chatService.sendMessage(chatMessage);
    }

    @PostMapping("/chat")
    public void createRoom(@RequestParam("name") String name) {
        chatService.createRoom(name);
    }

    @GetMapping("/chat/rooms")
    public List<ChatRoomDto> getRooms() {
        return chatService.findAllRoom();
    }

    @GetMapping("/chat")
    public List<ChatMessageDto> getMessage(@RequestParam("roomId") String roomId) {
        return chatService.findMessage(roomId);
    }
}