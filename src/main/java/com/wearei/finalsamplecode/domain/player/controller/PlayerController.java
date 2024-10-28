package com.wearei.finalsamplecode.domain.player.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayerController{
    private final PlayerService playerService;

    @GetMapping("/player")
    public ApiResponse<PlayerSearchResponse> getSearchPlayerName(@RequestParam String playerName){
        PlayerSearchResponse playerSearchResponse = playerService.getPlayerByName(playerName);
        return ApiResponse.onSuccess(playerSearchResponse);
    }
}
