package com.wearei.finalsamplecode.api.player.controller;

import com.wearei.finalsamplecode.api.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.api.player.service.PlayerService;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/players")
    public ApiResponse<List<PlayerSearchResponse>> getSearchPlayerName(@RequestParam String playerName, @RequestParam(required = false) String teamName) {
        List<PlayerSearchResponse> playerSearchResponse = playerService.getPlayerByNameAndTeamName(playerName, teamName);
        return ApiResponse.onSuccess(playerSearchResponse);
    }
}
