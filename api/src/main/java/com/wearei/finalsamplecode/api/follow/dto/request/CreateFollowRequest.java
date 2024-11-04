package com.wearei.finalsamplecode.api.follow.dto.request;

import lombok.Getter;

@Getter
public class CreateFollowRequest {
    private Long userId;
    private Long playerId;
}
