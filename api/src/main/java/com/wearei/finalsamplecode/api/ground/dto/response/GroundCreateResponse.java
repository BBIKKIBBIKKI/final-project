package com.wearei.finalsamplecode.api.ground.dto.response;

public class GroundCreateResponse extends GroundResponse {
    public GroundCreateResponse(Long teamId, String groundName, String location, String tel, String groundImg) {
        super(teamId, groundName, location, tel, groundImg);
    }
}