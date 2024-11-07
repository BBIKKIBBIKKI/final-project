package com.wearei.finalsamplecode.core.domain.team.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DomainTeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final S3Api s3Api;

    @Transactional
    public Team createTeam(Long userId, String teamName, String themeSong, MultipartFile uniformImg, MultipartFile mascotImg, MultipartFile equipmentImg) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_ADMIN)) {
            throw new ApiException(ErrorStatus._INVALID_USER_ROLE);
        }

        String uniformImageUrl = null;
        String mascotImageUrl = null;
        String equipmentImageUrl = null;

        try {
            uniformImageUrl = s3Api.uploadImageToS3(uniformImg);
            mascotImageUrl = s3Api.uploadImageToS3(mascotImg);
            equipmentImageUrl = s3Api.uploadImageToS3(equipmentImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        return teamRepository.save(new Team(teamName, uniformImageUrl, mascotImageUrl, equipmentImageUrl, themeSong));
    }
}
