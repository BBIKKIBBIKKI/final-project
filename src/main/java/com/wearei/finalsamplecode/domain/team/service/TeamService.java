package com.wearei.finalsamplecode.domain.team.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.config.S3ClientUtility;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamUpdateRequest;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamUpdateResponse;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final S3ClientUtility s3ClientUtility;

    public TeamCreateResponse createTeam(TeamCreateRequest request, AuthUser authUser, MultipartFile uniformImg, MultipartFile mascotImg, MultipartFile equipmentImg) throws IOException {
        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_USER));

        checkIfAdmin(user);

        String uniformImgUrl = uploadImgIfExists(uniformImg, "uniforms/");
        String mascotImgUrl = uploadImgIfExists(mascotImg, "mascots/");
        String equipmentImgUrl = uploadImgIfExists(equipmentImg, "equipments/");

        Team team = teamRepository.save(new Team(request.getTeamName(), uniformImgUrl, mascotImgUrl, equipmentImgUrl, request.getThemeSong()));

        return new TeamCreateResponse(team);
    }

    public TeamSearchResponse searchTeam(AuthUser authUser, String teamName) {

        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_TEAM));

        return new TeamSearchResponse(team);
    }

    public TeamUpdateResponse updateTeam(Long teamId, AuthUser authUser, TeamUpdateRequest request, MultipartFile uniformImg, MultipartFile mascotImg, MultipartFile equipmentImg) throws IOException {
        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_USER));

        checkIfAdmin(user);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_TEAM));

        String uniformImgUrl = updateAndUploadImg(team.getUniformImg(), uniformImg, "uniforms/");
        String mascotImgUrl = updateAndUploadImg(team.getMascotImg(), mascotImg, "mascots/");
        String equipmentImgUrl = updateAndUploadImg(team.getEquipmentImg(), equipmentImg, "equipments/");

        return new TeamUpdateResponse(request.getTeamName(),uniformImgUrl,mascotImgUrl, equipmentImgUrl, request.getThemeSong());
    }

    public void deleteTeam(Long teamId, AuthUser authUser) {
        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_USER));

        checkIfAdmin(user);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_TEAM));

        teamRepository.delete(team);
    }

    public void checkIfAdmin(User user) {
        if (!user.getUserRole().equals((UserRole.ROLE_ADMIN))) {
            throw new ApiException(ErrorStatus._NOT_ADMIN_USER);
        }
    }

    private String uploadImgIfExists(MultipartFile image, String directory) throws IOException {
        if (image != null && !image.isEmpty()) {
            return s3ClientUtility.uploadFile(directory, image);
        }
        return null;
    }

    private String updateAndUploadImg(String currentImageUrl, MultipartFile newImage, String directory) throws IOException {
        if (newImage != null && !newImage.isEmpty()) {
            if (currentImageUrl != null && !currentImageUrl.isEmpty()) {
                s3ClientUtility.deleteImageFromS3(currentImageUrl);
            }
            return s3ClientUtility.uploadFile(directory, newImage);
        }
        return currentImageUrl;
    }
}
