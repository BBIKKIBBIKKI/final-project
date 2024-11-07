package com.wearei.finalsamplecode.api.follow.controller;

import com.wearei.finalsamplecode.api.follow.dto.request.CreateFollowRequest;
import com.wearei.finalsamplecode.api.follow.dto.response.CreateFollowResponse;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.follow.service.DomainFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowDomainController {
    private final DomainFollowService domainFollowService;

    @PostMapping("/asfollows")
    public ApiResponse<CreateFollowResponse> createFollow(@RequestBody CreateFollowRequest request) {
        return ApiResponse.onSuccess(new CreateFollowResponse(domainFollowService.createFollow(request.getUserId(), request.getPlayerId())));
    }
}
