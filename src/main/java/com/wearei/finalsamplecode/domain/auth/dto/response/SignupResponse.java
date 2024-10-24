package com.wearei.finalsamplecode.domain.auth.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignupResponse {

    private final Long userId;
    private final String name;
    private final String email;
    private final String userRole;
    private final LocalDateTime createdAt;

    public SignupResponse(Long userId, String name, String email, String userRole, LocalDateTime createdAt){

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
        this.createdAt = createdAt;
    }
}