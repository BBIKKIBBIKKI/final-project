package com.wearei.finalsamplecode.domain.ground.dto.response;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroundResponse {
    private Long teamId;
    private String groundName;
    private String location;
    private String tel;
    private String groundImg;

    public GroundResponse(Ground ground) {
        this.teamId = ground.getTeam().getId();
        this.groundName = ground.getGroundName();
        this.location = ground.getLocation();
        this.tel = ground.getTel();
        this.groundImg = ground.getGroundImg();
    }
}
