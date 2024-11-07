package com.wearei.finalsamplecode.api.auth;

import com.wearei.finalsamplecode.api.auth.dto.request.SigninRequest;
import com.wearei.finalsamplecode.api.auth.dto.request.SignupRequest;
import com.wearei.finalsamplecode.api.auth.dto.response.SigninResponse;
import com.wearei.finalsamplecode.api.auth.dto.response.SignupResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    // 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<SignupResponse> signup(
            @Valid
            @RequestBody SignupRequest request) {
        var user = authService.signup(
          request.getEmail(), request.getName(), request.getPassword(), request.getUserRole()
        );
        return ApiResponse.onSuccess(new SignupResponse(user));
    }

    // 로그인
    @PostMapping("/auth/sign-in")
    public ApiResponse<SigninResponse> signin(
            @Valid
            @RequestBody SigninRequest request) {
        var token = authService.signin(request.getEmail(), request.getPassword());
        return ApiResponse.onSuccess(new SigninResponse(token));
    }
}
