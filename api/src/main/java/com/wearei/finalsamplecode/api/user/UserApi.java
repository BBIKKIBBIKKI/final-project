package com.wearei.finalsamplecode.api.user;

import com.wearei.finalsamplecode.api.user.dto.request.UserDeleteRequest;
import com.wearei.finalsamplecode.api.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.api.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.user.service.DomainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {
    private final DomainUserService domainUserService;

    // 회원정보변경
    @PatchMapping("/users")
    public ApiResponse<UserUpdateResponse> userUpdate(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserUpdateRequest request) {

        return ApiResponse.onSuccess(new UserUpdateResponse(domainUserService.userUpdate(authUser.getUserId(), request.getName(), request.getEmail(), request.getPassword(), request.getNewPassword())));
    }

    // 회원탈퇴
    @DeleteMapping("/users")
    public ApiResponse<String> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserDeleteRequest request) {

        domainUserService.deleteUser(authUser.getUserId(), request.getPassword());
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}