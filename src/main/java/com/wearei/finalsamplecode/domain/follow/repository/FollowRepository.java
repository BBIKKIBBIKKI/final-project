package com.wearei.finalsamplecode.domain.follow.repository;

import com.wearei.finalsamplecode.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>{
}
