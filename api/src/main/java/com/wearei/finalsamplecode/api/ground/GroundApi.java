package com.wearei.finalsamplecode.api.ground;

import com.wearei.finalsamplecode.api.ground.dto.request.GroundCreateRequest;
import com.wearei.finalsamplecode.api.ground.dto.response.GroundCreateResponse;
import com.wearei.finalsamplecode.api.ground.dto.response.GroundSearchResponse;
import com.wearei.finalsamplecode.api.ground.service.DefaultGroundService;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.security.AuthUser;
import com.wearei.finalsamplecode.core.domain.ground.service.DomainGroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/grounds")
@RequiredArgsConstructor
public class GroundApi {
    private final DefaultGroundService defaultGroundService;
    private final DomainGroundService domainGroundService;

    @PostMapping
    public ApiResponse<GroundCreateResponse> createGround(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart GroundCreateRequest request,
            @RequestPart(required = false) MultipartFile groundImg
    ) {
        return ApiResponse.onSuccess(new GroundCreateResponse(domainGroundService.createGround(authUser.getUserId(), request.getTeamId(), request.getGroundName(), request.getLocation(), request.getTel(),  groundImg)));
    }

    @GetMapping("/search")
    public ApiResponse<GroundSearchResponse> searchGround(
            @RequestParam(required = false, name="teamName") String teamName,
            @RequestParam(required = false, name="groundName") String groundName
    ) {
        return ApiResponse.onSuccess(new GroundSearchResponse(defaultGroundService.searchGround(teamName, groundName)));
    }
}
