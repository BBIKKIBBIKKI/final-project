package com.wearei.finalsamplecode.domain.ground.repository;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;

import java.util.Optional;

public interface GroundRepositoryQuery {
    Optional<Ground> searchGroundByTeamOrGroundName(String teamName, String groundName);
}
