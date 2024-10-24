package com.wearei.finalsamplecode.domain.user.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.common.annotation.Auth;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.user.dto.request.UserDeleteRequest;
import com.wearei.finalsamplecode.domain.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserDeleteResponse;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원정보변경
    @PatchMapping("/users")
    public ApiResponse<UserUpdateResponse> userUpdate(
            @Auth AuthUser authUser,
            @RequestBody UserUpdateRequest userUpdateRequest){

        UserUpdateResponse updateUser = userService.userUpdate(authUser.getId(), userUpdateRequest);
        return ApiResponse.onSuccess(updateUser);
    }

    // 회원탈퇴
    @DeleteMapping("/users")
    public ApiResponse<UserDeleteResponse> deleteUser(
            @Auth AuthUser authUser,
            @RequestBody UserDeleteRequest userDeleteRequest){

        userService.deleteUser(authUser.getId(), userDeleteRequest.getPassword());
        return ApiResponse.onSuccess(new UserDeleteResponse("회원탈퇴 됐습니다."));
    }
}