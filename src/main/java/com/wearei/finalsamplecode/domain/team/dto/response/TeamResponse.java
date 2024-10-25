package com.wearei.finalsamplecode.domain.team.dto.response;

import com.wearei.finalsamplecode.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TeamResponse {
    private String teamName;
    private String uniformImg;
    private String mascotImg;
    private String equipmentImg;
    private String themeSong;

    public TeamResponse(String teamName, String uniformImg, String mascotImg, String equipmentImg, String themeSong) {
        this.teamName = teamName;
        this.uniformImg = uniformImg;
        this.mascotImg = mascotImg;
        this.equipmentImg = equipmentImg;
        this.themeSong = themeSong;
    }
}