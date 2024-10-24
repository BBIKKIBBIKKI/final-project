package com.wearei.finalsamplecode.domain.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class ChatRoomCreateResponse {
    private final String roodId;

    private final LocalDateTime createdAt = LocalDateTime.now();
}
