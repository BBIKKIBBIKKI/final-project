package com.wearei.finalsamplecode.api.team.dto.response;

import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import lombok.Getter;

@Getter
public class TeamResponse {
    private final String teamName;
    private final String uniformImg;
    private final String mascotImg;
    private final String equipmentImg;
    private final String themeSong;

    public TeamResponse(Team team) {
        this.teamName = team.getTeamName();
        this.uniformImg = team.getUniformImg();
        this.mascotImg = team.getMascotImg();
        this.equipmentImg = team.getEquipmentImg();
        this.themeSong = team.getThemeSong();
    }

    public TeamResponse(String teamName, String uniformImg, String mascotImg, String equipmentImg, String themeSong) {
        this.teamName = teamName;
        this.uniformImg = uniformImg;
        this.mascotImg = mascotImg;
        this.equipmentImg = equipmentImg;
        this.themeSong = themeSong;
    }
}