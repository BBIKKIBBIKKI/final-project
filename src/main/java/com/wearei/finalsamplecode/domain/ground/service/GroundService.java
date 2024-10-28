package com.wearei.finalsamplecode.domain.ground.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.config.S3ClientUtility;
import com.wearei.finalsamplecode.domain.ground.dto.request.GroundCreateRequest;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundCreateResponse;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundSearchResponse;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GroundService {
    private final TeamRepository teamRepository;
    private final GroundRepository groundRepository;
    private final UserRepository userRepository;
    private final S3ClientUtility s3ClientUtility;

    @Transactional
    public GroundCreateResponse createGround(GroundCreateRequest request, AuthUser authUser, MultipartFile groundImg) {
        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));

        checkIfAdmin(user);

        Team team = teamRepository.findById(request.getTeamId())
                        .orElseThrow(() -> new ApiException((ErrorStatus._NOT_FOUND_TEAM)));

        String groundImageUrl = null;
        try {
            groundImageUrl = s3ClientUtility.uploadImageToS3(groundImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        Ground ground = groundRepository.save(new Ground(request.getGroundName(), request.getLocation(), request.getTel(), groundImageUrl, team));

        return new GroundCreateResponse(ground.getTeam().getId(), ground.getGroundName(), ground.getLocation(), ground.getTel(), ground.getGroundImg());
    }

    public GroundSearchResponse searchGround(AuthUser authUser, String teamName, String groundName) {
        Ground ground = null;

        if (teamName != null && !teamName.isEmpty()) {
            Team team = teamRepository.findByTeamName(teamName)
                    .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));

            ground = groundRepository.findByTeam(team)
                    .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));
        }
        else if (groundName != null && !groundName.isEmpty()) {
            ground = groundRepository.findByGroundName(groundName)
                    .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));
        } else {
            throw new ApiException(ErrorStatus._INVALID_SEARCH_CRITERIA);
        }

        return new GroundSearchResponse(ground.getTeam().getId(), ground.getGroundName(), ground.getLocation(), ground.getTel(), ground.getGroundImg());
    }

    public void checkIfAdmin(User user) {
        if (!user.getUserRole().equals((UserRole.ROLE_ADMIN))) {
            throw new ApiException(ErrorStatus._NOT_ADMIN_USER);
        }
    }
}
