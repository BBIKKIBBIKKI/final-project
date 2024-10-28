package com.wearei.finalsamplecode.domain.player.dto.response;

import lombok.Getter;

@Getter
public class PlayerSearchResponse{
    private final Long playerId;
    private final Long teamId;
    private final Long playerAge;
    private final Long follow;
    private final String playerName;
    private final String teamName;
    private final String position;
    private final String playerSong;
    private final String playerBodyCheck;

    public PlayerSearchResponse(Long playerId, Long teamId, Long playerAge, Long follow, String playerName, String teamName, String position, String playerSong, String playerBodyCheck) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.playerAge = playerAge;
        this.follow = follow;
        this.playerName = playerName;
        this.teamName = teamName;
        this.position = position;
        this.playerSong = playerSong;
        this.playerBodyCheck = playerBodyCheck;
    }
}
