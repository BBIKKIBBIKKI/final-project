package com.wearei.finalsamplecode.api.ground.service;

import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultGroundService {
    private final GroundQuery groundQuery;

    @Transactional(readOnly = true)
    public Ground searchGround(String teamName, String groundName) {
        return groundQuery.searchGroundByTeamOrGroundName(teamName, groundName).orElse(null);
    }
}
