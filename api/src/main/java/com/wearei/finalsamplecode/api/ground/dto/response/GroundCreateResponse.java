package com.wearei.finalsamplecode.api.ground.dto.response;

import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;

public class GroundCreateResponse extends GroundResponse {
    public GroundCreateResponse(Ground ground) {
        super(ground);
    }

    public GroundCreateResponse(Long teamId, String groundName, String location, String tel, String groundImg) {
        super(teamId, groundName, location, tel, groundImg);
    }
}