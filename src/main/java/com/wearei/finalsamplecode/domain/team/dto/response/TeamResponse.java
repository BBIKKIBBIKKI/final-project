package com.wearei.finalsamplecode.domain.team.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamResponse {
    private String teamName;
    private String uniformImg;
    private String mascotImg;
    private String equipmentImg;
    private String themeSong;
}