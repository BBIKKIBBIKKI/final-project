package com.wearei.finalsamplecode.core.domain.player.repository;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.playerName = :playerName AND (:teamName IS NULL OR p.teamName = :teamName)")
    List<Player> findByPlayerNameAndOptionalTeamName(@Param("playerName") String playerName, @Param("teamName") String teamName);

    default Player findByPlayerIdOrThrow(Long playerId) {
        return findById(playerId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_PLAYER));
    }
}