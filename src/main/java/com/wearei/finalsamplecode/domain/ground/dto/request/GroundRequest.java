package com.wearei.finalsamplecode.domain.ground.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroundRequest {
    private Long teamId;
    private String groundName;
    private String location;
    private String tel;
}
