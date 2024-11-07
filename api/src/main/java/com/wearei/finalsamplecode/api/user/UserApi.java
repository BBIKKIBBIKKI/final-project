package com.wearei.finalsamplecode.api.user;

import com.wearei.finalsamplecode.api.user.dto.request.UserDeleteRequest;
import com.wearei.finalsamplecode.api.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.api.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.api.user.service.DefaultUserService;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {
    private final DefaultUserService defaultUserService;

    // 회원정보변경
    @PatchMapping("/users")
    public ApiResponse<UserUpdateResponse> userUpdate(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserUpdateRequest request) {

        return ApiResponse.onSuccess(new UserUpdateResponse(defaultUserService.updatePassword(authUser.getUserId(), request.getPassword(), request.getNewPassword())));
    }

    // 회원탈퇴
    @DeleteMapping("/users")
    public ApiResponse<String> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserDeleteRequest request) {

        defaultUserService.delete(authUser.getUserId(), request.getPassword());
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}