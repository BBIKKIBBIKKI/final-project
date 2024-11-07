package com.wearei.finalsamplecode.core.domain.user.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {
    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }

    public void checkIfAdmin(User user) {
        if (!user.getUserRole().equals(UserRole.ROLE_ADMIN)) {
            throw new ApiException(ErrorStatus._NOT_ADMIN_USER);
        }
    }

    public void checkIfOwner(AuthUser authUser) {
        if (!authUser.getUserRole().equals(UserRole.ROLE_OWNER)){
            throw new ApiException(ErrorStatus._BAD_REQUEST_STORE);
        }
    }
}
