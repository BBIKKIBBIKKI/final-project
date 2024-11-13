package com.wearei.finalsamplecode.api.auth;

import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.security.JwtUtil;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        Preconditions.validate(!userRepository.existsByEmail(email), ErrorStatus._EMAIL_ALREADY_EXISTS);

        Preconditions.validate(!userRepository.existsByUsername(name), ErrorStatus._BAD_REQUEST_USER);

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
                .orElseThrow(()-> new ApiException(ErrorStatus._BAD_FOUND_EMAIL));

        System.out.println(password);
        System.out.println(passwordEncoder.encode(password));
        System.out.println(user.getPassword());

        Preconditions.validate(passwordEncoder.matches(password, user.getPassword()), ErrorStatus._BAD_REQUEST_PASSWORD);

        // 응답 DTO에 토큰 담아서 반환
        return jwtUtil.createToken(
          user.getId(),
          user.getEmail(),
          user.getUserRole());
    }
}
