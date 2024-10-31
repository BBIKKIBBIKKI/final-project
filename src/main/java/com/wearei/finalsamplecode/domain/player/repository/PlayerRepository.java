package com.wearei.finalsamplecode.domain.player.repository;

import com.wearei.finalsamplecode.domain.player.entity.Player;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.playerName = :playerName AND (:teamName IS NULL OR p.teamName = :teamName)")
    List<Player> findByPlayerNameAndOptionalTeamName(@Param("playerName") String playerName, @Param("teamName") String teamName);
}