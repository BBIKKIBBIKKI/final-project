package com.wearei.finalsamplecode.domain.player.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    // 선수 단건 조회
    @Transactional(readOnly = true)
    public List<PlayerSearchResponse> getPlayerByNameAndTeamName(String playerName, String teamName) {
        // JPQL 쿼리로 선수 검색 (teamName이 없으면 playerName만으로 검색)
        List<Player> players = playerRepository.findByPlayerNameAndOptionalTeamName(playerName, teamName);

        if (players.isEmpty()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_PLAYER);
        }

        return players.stream()
                .map(player -> new PlayerSearchResponse(
                        player.getId(),
                        player.getPlayerAge(),
                        player.getFollow(),
                        player.getPlayerName(),
                        player.getTeamName(),
                        player.getPosition(),
                        player.getPlayerSong(),
                        player.getPlayerBodyCheck()))
                .toList();
    }
}