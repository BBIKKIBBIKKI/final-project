package com.wearei.finalsamplecode.api.auth;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Test
    public void 회원가입_성공() {
        String email = "test@example.com";
        String password = "123123";
        String userName = "아아아";
        String userRole = "ROLE_ADMIN";

        // when : 회원가입 성공
        User user = authService.signup(email,userName,password,userRole);

        // then : 회원가입 시 입력한 정보 확인
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(userName, user.getUsername());
        assertTrue(passwordEncoder.matches(password,user.getPassword()));
        assertEquals(UserRole.ROLE_ADMIN, user.getUserRole());
    }

    @Test
    public void 회원가입_실패_이메일중복() {
        // given : 중복된 이메일로 사용자 추가
        User user = userRepository.save(new User("test@example.com", "testUser", passwordEncoder.encode("Password@123"), UserRole.ROLE_USER));

        // when : 중복된 이메일로 회원가입 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signup(user.getEmail(),user.getUsername(),user.getPassword(), user.getUserRole().toString());
        });

        // then : 예외발생 확인
        assertEquals(ErrorStatus._EMAIL_ALREADY_EXISTS.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage()); // 메시지 비교
    }

    @Test
    public void 회원가입_실패_사용자이름중복() {
        // given : 중복된 사용자 이름으로 사용자 추가
        User user = userRepository.save(new User("other@example.com", "testUser", passwordEncoder.encode("Password@123"), UserRole.ROLE_USER));

        // when : 중복된 사용자 이름으로 회원가입 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signup("user@email.com", user.getUsername(), user.getPassword(), user.getUserRole().toString());
        });

        // then : 예외발생 확인
        assertEquals(ErrorStatus._BAD_REQUEST_USER.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 로그인_성공() {
        // given : 회원가입
        String email = "test@example.com";
        String userName = "아아아";
        String password = "123123";
        String userRole = "ROLE_ADMIN";

        User user = authService.signup(email,userName,password,userRole);

        // when : 로그인 성공
        String token = authService.signin(user.getEmail(), password);

        // then : signinResponse, 토큰 null 확인
        assertNotNull(token);
    }

    @Test
    public void 로그인_실패_이메일없음() {
        User user = userRepository.save(new User("test@example.com", "아아아", "123123", UserRole.ROLE_ADMIN));

        // when : 로그인 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signin("other@example.com", user.getPassword());
        });

        // then : 예외발생 확인
        assertEquals(ErrorStatus._BAD_FOUND_EMAIL.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 로그인_실패_비밀번호불일치() {
        String email = "test@example.com";
        String userName = "아아아";
        String password = "123123";
        String userRole = "ROLE_ADMIN";

        User user = authService.signup(email,userName,password,userRole);

        // when : 비밀번호 불일치 로그인 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            authService.signin(user.getEmail(), "otherPassword");
        });
        // then : 예외발생 확인
        assertEquals(ErrorStatus._BAD_REQUEST_PASSWORD.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }
}
