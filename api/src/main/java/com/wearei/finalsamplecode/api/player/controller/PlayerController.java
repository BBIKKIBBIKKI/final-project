package com.wearei.finalsamplecode.api.player.controller;

import com.wearei.finalsamplecode.api.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.api.player.service.DefaultPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final DefaultPlayerService defaultPlayerService;

    @GetMapping("/players")
    public ApiResponse<List<PlayerSearchResponse>> getSearchPlayerName(@RequestParam String playerName, @RequestParam(required = false) String teamName) {
        List<Player> players = defaultPlayerService.getPlayerByNameAndTeamName(playerName, teamName);;
        return ApiResponse.onSuccess(players.stream().map(PlayerSearchResponse::new).toList());
    }
}
