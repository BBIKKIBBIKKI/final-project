package com.wearei.finalsamplecode.domain.ground.repository;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroundRepository extends JpaRepository<Ground, Long>, GroundRepositoryQuery {
}