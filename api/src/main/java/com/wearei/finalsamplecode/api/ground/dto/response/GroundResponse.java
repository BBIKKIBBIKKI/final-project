package com.wearei.finalsamplecode.api.ground.dto.response;

import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import lombok.Getter;

@Getter
public class GroundResponse {
    private final Long teamId;
    private final String groundName;
    private final String location;
    private final String tel;
    private final String groundImg;

    public GroundResponse(Ground ground) {
        this.teamId = ground.getTeam().getId();
        this.groundName = ground.getGroundName();
        this.location = ground.getLocation();
        this.tel = ground.getTel();
        this.groundImg = ground.getGroundImg();
    }

    public GroundResponse(Long teamId, String groundName, String location, String tel, String groundImg) {
        this.teamId = teamId;
        this.groundName = groundName;
        this.location = location;
        this.tel = tel;
        this.groundImg = groundImg;
    }
}