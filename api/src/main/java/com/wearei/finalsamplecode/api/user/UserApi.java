package com.wearei.finalsamplecode.api.user;

import com.wearei.finalsamplecode.api.user.dto.UserRequest;
import com.wearei.finalsamplecode.api.user.dto.UserResponse;
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
    public ApiResponse<UserResponse.Update> userUpdate(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserRequest.Update request) {

        return ApiResponse.onSuccess(new UserResponse.Update(defaultUserService.updatePassword(authUser.getUserId(), request.password(), request.newPassword())));
    }

    // 회원탈퇴
    @DeleteMapping("/users")
    public ApiResponse<Void> deleteUser(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody UserRequest.Delete request) {

        defaultUserService.delete(authUser.getUserId(), request.password());
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}