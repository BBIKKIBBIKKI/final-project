package com.wearei.finalsamplecode.api.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import static com.wearei.finalsamplecode.api.auth.dto.request.AuthRequest.SignIn;
import static com.wearei.finalsamplecode.api.auth.dto.request.AuthRequest.SignUp;

public sealed interface AuthRequest permits SignUp, SignIn {

    record SignUp (
            @NotBlank(message = "빈칸은 입력이 불가능합니다.") String name,
            @NotBlank(message = "빈칸은 입력이 불가능합니다.") @Email(message = "이메일 형식을 맞춰주세요.") String email,
            @NotBlank(message = "빈칸은 입력이 불가능합니다.") @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
                    message = "대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하여 최소 8글자 이상이어야 합니다.") String password,
            @NotBlank(message = "빈칸은 입력이 불가능합니다.") String userRole
    ) implements AuthRequest {}

    record SignIn (
            @NotBlank(message = "빈칸은 입력이 불가능합니다.") @Email(message = "이메일 형식을 맞춰주세요.") String email,
            @NotBlank(message = "빈칸은 입력이 불가능합니다.") @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
                    message = "대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하여 최소 8글자 이상이어야 합니다.") String password
    ) implements AuthRequest {}
}
