package com.wearei.finalsamplecode.domain.team.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
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
public class DomainTeamService {
    private final TeamRepository teamRepository;
    private final CustomUserDetailsService userDetailsService; // security permit
    private final S3Api s3Api;

    @Transactional
    public Team createTeam(AuthUser authUser, String teamName, String themeSong, MultipartFile uniformImg, MultipartFile mascotImg, MultipartFile equipmentImg) {
        User user = userDetailsService.findUserById(authUser.getUserId());

        userDetailsService.checkIfAdmin(user);

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
