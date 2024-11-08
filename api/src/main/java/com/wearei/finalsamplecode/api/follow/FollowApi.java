package com.wearei.finalsamplecode.api.follow;

import com.wearei.finalsamplecode.api.follow.dto.FollowRequest;
import com.wearei.finalsamplecode.api.follow.dto.FollowResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.core.domain.follow.service.DomainFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowApi {
    private final DomainFollowService domainFollowService;

    @PostMapping("/asfollows")
    public ApiResponse<FollowResponse.Create> createFollow(@RequestBody FollowRequest.Create request) {
        return ApiResponse.onSuccess(new FollowResponse.Create(domainFollowService.createFollow(request.userId(), request.playerId())));
    }
}
