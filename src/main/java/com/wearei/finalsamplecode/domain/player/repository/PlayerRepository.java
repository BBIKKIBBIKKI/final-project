package com.wearei.finalsamplecode.domain.player.repository;

import com.wearei.finalsamplecode.domain.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    // 선수이름 검색
    Optional<Player> findByPlayerName(String playerName);
}
