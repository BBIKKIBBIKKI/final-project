package com.wearei.finalsamplecode.domain.follow.dto.request;

import lombok.Getter;

@Getter
public class CreateFollowRequest {
    private Long userId;
    private Long playerId;
}
