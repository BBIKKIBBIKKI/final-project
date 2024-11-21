package com.wearei.finalsamplecode.api.auth.dto.response;

import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import java.time.LocalDateTime;
import static com.wearei.finalsamplecode.api.auth.dto.response.AuthResponse.*;

public sealed interface AuthResponse permits SignUp, SignIn, JoinResultDTO {

    record SignUp(
            Long userId,
            String name,
            String email,
            UserRole userRole,
            LocalDateTime createdAt
    ) implements AuthResponse {
        public SignUp(User user) {
            this (
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getUserRole(),
                    user.getCreatedAt()
            );
        }
    }

    record SignIn (
            String bearerToken
    ) implements AuthResponse {
        public SignIn(String bearerToken) {
            this.bearerToken = bearerToken;
        }
    }

    record JoinResultDTO (
            Long userId,
            LocalDateTime createdAt
    ) implements AuthResponse {
        public JoinResultDTO(User user) {
            this (
                    user.getId(),
                    user.getCreatedAt()
            );
        }
    }
}
