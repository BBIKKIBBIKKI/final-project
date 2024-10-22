package com.wearei.finalsamplecode.domain.user.enums;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;

import java.util.Arrays;

public enum UserRole {

    OWNER, USER, ADMIN;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorStatus._INVALID_USER_ROLE));
    }
}
