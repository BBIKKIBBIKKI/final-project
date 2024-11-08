package com.wearei.finalsamplecode.core.domain.ground.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DomainGroundService {
    private final TeamRepository teamRepository;
    private final GroundRepository groundRepository;
    private final UserRepository userRepository;
    private final S3Api s3Api;

    @Transactional
    public Ground createGround(Long userId, Long teamId, String groundName, String location, String tel, MultipartFile groundImg) {
        User user = userRepository.findByIdOrThrow(userId);

        Preconditions.validate(!user.isNotSameRole(UserRole.ROLE_ADMIN), ErrorStatus._INVALID_USER_ROLE);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ApiException((ErrorStatus._NOT_FOUND_TEAM)));

        String groundImageUrl = null;
        try {
            groundImageUrl = s3Api.uploadImageToS3(groundImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        return groundRepository.save(new Ground(groundName, location, tel, groundImageUrl, team));
    }
}
