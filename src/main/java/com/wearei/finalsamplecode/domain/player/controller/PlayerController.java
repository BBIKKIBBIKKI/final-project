package com.wearei.finalsamplecode.domain.player.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
