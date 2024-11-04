package com.wearei.finalsamplecode.api.ground.dto.request;

import lombok.Getter;

@Getter
public class GroundRequest {
    private final Long teamId;
    private final String groundName;
    private final String location;
    private final String tel;

    public GroundRequest (Long teamId, String groundName, String location, String tel) {
        this.teamId = teamId;
        this.groundName = groundName;
        this.location = location;
        this.tel = tel;
    }
}