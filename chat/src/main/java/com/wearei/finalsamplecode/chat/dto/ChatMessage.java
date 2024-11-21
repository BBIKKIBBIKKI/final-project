package com.wearei.finalsamplecode.chat.dto;

import java.time.LocalDateTime;

public record ChatMessage(
        String sender,
        String content,
        LocalDateTime sendTime) {
}