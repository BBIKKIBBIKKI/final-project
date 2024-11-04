package com.wearei.finalsamplecode.api.chat.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomCreateResponse {
    private final String roodId;

    private final LocalDateTime createdAt = LocalDateTime.now();
}
