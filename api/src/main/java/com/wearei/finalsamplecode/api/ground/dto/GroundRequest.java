package com.wearei.finalsamplecode.api.ground.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.wearei.finalsamplecode.api.ground.dto.GroundRequest.*;

public sealed interface GroundRequest permits Create {
    record Create(
            @NotNull Long teamId,
            @NotBlank String groundName,
            String location,
            String tel
    ) implements GroundRequest {}
}
