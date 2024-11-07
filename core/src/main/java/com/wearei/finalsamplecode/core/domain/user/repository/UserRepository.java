package com.wearei.finalsamplecode.core.domain.user.repository;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
//    사용자 이메일 중복체크
    boolean existsByEmail(String email);
    // 사용자 이름 중복 체크
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    default User findByIdOrThrow(Long userId) {
        return findById(userId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
