package com.wearei.finalsamplecode.chat.controller;

import com.wearei.finalsamplecode.chat.response.ChatRoomCreateResponse;
import com.wearei.finalsamplecode.api.chat.service.ChatService;
import com.wearei.finalsamplecode.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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