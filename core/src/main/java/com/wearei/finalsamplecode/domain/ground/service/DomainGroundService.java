package com.wearei.finalsamplecode.domain.ground.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
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

@Service
@RequiredArgsConstructor
public class DomainGroundService {
    private final TeamRepository teamRepository;
    private final GroundRepository groundRepository;
    private final CustomUserDetailsService userDetailsService;
    private final S3Api s3Api;

    @Transactional
    public Ground createGround(Long teamId, String groundName, String location, String tel, AuthUser authUser, MultipartFile groundImg) {
        User user = userDetailsService.findUserById(authUser.getUserId());

        userDetailsService.checkIfAdmin(user);

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
