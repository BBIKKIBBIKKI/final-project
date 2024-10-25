package com.wearei.finalsamplecode.domain.auth;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.config.JwtUtil;
import com.wearei.finalsamplecode.config.PasswordEncoder;
import com.wearei.finalsamplecode.domain.auth.dto.request.SigninRequest;
import com.wearei.finalsamplecode.domain.auth.dto.request.SignupRequest;
import com.wearei.finalsamplecode.domain.auth.dto.response.SigninResponse;
import com.wearei.finalsamplecode.domain.auth.dto.response.SignupResponse;
import com.wearei.finalsamplecode.domain.auth.service.AuthService;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    private SignupRequest signupRequest;
    private SigninRequest signinRequest;

    @BeforeEach
    public void setUp() {
        // given
        // 회원가입과 로그인 요청 생성
        signupRequest = new SignupRequest("testUser", "test@example.com", "Test@123", "ROLE_USER");
        signinRequest = new SigninRequest("test@example.com", "Test@123");
    }

    @Test
    public void 회원가입_성공() {
        // when : 회원가입 성공
        SignupResponse signupResponse = authService.signup(signupRequest);
        // then : 회원가입 시 입력한 정보 확인
        assertNotNull(signupResponse);
        assertEquals(signupRequest.getEmail(), signupResponse.getEmail());
        assertEquals(signupRequest.getName(), signupResponse.getName());
        assertEquals(UserRole.ROLE_USER.name(), signupResponse.getUserRole());
    }

    @Test
    public void 회원가입_실패_이메일중복() {
        // given : 중복된 이메일로 사용자 추가
        userRepository.save(new User("test@example.com", "testUser", passwordEncoder.encode("Password@123"), UserRole.ROLE_USER));
        SignupRequest signupRequest = new SignupRequest("newUser","test@example.com","Password@123",UserRole.ROLE_USER.name());
        // when : 중복된 이메일로 회원가입 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signup(signupRequest);
        });
        // then : 예외발생 확인
        assertEquals("이미 존재하는 이메일입니다", exception.getErrorCode().getReasonHttpStatus().getMessage()); // 메시지 비교
    }

    @Test
    public void 회원가입_실패_사용자이름중복() {
        // given : 중복된 사용자 이름으로 사용자 추가
        userRepository.save(new User("other@example.com", "testUser", passwordEncoder.encode("Password@123"), UserRole.ROLE_USER));
        // when : 중복된 사용자 이름으로 회원가입 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signup(signupRequest);});
        // then : 예외발생 확인
        assertEquals(ErrorStatus._BAD_REQUEST_USER.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 로그인_성공() {
        // given : 회원가입
        authService.signup(signupRequest);
        // when : 로그인 성공
        SigninResponse signinResponse = authService.signin(signinRequest);
        // then : signinResponse, 토큰 null 확인
        assertNotNull(signinResponse);
        assertNotNull(signinResponse.getBearerToken());
    }

    @Test
    public void 로그인_실패_이메일없음() {
        // when : 로그인 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signin(signinRequest);});
        // then : 예외발생 확인
        assertEquals(ErrorStatus._BAD_FOUND_EMAIL.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 로그인_실패_비밀번호불일치() {
        // given : 회원가입
        authService.signup(signupRequest);
        // when : 비밀번호 불일치 로그인 시도
        SigninRequest wrongPasswordRequest = new SigninRequest(signupRequest.getEmail(), "WrongPassword");
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signin(wrongPasswordRequest);});
        // then : 예외발생 확인
        assertEquals(ErrorStatus._BAD_REQUEST_PASSWORD.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }
}