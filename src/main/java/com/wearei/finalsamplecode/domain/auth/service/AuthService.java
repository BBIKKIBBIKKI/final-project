package com.wearei.finalsamplecode.domain.auth.service;


import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.config.JwtUtil;
import com.wearei.finalsamplecode.config.PasswordEncoder;
import com.wearei.finalsamplecode.domain.auth.dto.request.SigninRequest;
import com.wearei.finalsamplecode.domain.auth.dto.request.SignupRequest;
import com.wearei.finalsamplecode.domain.auth.dto.response.SigninResponse;
import com.wearei.finalsamplecode.domain.auth.dto.response.SignupResponse;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        // 이메일 중복 체크 검증 로직 먼저 올리기
        if (userRepository.existsByEmail(signupRequest.getEmail())){
            throw new ApiException(ErrorStatus._BAD_REQUEST_EMAIL);
        }
        // 사용자 이름 중복 체크
        if (userRepository.existsByUsername(signupRequest.getName())){
            throw new ApiException(ErrorStatus._BAD_REQUEST_USER);
        }
        // 사용자가 입력한 역할을 enum으로 변환
        UserRole userRole = UserRole.of(signupRequest.getUserRole());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // 새로운 User 객체 생성
        User newUser = new User(
                signupRequest.getEmail(),
                signupRequest.getName(),
                encodedPassword, // 암호화된 비밀번호 저장
                userRole);

        // 유저를 DB에 저장
        User savedUser = userRepository.save(newUser);

        // 저장된 유저 정보를 바탕으로 JWT 토큰 생성
        String bearerToken = jwtUtil.createToken(savedUser.getUserId(), savedUser.getEmail(), savedUser.getUserRole());

        return new SignupResponse(
                savedUser.getUserId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getUserRole().name(),
                savedUser.getCreatedAt());
    }

    // 로그인
    @Transactional
    public SigninResponse signin(SigninRequest signinRequest) {
        // 이메일로 유저 찾기
        User user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(
                        ()-> new ApiException(ErrorStatus._BAD_FOUND_EMAIL));

        // 입력된 비밀번호가 저장된 비밀번호와 일치하는지 확인
        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())){
            throw new ApiException(ErrorStatus._BAD_REQUEST_PASSWORD);
        }

        // 유저 정보를 바탕으로 JWT 토큰 생성
        String bearerToken = jwtUtil.createToken(
                user.getUserId(),
                user.getEmail(),
                user.getUserRole());

        // 응답 DTO에 토큰 담아서 반환
        return new SigninResponse(bearerToken);
    }
}