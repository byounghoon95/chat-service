package com.example.chatservice.redis;

import com.example.chatservice.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Object publish
     */
    public void publish(ChannelTopic topic, ChatMessageDto dto) {
        redisTemplate.convertAndSend(topic.getTopic(), dto);
    }

    /**
     * String publish
     */
    public void publish(ChannelTopic topic ,String data) {
        redisTemplate.convertAndSend(topic.getTopic(), data);
    }
}