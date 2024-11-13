package com.wearei.finalsamplecode.core.domain.user.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DomainUserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DomainUserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("testuser@example.com", "테스트유저", "password123", UserRole.ROLE_USER);
    }

    @Test
    void 비밀번호_정상_변경() {
        // given
        String newPassword = "newPassword123";

        // when
        User updatedUser = userService.passwordUpdate(user, newPassword);

        // then
        assertNotNull(updatedUser);
        assertEquals(newPassword, updatedUser.getPassword());
    }

    @Test
    void 사용자_정상_삭제() {
        // given
        assertFalse(user.isDeleted());

        // when
        userService.delete(user);

        // then
        assertTrue(user.isDeleted());
    }

    @Test
    void 이미_삭제된_사용자_삭제_예외처리() {
        // given
        user.markIsDeleted(); // 이미 삭제된 상태로 설정
        // when & then
        ApiException exception = assertThrows(ApiException.class, () -> userService.delete(user));
        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(), exception.getMessage());
    }
}
