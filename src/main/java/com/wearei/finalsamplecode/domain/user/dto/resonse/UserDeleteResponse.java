package com.wearei.finalsamplecode.domain.user.dto.resonse;

import lombok.Getter;

@Getter
public class UserDeleteResponse {
    private final String message;

    public UserDeleteResponse(String message) {
        this.message = message;
    }
}