package com.wearei.finalsamplecode.domain.ground.dto.response;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GroundResponse {
    private final Long teamId;
    private final String groundName;
    private final String location;
    private final String tel;
    private final String groundImg;

    public GroundResponse(Long teamId, String groundName, String location, String tel, String groundImg) {
        this.teamId = teamId;
        this.groundName = groundName;
        this.location = location;
        this.tel = tel;
        this.groundImg = groundImg;
    }
}
