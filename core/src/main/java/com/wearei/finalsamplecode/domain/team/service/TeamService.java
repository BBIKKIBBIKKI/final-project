package com.wearei.finalsamplecode.domain.team.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
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
public class TeamService {
    private final TeamRepository teamRepository;
    private final CustomUserDetailsService userDetailsService;
    private final S3Api s3Api;

    @Transactional
    public TeamCreateResponse createTeam(TeamCreateRequest request, AuthUser authUser, MultipartFile uniformImg, MultipartFile mascotImg, MultipartFile equipmentImg) {
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

        Team team = teamRepository.save(new Team(request.getTeamName(), uniformImageUrl, mascotImageUrl, equipmentImageUrl, request.getThemeSong()));

        return new TeamCreateResponse(team.getTeamName(), team.getUniformImg(), team.getMascotImg(), team.getUniformImg(), team.getEquipmentImg());
    }

    @Transactional(readOnly = true)
    public TeamSearchResponse searchTeam(String teamName) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));

        return new TeamSearchResponse(team.getTeamName(), team.getUniformImg(), team.getMascotImg(), team.getEquipmentImg(), team.getThemeSong());
    }
}