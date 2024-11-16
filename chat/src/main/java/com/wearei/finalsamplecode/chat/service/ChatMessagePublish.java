package com.wearei.finalsamplecode.chat.service;

import com.wearei.finalsamplecode.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessagePublish {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publishMessage(String roomId, ChatMessage message) {
        // Redis 채널에 방 이름을 포함해서 메시지를 발행
        redisTemplate.convertAndSend("chat:" + roomId, message.content());
    }
}