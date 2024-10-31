package com.wearei.finalsamplecode.domain.team.repository;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.exception.ApiException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);

    default Team findByTeamId(Long teamId){
        return findById(teamId).orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }
}