package com.wearei.finalsamplecode.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.wearei.finalsamplecode.api.user.dto.UserRequest.*;

public sealed interface UserRequest permits Delete, Update {
    record Delete(
            String password
    ) implements UserRequest{}

    record Update(
            @NotBlank(message = "현재 비밀번호는 빈칸일 수 없습니다.")
            @NotBlank String password,

            @NotBlank(message = "새 비밀번호는 빈칸일 수 없습니다.")
            @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
                    message = "대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하여 최소 8글자 이상이어야 합니다.")
            @NotBlank  String newPassword
    ) implements UserRequest {}

}
