package com.wearei.finalsamplecode.api.ground.dto.response;

public class GroundSearchResponse extends GroundResponse {
    public GroundSearchResponse(Long teamId, String groundName, String location, String tel, String groundImg) {
        super(teamId, groundName, location, tel, groundImg);
    }
}