package com.wearei.finalsamplecode.domain.user.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.annotation.Auth;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.user.dto.request.UserDeleteRequest;
import com.wearei.finalsamplecode.domain.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserDeleteResponse;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.domain.user.service.UserService;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원정보변경
    @PatchMapping("/users")
    public ApiResponse<UserUpdateResponse> userUpdate(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserUpdateRequest userUpdateRequest){

        // userid가 null인지 확인
        if(authUser == null || authUser.getUserId() == null){
            throw new ApiException(ErrorStatus._NOT_FOUND_USER);
        }

        UserUpdateResponse updateUser = userService.userUpdate(authUser.getUserId(), userUpdateRequest);
        return ApiResponse.onSuccess(updateUser);
    }

    // 회원탈퇴
    @DeleteMapping("/users")
    public ApiResponse<UserDeleteResponse> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserDeleteRequest userDeleteRequest){

        // userId가 null인지 확인
        if(authUser == null || authUser.getUserId() == null){
            throw new ApiException(ErrorStatus._NOT_FOUND_USER);
        }

        userService.deleteUser(authUser.getUserId(), userDeleteRequest.getPassword());
        return ApiResponse.onSuccess(new UserDeleteResponse("회원탈퇴 됐습니다."));
    }
}