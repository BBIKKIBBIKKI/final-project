package com.wearei.finalsamplecode.core.domain.user.service;

import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainUserService {
    // 회원정보변경
    public User passwordUpdate(User user, String newPassword) {
        user.updatePassword(newPassword);

        return user;
    }

    // 회원 탈퇴
    public void delete(User user) {
        // 이미 탈퇴된 사용자일 경우
        if (user.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_USER);
        }

        // 사용자 삭제 처리
        user.markIsDeleted(); // 삭제 상태로 변경
    }
}