package com.wearei.finalsamplecode.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "이름은 빈칸일 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 빈칸일 수 없습니다.")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "올바른 이메일 형식을 입력하세요.")
    private String email;

    @NotBlank(message = "현재 비밀번호는 빈칸일 수 없습니다.")
    private String password;

    @NotBlank(message = "새 비밀번호는 빈칸일 수 없습니다.")
    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
            message = "대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하여 최소 8글자 이상이어야 합니다.")
    private String newPassword;

}