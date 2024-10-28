package com.wearei.finalsamplecode.domain.follow.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.follow.dto.request.CreateFollowRequest;
import com.wearei.finalsamplecode.domain.follow.dto.response.CreateFollowResponse;
import com.wearei.finalsamplecode.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController{
    private final FollowService followService;

    @PostMapping("/asfollows")
    public ApiResponse<CreateFollowResponse> createFollow(@RequestBody CreateFollowRequest createFollowRequest){
        return ApiResponse.onSuccess(followService.createFollow(createFollowRequest));
    }
}
