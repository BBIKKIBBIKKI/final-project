package com.wearei.finalsamplecode.domain.auth.service;

import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.config.JwtUtil;
import com.wearei.finalsamplecode.config.PasswordEncoder;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    public User signup(String email, String name, String password, String userRoleStr) {
        // 이메일 중복 체크 검증 로직 먼저 올리기
        if (userRepository.existsByEmail(email)) {
            throw new ApiException(ErrorStatus._EMAIL_ALREADY_EXISTS);
        }
        // 사용자 이름 중복 체크
        if (userRepository.existsByUsername(name)) {
            throw new ApiException(ErrorStatus._BAD_REQUEST_USER);
        }

        return userRepository.save(new User(
          email,
          name,
          passwordEncoder.encode(password),
          UserRole.of(userRoleStr)));
    }

    // 로그인
    public String signin(String email, String password) {
        // 이메일로 유저 찾기
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        ()-> new ApiException(ErrorStatus._BAD_FOUND_EMAIL));

        // 입력된 비밀번호가 저장된 비밀번호와 일치하는지 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException(ErrorStatus._BAD_REQUEST_PASSWORD);
        }


        // 응답 DTO에 토큰 담아서 반환
        return jwtUtil.createToken(
          user.getId(),
          user.getEmail(),
          user.getUserRole());
    }
}
