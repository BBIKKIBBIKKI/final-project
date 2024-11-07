package com.wearei.finalsamplecode.api.player.service;

import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class DefaultPlayerService {
    private final PlayerRepository playerRepository;

    // 선수 단건 조회
    @Transactional(readOnly = true)
    public List<Player> getPlayerByNameAndTeamName(String playerName, String teamName) {
        return playerRepository.findByPlayerNameAndOptionalTeamName(playerName, teamName);
    }
}