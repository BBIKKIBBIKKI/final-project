package com.wearei.finalsamplecode.api.follow.controller;

import com.wearei.finalsamplecode.api.follow.dto.request.CreateFollowRequest;
import com.wearei.finalsamplecode.api.follow.dto.response.CreateFollowResponse;
import com.wearei.finalsamplecode.api.follow.service.FollowService;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/asfollows")
    public ApiResponse<CreateFollowResponse> createFollow(@RequestBody CreateFollowRequest createFollowRequest) {
        return ApiResponse.onSuccess(followService.createFollow(createFollowRequest));
    }
}
