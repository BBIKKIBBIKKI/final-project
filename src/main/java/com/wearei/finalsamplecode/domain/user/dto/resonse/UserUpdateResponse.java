package com.wearei.finalsamplecode.domain.user.dto.resonse;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UserUpdateResponse {

    private final Long userId;
    private final String name;
    private final String email;
    private final String userRole;
    private final LocalDateTime modifiedAt;

    public UserUpdateResponse(Long userId, String name, String email, String userRole, LocalDateTime modifiedAt){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
        this.modifiedAt = modifiedAt;
    }
}