package com.wearei.finalsamplecode.domain.follow.dto.response;

import lombok.Getter;

@Getter
public class CreateFollowResponse {
    private final Long followId;
    private final Long playerId;

    public CreateFollowResponse(Long followId, Long playerId) {
        this.followId = followId;
        this.playerId = playerId;
    }
}
