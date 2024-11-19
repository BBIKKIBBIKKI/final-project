package com.wearei.finalsamplecode.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearei.finalsamplecode.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZoneOffset;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatMessagePublish {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public void publishMessage(String roomId, ChatMessage message) throws JsonProcessingException {

        log.info("메세지 발행 : {}, {}", roomId, message.content());

        long timestamp = message.sendTime().toEpochSecond(ZoneOffset.UTC);
        String jsonMessage = objectMapper.writeValueAsString(message);

        redisTemplate.opsForZSet().add("chat:messages:" + roomId, jsonMessage, timestamp);

        // 메시지 키에 TTL 설정 (예: 1시간 = 3600초)
        redisTemplate.expire("chat:messages:" + roomId, Duration.ofDays(1));

        // Redis 채널에 방 이름을 포함해서 메시지를 발행
        redisTemplate.convertAndSend("chat:" + roomId, jsonMessage);
    }
}