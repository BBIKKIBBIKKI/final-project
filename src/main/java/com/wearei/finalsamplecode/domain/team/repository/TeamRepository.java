package com.wearei.finalsamplecode.domain.team.repository;

import com.wearei.finalsamplecode.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
