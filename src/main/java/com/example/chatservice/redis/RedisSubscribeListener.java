package com.example.chatservice.redis;

import com.example.chatservice.dto.ChatMessageDto;
import com.example.chatservice.entity.ChatMessage;
import com.example.chatservice.repository.ChatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscribeListener implements MessageListener {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ChatRepository chatRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = redisTemplate
                    .getStringSerializer().deserialize(message.getBody());

            ChatMessageDto messageDto = objectMapper.readValue(publishMessage, ChatMessageDto.class);

            log.info("Redis Subscribe Channel : " + messageDto.getRoomId());
            log.info("Redis SUB Message : {}", publishMessage);

            // 레디스로 받은 데이터를 웹소켓에 전송
            messagingTemplate.convertAndSend("/topic/public", messageDto);
            chatRepository.save(new ChatMessage(messageDto));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}