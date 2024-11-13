package com.wearei.finalsamplecode.api.team;

import com.wearei.finalsamplecode.api.team.dto.TeamRequest;
import com.wearei.finalsamplecode.api.team.dto.TeamResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.security.AuthUser;
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

    @PostMapping
    public ApiResponse<TeamResponse.Create> createTeam(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart TeamRequest.Create request,
            @RequestPart(required = false) MultipartFile uniformImg,
            @RequestPart(required = false) MultipartFile mascotImg,
            @RequestPart(required = false) MultipartFile equipmentImg
    ) {
        return ApiResponse.onSuccess(new TeamResponse.Create(domainTeamService.createTeam(authUser.getUserId(), request.teamName(), request.themeSong(), uniformImg,  mascotImg, equipmentImg)));
    }

    @GetMapping("/search")
    public ApiResponse<TeamResponse.Search> searchTeam(
            @RequestParam(required = false, name="teamName") String teamName
    ) {
       return ApiResponse.onSuccess(new TeamResponse.Search(defaultTeamService.findByTeamName(teamName)));
    }
}
