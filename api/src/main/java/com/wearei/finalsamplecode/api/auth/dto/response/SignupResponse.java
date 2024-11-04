package com.wearei.finalsamplecode.api.auth.dto.response;

import com.wearei.finalsamplecode.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SignupResponse {
    private final Long userId;
    private final String name;
    private final String email;
    private final String userRole;
    private final LocalDateTime CreatedAt;

    public SignupResponse(User user) {
        this.userId = user.getId();
        this.name = user.getUsername();
        this.email = user.getEmail();
        this.userRole = user.getUserRole().name();
        this.CreatedAt = user.getCreatedAt();
    }
}