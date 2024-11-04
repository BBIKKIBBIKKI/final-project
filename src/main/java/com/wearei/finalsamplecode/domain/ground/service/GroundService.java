package com.wearei.finalsamplecode.domain.ground.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.dto.request.GroundCreateRequest;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundCreateResponse;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundSearchResponse;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.service.CustomUserDetailsService;
import com.wearei.finalsamplecode.exception.ApiException;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroundService {
    private final TeamRepository teamRepository;
    private final GroundRepository groundRepository;
    private final CustomUserDetailsService userDetailsService;
    private final S3Api s3Api;

    @Transactional
    public GroundCreateResponse createGround(GroundCreateRequest request, AuthUser authUser, MultipartFile groundImg) {
        User user = userDetailsService.findUserById(authUser.getUserId());

        userDetailsService.checkIfAdmin(user);

        Team team = teamRepository.findById(request.getTeamId())
                        .orElseThrow(() -> new ApiException((ErrorStatus._NOT_FOUND_TEAM)));

        String groundImageUrl = null;
        try {
            groundImageUrl = s3Api.uploadImageToS3(groundImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        Ground ground = groundRepository.save(new Ground(request.getGroundName(), request.getLocation(), request.getTel(), groundImageUrl, team));

        return new GroundCreateResponse(ground.getTeam().getId(), ground.getGroundName(), ground.getLocation(), ground.getTel(), ground.getGroundImg());
    }

    @Transactional(readOnly = true)
    public GroundSearchResponse searchGround(String teamName, String groundName) {
        Optional<Ground> result = groundRepository.searchGroundByTeamOrGroundName(teamName, groundName);

        // 결과가 존재하지 않을 경우 빈 응답 반환
        return result.map(ground -> new GroundSearchResponse(
                ground.getTeam().getId(),
                ground.getGroundName(),
                ground.getLocation(),
                ground.getTel(),
                ground.getGroundImg()
        )).orElse(null);
    }
}