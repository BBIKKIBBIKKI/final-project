package com.wearei.finalsamplecode.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wearei.finalsamplecode.chat.dto.ChatMessage;
import com.wearei.finalsamplecode.chat.service.ChatHistoryService;
import com.wearei.finalsamplecode.chat.service.ChatMessagePublish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessagePublish chatMessagePublish;

    private final ChatHistoryService chatHistoryService;

    @MessageMapping("/chat/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable("roomId") String roomId, ChatMessage message) {
        try {
            chatMessagePublish.publishMessage(roomId, message);
        } catch (JsonProcessingException e){
            log.info("메세지 전송 오류 : {}", e.getMessage());
        }

        return message;
    }
    @ResponseBody
    @GetMapping("/chat/history/{roomId}")
    public List<ChatMessage> getCHatHistory(@PathVariable("roomId") String roomId) {
        return chatHistoryService.getChatHistory(roomId, 0, -1);
    }

    @ResponseBody
    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "채팅 어플리케이션 헬스체크 정상적";
    }
}
