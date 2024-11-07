package com.wearei.finalsamplecode.api.team.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultTeamService {
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public Team findByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }

    @Transactional(readOnly = true)
    public Team findById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }
}
