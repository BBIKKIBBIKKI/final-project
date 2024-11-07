package com.wearei.finalsamplecode.core.domain.player.repository;

import com.wearei.finalsamplecode.core.domain.player.entity.Player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.playerName = :playerName AND (:teamName IS NULL OR p.teamName = :teamName)")
    List<Player> findByPlayerNameAndOptionalTeamName(@Param("playerName") String playerName, @Param("teamName") String teamName);
}