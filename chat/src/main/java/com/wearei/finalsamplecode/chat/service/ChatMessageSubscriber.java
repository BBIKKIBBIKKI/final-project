package com.wearei.finalsamplecode.chat.service;

import com.wearei.finalsamplecode.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String roomId = new String(message.getChannel());
        ChatMessage chatMessage = new ChatMessage(
                "오현택",
                new String(message.getBody())
        );

        messagingTemplate.convertAndSend("/sub/chat/" + roomId, chatMessage);
    }
}