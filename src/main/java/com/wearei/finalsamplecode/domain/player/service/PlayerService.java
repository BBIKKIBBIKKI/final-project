package com.wearei.finalsamplecode.domain.player.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    // 선수 단건 조회
    @Transactional(readOnly = true)
    public PlayerSearchResponse getPlayerByName(String playerName){
        // 선수이름으로 선수찾기
        Player player = playerRepository.findByPlayerName(playerName)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_PLAYER));

        // 찾은 Player를 PlayerSearchResponse로 변환
        return new PlayerSearchResponse(
                player.getId(),
                player.getTeamId(),
                player.getPlayerAge(),
                player.getFollow(),
                player.getPlayerName(),
                player.getTeamName(),
                player.getPosition(),
                player.getPlayerSong(),
                player.getPlayerBodyCheck());
    }
}
