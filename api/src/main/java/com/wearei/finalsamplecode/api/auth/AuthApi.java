package com.wearei.finalsamplecode.api.auth;

import com.wearei.finalsamplecode.api.auth.dto.request.AuthRequest;
import com.wearei.finalsamplecode.api.auth.dto.response.AuthResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    // 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<AuthResponse.SignUp> signup(
            @Valid
            @RequestBody AuthRequest.SignUp request) {
        var user = authService.signup(
          request.email(), request.name(), request.password(), request.userRole()
        );
        return ApiResponse.onSuccess(new AuthResponse.SignUp(user));
    }

    // 로그인
    @PostMapping("/auth/sign-in")
    public ApiResponse<AuthResponse.SignIn> signin(
            @Valid
            @RequestBody AuthRequest.SignIn request) {
        var token = authService.signin(request.email(), request.password());
        return ApiResponse.onSuccess(new AuthResponse.SignIn(token));
    }
}
