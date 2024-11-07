package com.wearei.finalsamplecode.api.team.dto.response;

import com.wearei.finalsamplecode.domain.team.entity.Team;

public class TeamSearchResponse extends TeamResponse {
    public TeamSearchResponse(Team team) {
        super(team);
    }

    public TeamSearchResponse(String teamName, String uniformImg, String mascotImg, String equipmentImg, String themeSong) {
        super(teamName, uniformImg, mascotImg, equipmentImg, themeSong);
    }
}
