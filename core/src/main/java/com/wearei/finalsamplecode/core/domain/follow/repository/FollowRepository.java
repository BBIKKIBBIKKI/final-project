package com.wearei.finalsamplecode.core.domain.follow.repository;

import com.wearei.finalsamplecode.core.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
