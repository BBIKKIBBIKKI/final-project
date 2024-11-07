package com.wearei.finalsamplecode.api.follow.dto.response;

import com.wearei.finalsamplecode.domain.follow.entity.Follow;
import lombok.Getter;

@Getter
public class CreateFollowResponse {
    private final Long followId;
    private final Long playerId;

    public CreateFollowResponse(Follow follow) {
        this.followId = follow.getFollowId();
        this.playerId = follow.getPlayer().getId();
    }

    public CreateFollowResponse(Long followId, Long playerId) {
        this.followId = followId;
        this.playerId = playerId;
    }
}
