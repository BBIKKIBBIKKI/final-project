package com.wearei.finalsamplecode.domain.user.repository;

import com.wearei.finalsamplecode.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
//    사용자 이메일 중복체크
    boolean existsByEmail(String email);
    // 사용자 이름 중복 체크
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
}
