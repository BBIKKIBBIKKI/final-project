package com.wearei.finalsamplecode.api.player;

import com.wearei.finalsamplecode.api.player.dto.response.PlayerResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerApi {
    private final DefaultPlayerService defaultPlayerService;

    @GetMapping("/players")
    public ApiResponse<List<PlayerResponse.Search>> getSearchPlayerName(@RequestParam String playerName, @RequestParam(required = false) String teamName) {
        List<Player> players = defaultPlayerService.getPlayerByNameAndTeamName(playerName, teamName);
        return ApiResponse.onSuccess(players.stream().map(PlayerResponse.Search::new).toList());
    }

    @GetMapping("/ranking")
    public ApiResponse<List<PlayerResponse.Ranking>> getRankingByFollow(@RequestParam(defaultValue = "10") Integer limit){
        List<Player> players = defaultPlayerService.getRankingByFollow(limit);
        List<PlayerResponse.Ranking> rankings = players.stream()
                .map(PlayerResponse.Ranking::new)
                .toList();
        return ApiResponse.onSuccess(rankings);
    }
}
