package com.example.chatservice.service;

import com.example.chatservice.dto.ChatMessageDto;
import com.example.chatservice.dto.ChatRoomDto;
import com.example.chatservice.entity.ChatRoom;
import com.example.chatservice.redis.RedisPublisher;
import com.example.chatservice.redis.RedisSubscribeListener;
import com.example.chatservice.repository.ChatRepository;
import com.example.chatservice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisPublisher redisPublisher;
    private final RedisSubscribeListener redisSubscribeListener;

    public List<ChatRoomDto> findAllRoom() {
        return chatRoomRepository.findAll().stream()
                .map(chatRoom -> chatRoom.toDto())
                .collect(Collectors.toList());
    }

    public void createRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(name);
        chatRoomRepository.save(chatRoom);
    }

    public void sendMessage(ChatMessageDto message) {
        // 요청한 Channel 을 구독.
        redisMessageListenerContainer.addMessageListener(redisSubscribeListener, new ChannelTopic("chat"));

        // Redis에  Message 전송
        redisPublisher.publish(new ChannelTopic("chat"), message);
    }

    public List<ChatMessageDto> findMessage(String roomId) {
        return chatRepository.findByRoomId(roomId).stream()
                .map(chatMessage -> chatMessage.toDto())
                .collect(Collectors.toList());
    }
}