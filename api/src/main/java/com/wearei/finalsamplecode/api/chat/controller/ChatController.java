package com.wearei.finalsamplecode.api.chat.controller;

import com.wearei.finalsamplecode.api.chat.dto.response.ChatRoomCreateResponse;
import com.wearei.finalsamplecode.api.chat.service.ChatService;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import lombok.RequiredArgsConstructor;

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