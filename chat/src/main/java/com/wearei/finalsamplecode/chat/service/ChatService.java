package com.wearei.finalsamplecode.chat.service;

import com.wearei.finalsamplecode.domain.chat.dto.response.ChatRoomCreateResponse;
import com.wearei.finalsamplecode.domain.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatRoomCreateResponse createRoom(String roomId) {
        return ChatRoomCreateResponse.builder()
                .roodId(chatRepository.createRoom(roomId).getRoomId())
                .build();
    }
}