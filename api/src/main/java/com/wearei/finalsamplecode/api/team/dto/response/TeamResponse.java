package com.wearei.finalsamplecode.api.team.dto.response;

import lombok.Getter;

@Getter
public class TeamResponse {
    private final String teamName;
    private final String uniformImg;
    private final String mascotImg;
    private final String equipmentImg;
    private final String themeSong;

    public TeamResponse(String teamName, String uniformImg, String mascotImg, String equipmentImg, String themeSong) {
        this.teamName = teamName;
        this.uniformImg = uniformImg;
        this.mascotImg = mascotImg;
        this.equipmentImg = equipmentImg;
        this.themeSong = themeSong;
    }
}