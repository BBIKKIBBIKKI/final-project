package com.wearei.finalsamplecode.api.ground.dto;

import com.wearei.finalsamplecode.api.ground.dto.GroundResponse.*;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import jakarta.validation.constraints.NotNull;

public sealed interface GroundResponse permits Create, Search{
    record Create(
            @NotNull Long teamId,
            String groundName,
            String location,
            String tel,
            String groundImg
    ) implements GroundResponse{
        public Create(Ground ground) {
            this(
              ground.getTeam().getId(),
              ground.getGroundName(),
              ground.getLocation(),
              ground.getTel(),
              ground.getGroundImg()
            );
        }
    }

    record Search(
            @NotNull Long teamId,
            String groundName,
            String location,
            String tel,
            String groundImg
    ) implements GroundResponse{
        public Search(Ground ground) {
            this(
                    ground.getTeam().getId(),
                    ground.getGroundName(),
                    ground.getLocation(),
                    ground.getTel(),
                    ground.getGroundImg()
            );
        }
    }
}
