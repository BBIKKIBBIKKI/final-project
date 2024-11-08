package com.wearei.finalsamplecode.api.user.dto;

import com.wearei.finalsamplecode.core.domain.user.entity.User;
import static com.wearei.finalsamplecode.api.user.dto.UserResponse.*;

import java.time.LocalDateTime;

public sealed interface UserResponse permits Update {
    record Update(
            Long userId,
            String name,
            String email,
            String userRole,
            LocalDateTime modifiedAt
    ) implements UserResponse {
        public Update(User user) {
            this(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getUserRole().toString(),
                    user.getModifiedAt()
            );
        }
    }
}
