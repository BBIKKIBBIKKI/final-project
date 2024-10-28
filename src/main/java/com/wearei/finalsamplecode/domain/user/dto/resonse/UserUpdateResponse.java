package com.wearei.finalsamplecode.domain.user.dto.resonse;

import lombok.Getter;

@Getter
public class UserUpdateResponse {

    private final Long userId;
    private final String name;
    private final String email;
    private final String userRole;

    public UserUpdateResponse(Long userId, String name, String email, String userRole){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }
}