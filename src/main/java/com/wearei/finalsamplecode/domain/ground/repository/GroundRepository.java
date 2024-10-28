package com.wearei.finalsamplecode.domain.ground.repository;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GroundRepository extends JpaRepository<Ground, Long> {
    Optional<Ground> findByGroundName(String groundName);
    Optional<Ground> findByTeam(Team team);
}