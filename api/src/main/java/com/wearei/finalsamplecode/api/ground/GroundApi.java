package com.wearei.finalsamplecode.api.ground;

import com.wearei.finalsamplecode.api.ground.dto.GroundRequest;
import com.wearei.finalsamplecode.api.ground.dto.GroundResponse;
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
    public ApiResponse<GroundResponse.Create> createGround(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart GroundRequest.Create request,
            @RequestPart(required = false) MultipartFile groundImg
    ) {
        return ApiResponse.onSuccess(new GroundResponse.Create(domainGroundService.createGround(authUser.getUserId(), request.teamId(), request.groundName(), request.location(), request.tel(),  groundImg)));
    }

    @GetMapping("/search")
    public ApiResponse<GroundResponse.Search> searchGround(
            @RequestParam(required = false, name="teamName") String teamName,
            @RequestParam(required = false, name="groundName") String groundName
    ) {
        return ApiResponse.onSuccess(new GroundResponse.Search(defaultGroundService.searchGround(teamName, groundName)));
    }
}
