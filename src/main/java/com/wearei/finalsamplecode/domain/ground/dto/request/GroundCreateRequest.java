package com.wearei.finalsamplecode.domain.ground.dto.request;

public class GroundCreateRequest extends GroundRequest {
    public GroundCreateRequest(Long teamId, String groundName, String location, String tel) {
        super(teamId, groundName, location, tel);
    }
}