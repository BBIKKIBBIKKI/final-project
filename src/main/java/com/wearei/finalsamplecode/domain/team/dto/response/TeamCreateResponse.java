package com.wearei.finalsamplecode.domain.team.dto.response;

import com.wearei.finalsamplecode.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamCreateResponse {
    private String teamName;
    private String uniformImg;
    private String mascotImg;
    private String equipmentImg;
    private String themeSong;

    public TeamCreateResponse(Team team) {
        this.teamName = team.getTeamName();
        this.uniformImg = team.getUniformImg();
        this.mascotImg = team.getMascotImg();
        this.themeSong = team.getThemeSong();
    }
}
