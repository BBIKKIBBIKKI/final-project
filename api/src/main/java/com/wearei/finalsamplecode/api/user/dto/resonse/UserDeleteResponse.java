package com.wearei.finalsamplecode.api.user.dto.resonse;

import lombok.Getter;

@Getter
public class UserDeleteResponse {
    private final String message;

    public UserDeleteResponse(String message) {
        this.message = message;
    }
}