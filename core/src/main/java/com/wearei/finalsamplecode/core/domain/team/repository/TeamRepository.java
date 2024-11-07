package com.wearei.finalsamplecode.core.domain.team.repository;

import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);

    default Team findByTeamId(Long teamId){
        return findById(teamId).orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }
}