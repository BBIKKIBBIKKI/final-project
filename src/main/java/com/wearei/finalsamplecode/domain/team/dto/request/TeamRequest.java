package com.wearei.finalsamplecode.domain.team.dto.request;

import lombok.Getter;

@Getter
public class TeamRequest {
    private final String teamName;
    private final String themeSong;

    public TeamRequest(String teamName, String themeSong) {
        this.teamName = teamName;
        this.themeSong = themeSong;
    }
}