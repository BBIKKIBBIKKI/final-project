package com.wearei.finalsamplecode.domain.team.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamUpdateRequest;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamUpdateResponse;
import com.wearei.finalsamplecode.domain.team.service.TeamService;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ApiResponse<TeamCreateResponse> createTeam(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody TeamCreateRequest request,
            @RequestPart(required = false) MultipartFile uniformImg,
            @RequestPart(required = false) MultipartFile mascotImg,
            @RequestPart(required = false) MultipartFile equipmentImg
    ) throws IOException {
        return ApiResponse.onSuccess(teamService.createTeam(request, authUser,  uniformImg,  mascotImg, equipmentImg));
    }

    @GetMapping("/search")
    public ApiResponse<TeamSearchResponse> searchTeam(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(required = false) String teamName
    ) {
       return ApiResponse.onSuccess(teamService.searchTeam(authUser, teamName));
    }

    @PatchMapping("/teams/{teamId}")
    public ApiResponse<TeamUpdateResponse> updateTeam(
            @PathVariable Long teamId,
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody TeamUpdateRequest request,
            @RequestPart(required = false) MultipartFile uniformImg,
            @RequestPart(required = false) MultipartFile mascotImg,
            @RequestPart(required = false) MultipartFile equipmentImg
    ) throws IOException {
        return ApiResponse.onSuccess(teamService.updateTeam(teamId, authUser, request, uniformImg, mascotImg, equipmentImg));
    }

    @DeleteMapping("/teams/{teamId}")
    public ApiResponse<String> deleteTeam(
            @PathVariable Long teamId,
            @AuthenticationPrincipal AuthUser authUser
    ) {
        try {
            teamService.deleteTeam(teamId, authUser);
            return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
        } catch (ApiException e) {
            return ApiResponse.onFailure(e.getErrorCode());
        } catch (Exception e) {
            return ApiResponse.onFailure(ErrorStatus._DELETION_FAILED);
        }
    }
}
