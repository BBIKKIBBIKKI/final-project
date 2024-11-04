package com.wearei.finalsamplecode.domain.ground.repository;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroundRepository extends JpaRepository<Ground, Long> {
    Optional<Ground> findByGroundName(String groundName);
    Optional<Ground> findByTeam(Team team);
}