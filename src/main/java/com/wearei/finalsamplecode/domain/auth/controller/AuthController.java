package com.wearei.finalsamplecode.domain.auth.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.auth.dto.request.SigninRequest;
import com.wearei.finalsamplecode.domain.auth.dto.request.SignupRequest;
import com.wearei.finalsamplecode.domain.auth.dto.response.SigninResponse;
import com.wearei.finalsamplecode.domain.auth.dto.response.SignupResponse;
import com.wearei.finalsamplecode.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<SignupResponse> signup(
            @Valid
            @RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = authService.signup(signupRequest);
        return ApiResponse.onSuccess(signupResponse);
    }

    // 로그인
    @PostMapping("/auth/sign-in")
    public ApiResponse<SigninResponse> signin(
            @Valid
            @RequestBody SigninRequest signinRequest) {
        SigninResponse signinResponse = authService.signin(signinRequest);
        return ApiResponse.onSuccess(signinResponse);
    }
}