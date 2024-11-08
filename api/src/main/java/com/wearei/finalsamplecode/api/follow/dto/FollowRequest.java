package com.wearei.finalsamplecode.api.follow.dto;

import jakarta.validation.constraints.NotNull;

import static com.wearei.finalsamplecode.api.follow.dto.FollowRequest.*;

public sealed interface FollowRequest permits Create {

    record Create(
            @NotNull Long userId,
            @NotNull Long playerId
    ) implements FollowRequest {}
}
