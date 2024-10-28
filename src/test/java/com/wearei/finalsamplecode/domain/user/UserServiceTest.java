package com.wearei.finalsamplecode.domain.user;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.config.PasswordEncoder;
import com.wearei.finalsamplecode.domain.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.domain.user.service.UserService;
import com.wearei.finalsamplecode.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private User existingUser;

    @BeforeEach
    public void setUp() {
        existingUser = userRepository.save(new User("existing@example.com", "ExistingUser", passwordEncoder.encode("Existing@123"), UserRole.ROLE_USER));
    }

    @Test
    public void 유저정보변경_성공() {
        // given : 유저 업데이트 요청 생성
        UserUpdateRequest updateRequest = new UserUpdateRequest("UpdatedUser", "existing@example.com", "Existing@123", "New@123");

        // when : 유저 정보 업데이트 성공
        UserUpdateResponse response = userService.userUpdate(existingUser.getId(), updateRequest);

        // then : 업데이트된 유저 정보 확인
        assertNotNull(response);
        assertEquals("UpdatedUser", response.getName());
        assertEquals("existing@example.com", response.getEmail());
        assertEquals(UserRole.ROLE_USER.name(), response.getUserRole());
    }

    @Test
    public void 유저정보변경_실패_비밀번호불일치() {
        // given : 잘못된 비밀번호로 유저 업데이트 요청 생성
        UserUpdateRequest updateRequest = new UserUpdateRequest("UpdatedUser", "existing@example.com", "WrongPassword", "New@123");

        // when : 비밀번호 불일치로 업데이트 실패
        ApiException exception = assertThrows(ApiException.class, () -> {
            userService.userUpdate(existingUser.getId(), updateRequest);});

        // then : 예외 메시지 확인
        assertEquals(ErrorStatus._PASSWORD_MISMATCH.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 유저삭제_성공() {
        // given : 유저 삭제 요청
        String password = "Existing@123";

        // when : 유저 삭제 성공
        userService.deleteUser(existingUser.getId(), password);

        // then : 삭제 상태 확인
        User deletedUser = userRepository.findById(existingUser.getId())
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
        assertTrue(deletedUser.isDeleted());
    }

    @Test
    public void 유저삭제_실패_비밀번호불일치() {
        // given : 잘못된 비밀번호로 유저 삭제 요청
        String wrongPassword = "WrongPassword";

        // when : 비밀번호 불일치로 삭제 실패
        ApiException exception = assertThrows(ApiException.class, () -> {
            userService.deleteUser(existingUser.getId(), wrongPassword);});

        // then : 예외 메시지 확인
        assertEquals(ErrorStatus._PASSWORD_MISMATCH.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }

    @Test
    public void 유저삭제_실패_이미삭제된유저() {
        // given : 이미 삭제된 유저
        existingUser.markIsDeleted();
        userRepository.save(existingUser);

        // when : 삭제된 유저 다시 삭제 시도
        ApiException exception = assertThrows(ApiException.class, () -> {
            userService.deleteUser(existingUser.getId(), "Existing@123");});

        // then : 예외 메시지 확인
        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(), exception.getErrorCode().getReasonHttpStatus().getMessage());
    }
}
