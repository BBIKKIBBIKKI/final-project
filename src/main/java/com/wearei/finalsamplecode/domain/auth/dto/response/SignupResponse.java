package com.wearei.finalsamplecode.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    private final Long userId;
    private final String name;
    private final String email;
    private final String userRole;

    public SignupResponse(Long userId, String name, String email, String userRole){

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }
}