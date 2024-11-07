package com.wearei.finalsamplecode.api.ground.dto.response;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;

public class GroundSearchResponse extends GroundResponse {
    public GroundSearchResponse(Ground ground) {
        super(ground);
    }

    public GroundSearchResponse(Long teamId, String groundName, String location, String tel, String groundImg) {
        super(teamId, groundName, location, tel, groundImg);
    }
}