package com.wearei.finalsamplecode.api.team;

import com.wearei.finalsamplecode.api.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.api.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.api.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.core.domain.team.service.DomainTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamApi {
    private final DomainTeamService domainTeamService;
    private final DefaultTeamService defaultTeamService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ApiResponse<TeamCreateResponse> createTeam(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart TeamCreateRequest request,
            @RequestPart(required = false) MultipartFile uniformImg,
            @RequestPart(required = false) MultipartFile mascotImg,
            @RequestPart(required = false) MultipartFile equipmentImg
    ) {
        return ApiResponse.onSuccess(new TeamCreateResponse(domainTeamService.createTeam(authUser, request.getTeamName(), request.getThemeSong(), uniformImg,  mascotImg, equipmentImg)));
    }

    @GetMapping("/search")
    public ApiResponse<TeamSearchResponse> searchTeam(
            @RequestParam(required = false, name="teamName") String teamName
    ) {
       return ApiResponse.onSuccess(new TeamSearchResponse(defaultTeamService.findByTeamName(teamName)));
    }
}
