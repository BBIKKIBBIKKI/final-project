package com.wearei.finalsamplecode.domain.team.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import com.wearei.finalsamplecode.config.S3ClientUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final S3ClientUtility s3ClientUtility;

    @Transactional
    public TeamCreateResponse createTeam(TeamCreateRequest request, AuthUser authUser, MultipartFile uniformImg, MultipartFile mascotImg, MultipartFile equipmentImg) {
        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));

        checkIfAdmin(user);

        String uniformImageUrl = null;
        String mascotImageUrl = null;
        String equipmentImageUrl = null;

        try {
            uniformImageUrl = s3ClientUtility.uploadImageToS3(uniformImg);
            mascotImageUrl = s3ClientUtility.uploadImageToS3(mascotImg);
            equipmentImageUrl = s3ClientUtility.uploadImageToS3(equipmentImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        Team team = teamRepository.save(new Team(request.getTeamName(), uniformImageUrl, mascotImageUrl, equipmentImageUrl, request.getThemeSong()));

        return new TeamCreateResponse(team.getTeamName(), team.getUniformImg(), team.getMascotImg(), team.getUniformImg(), team.getEquipmentImg());
    }

    public TeamSearchResponse searchTeam(AuthUser authUser, String teamName) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));

        return new TeamSearchResponse(team.getTeamName(), team.getUniformImg(), team.getMascotImg(), team.getEquipmentImg(), team.getThemeSong());
    }

    public void checkIfAdmin(User user) {
        if (!user.getUserRole().equals((UserRole.ROLE_ADMIN))) {
            throw new ApiException(ErrorStatus._NOT_ADMIN_USER);
        }
    }
}