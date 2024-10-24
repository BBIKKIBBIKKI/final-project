package com.wearei.finalsamplecode.domain.ground.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.dto.request.GroundCreateRequest;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundCreateResponse;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundSearchResponse;
import com.wearei.finalsamplecode.domain.ground.service.GroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/grounds")
@RequiredArgsConstructor
public class GroundController {
    private final GroundService groundService;

    @PostMapping
    public ApiResponse<GroundCreateResponse> createGround(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart GroundCreateRequest request,
            @RequestPart(required = false) MultipartFile groundImg
    ) throws IOException {
        return ApiResponse.onSuccess(groundService.createGround(request, authUser,  groundImg));
    }

    @GetMapping("/search")
    public ApiResponse<GroundSearchResponse> searchGround(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(required = false, name="teamName") String teamName,
            @RequestParam(required = false, name="groundName") String groundName
    ) {
        return ApiResponse.onSuccess(groundService.searchGround(authUser, teamName, groundName));
    }
}
