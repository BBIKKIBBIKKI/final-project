package com.wearei.finalsamplecode.api.team.dto.response;

import com.wearei.finalsamplecode.core.domain.team.entity.Team;

public class TeamCreateResponse extends TeamResponse {
    public TeamCreateResponse(Team team) {
        super(team);
    }

    public TeamCreateResponse(String teamName, String uniformImg, String mascotImg, String equipmentImg, String themeSong) {
        super(teamName, uniformImg, mascotImg, equipmentImg, themeSong);
    }
}
