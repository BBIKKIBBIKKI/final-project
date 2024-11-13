package com.wearei.finalsamplecode.api.user.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.core.domain.user.service.DomainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService {
    private final DomainUserService domainUserService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User updatePassword(Long userId, String password, String newPassword) {
        var user = userRepository.findByIdOrThrow(userId);

        Preconditions.validate(passwordEncoder.matches(password, user.getPassword()), ErrorStatus._PASSWORD_MISMATCH);
        Preconditions.validate(!password.equals(newPassword), ErrorStatus._NOT_ALLOW_SAME_PASSWORD);

        return domainUserService.passwordUpdate(user, newPassword);
    }

    public void delete(Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));

        Preconditions.validate( passwordEncoder.matches(password, user.getPassword()), ErrorStatus._PASSWORD_MISMATCH);

        domainUserService.delete(user);
    }
}
