package com.wearei.finalsamplecode.domain.ground.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroundResponse {
    private final Long teamId;
    private final String groundName;
    private final String location;
    private final String tel;
    private final String groundImg;
}