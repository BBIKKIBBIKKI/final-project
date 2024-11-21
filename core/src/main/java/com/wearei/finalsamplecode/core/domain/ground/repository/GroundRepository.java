package com.wearei.finalsamplecode.core.domain.ground.repository;

import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroundRepository extends JpaRepository<Ground, Long> {
}