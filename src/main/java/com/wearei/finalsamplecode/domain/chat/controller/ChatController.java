package com.wearei.finalsamplecode.domain.chat.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.chat.dto.response.ChatRoomCreateResponse;
import com.wearei.finalsamplecode.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/room")
    public ApiResponse<ChatRoomCreateResponse> createRoom(@RequestParam String roomId) {
        return ApiResponse.onSuccess(chatService.createRoom(roomId));
    }
}