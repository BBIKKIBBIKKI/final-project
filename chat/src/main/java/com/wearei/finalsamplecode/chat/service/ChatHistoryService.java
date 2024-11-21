package com.wearei.finalsamplecode.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearei.finalsamplecode.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public List<ChatMessage> getChatHistory(String roomId, long start, long end) {

        Set<Object> jsonMessages = redisTemplate.opsForZSet().range("chat:messages:" + roomId, start, end);

        return jsonMessages.stream()
                .map(json -> {
                    try {
                        return objectMapper.readValue((String) json, ChatMessage.class);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to deserialize message", e);
                    }
                })
                .collect(Collectors.toList());
    }

}
