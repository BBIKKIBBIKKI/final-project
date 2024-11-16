package com.wearei.finalsamplecode.chat.controller;

import com.wearei.finalsamplecode.chat.dto.ChatMessage;
import com.wearei.finalsamplecode.chat.service.ChatMessagePublish;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessagePublish chatMessagePublish;

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/sub/chat/{roomId}")  // 특정 roomId에만 메시지를 전달
    public ChatMessage sendMessage(@DestinationVariable("roomId") String roomId, ChatMessage message) {

        chatMessagePublish.publishMessage(roomId, message);

        return new ChatMessage(message.sender(), message.content());
    }
}
