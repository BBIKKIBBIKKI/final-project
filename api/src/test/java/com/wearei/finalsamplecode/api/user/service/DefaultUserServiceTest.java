//package com.wearei.finalsamplecode.api.user.service;
//
//import com.wearei.finalsamplecode.api.user.dto.UserRequest;
//import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
//import com.wearei.finalsamplecode.common.enums.UserRole;
//import com.wearei.finalsamplecode.common.exception.ApiException;
//import com.wearei.finalsamplecode.core.domain.user.entity.User;
//import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
//import com.wearei.finalsamplecode.core.domain.user.service.DomainUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@Transactional
//@SpringBootTest
//class DefaultUserServiceTest {
//
//    @Autowired
//    private DefaultUserService defaultUserService;
//    @Autowired
//    private DomainUserService domainUserService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    private User existingUser;
//
//    @BeforeEach
//    public void setUp() {
//        existingUser = userRepository.save(
//                new User(
//                        "existing@example.com",
//                        "ExistingUser",
//                        passwordEncoder.encode("Existing@123"),
//                        UserRole.ROLE_USER));
//    }
//
//    @Test
//    public void 유저정보변경_성공() {
//        // given : 유저 업데이트 요청 생성
//        UserRequest.Update updateRequest = new UserRequest.Update("Existing@123","Newpassword@123");
//
//        // when : 유저 정보 업데이트 성공
//        User updatedUser = defaultUserService.updatePassword(
//                existingUser.getId(),
//                updateRequest.password(),
//                updateRequest.newPassword());
//
//        // then : 업데이트된 유저 정보 확인
//        assertNotNull(updatedUser);
////        assertTrue(passwordEncoder.matches("Newpassword@123", passwordEncoder.encode(updatedUser.getPassword())));
//        assertTrue("Newpassword@123".equals(updatedUser.getPassword()));
//    }
//
//    @Test
//    public void 유저정보변경_실패_비밀번호불일치() {
//        // given
//        UserRequest.Update updateRequest = new UserRequest.Update("WrongPassword","Newpassword@123");
//        // when
//        ApiException exception = assertThrows(ApiException.class, () ->
//                defaultUserService.updatePassword(
//                        existingUser.getId(),
//                        updateRequest.password(),
//                        updateRequest.newPassword()));
//        // then
//        assertEquals(ErrorStatus._PASSWORD_MISMATCH.getMessage(), exception.getMessage());
//    }
//
//    @Test
//    public void 유저삭제_성공() {
//        // given
//        String password = "Existing@123";
//        // when
//        defaultUserService.delete(existingUser.getId(), password);
//        //then
//        User deletedUser = userRepository.findById(existingUser.getId()).
//                orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
//        assertTrue(deletedUser.isDeleted());
//    }
//
//    @Test
//    public void 유저삭제_실패_비밀번호불일치() {
//        // given
//        String wrongPassword = "WrongPassword";
//        // when
//        ApiException exception = assertThrows(ApiException.class, () ->
//                defaultUserService.delete(existingUser.getId(), wrongPassword));
//        // then
//        assertEquals(ErrorStatus._PASSWORD_MISMATCH.getMessage(),exception.getMessage());
//    }
//
//    @Test
//    public void 유저삭제_실패_이미삭제된유저() {
//        // given
//        domainUserService.delete(existingUser);
//        // when
//        ApiException exception = assertThrows(ApiException.class, () ->
//                defaultUserService.delete(existingUser.getId(), "Existing@123"));
//        // then
//        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(),exception.getMessage());
//    }
//}
