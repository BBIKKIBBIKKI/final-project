package com.wearei.finalsamplecode.api.ground.service;

import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultGroundService {
    private final GroundRepository groundRepository;
    @Transactional(readOnly = true)
    public Ground searchGround(String teamName, String groundName) {
        return groundRepository.searchGroundByTeamOrGroundName(teamName, groundName).orElse(null);
    }
}
