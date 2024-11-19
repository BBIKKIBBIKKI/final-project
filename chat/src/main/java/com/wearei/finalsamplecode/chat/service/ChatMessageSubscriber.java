package com.wearei.finalsamplecode.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        try {
            // 메시지 내용이 JSON 문자열이라고 가정
            String jsonMessage = new String(message.getBody());

            // 한 번 더 파싱하여 이중 인코딩을 해결
            String decodedMessage = objectMapper.readValue(jsonMessage, String.class);

            // ObjectMapper를 사용하여 JSON을 ChatMessage 객체로 역직렬화
            ChatMessage incomingMessage = objectMapper.readValue(decodedMessage, ChatMessage.class);

            // sender 값을 추출하여 새로운 ChatMessage를 생성
            String subRoomName = new String(message.getChannel());
            String roomId = subRoomName.replace("chat:", "");

            // WebSocket을 통해 클라이언트에 메시지 전송
            messagingTemplate.convertAndSend("/sub/chat/" + roomId, incomingMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}