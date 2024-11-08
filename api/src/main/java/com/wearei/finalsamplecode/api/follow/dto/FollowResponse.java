package com.wearei.finalsamplecode.api.follow.dto;

import com.wearei.finalsamplecode.core.domain.follow.entity.Follow;
import jakarta.validation.constraints.NotNull;

import static com.wearei.finalsamplecode.api.follow.dto.FollowResponse.*;

public sealed interface FollowResponse permits Create {
    record Create(
            @NotNull Long followId,
            @NotNull Long playerId
    ) implements FollowResponse {
        public Create(Follow follow) {
            this(
                    follow.getFollowId(),
                    follow.getPlayer().getId()
            );
        }
    }
}
