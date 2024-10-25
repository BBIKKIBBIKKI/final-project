package com.wearei.finalsamplecode.domain.team.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.domain.team.service.TeamService;
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
            @RequestPart TeamCreateRequest request,
            @RequestPart(required = false) MultipartFile uniformImg,
            @RequestPart(required = false) MultipartFile mascotImg,
            @RequestPart(required = false) MultipartFile equipmentImg
    ) throws IOException {
        return ApiResponse.onSuccess(teamService.createTeam(request, authUser,  uniformImg,  mascotImg, equipmentImg));
    }

    @GetMapping("/search")
    public ApiResponse<TeamSearchResponse> searchTeam(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(required = false, name="teamName") String teamName
    ) {
       return ApiResponse.onSuccess(teamService.searchTeam(authUser, teamName));
    }
}
