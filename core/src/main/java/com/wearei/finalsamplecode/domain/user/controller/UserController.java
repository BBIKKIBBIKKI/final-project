package com.wearei.finalsamplecode.domain.user.controller;

import com.wearei.finalsamplecode.domain.user.dto.request.UserDeleteRequest;
import com.wearei.finalsamplecode.domain.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.domain.user.service.UserService;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원정보변경
    @PatchMapping("/users")
    public ApiResponse<UserUpdateResponse> userUpdate(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserUpdateRequest userUpdateRequest) {

        return ApiResponse.onSuccess(userService.userUpdate(authUser.getUserId(), userUpdateRequest));
    }

    // 회원탈퇴
    @DeleteMapping("/users")
    public ApiResponse<String> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserDeleteRequest userDeleteRequest) {

        userService.deleteUser(authUser.getUserId(), userDeleteRequest.getPassword());
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}