package com.wearei.finalsamplecode.domain.user.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.config.PasswordEncoder;
import com.wearei.finalsamplecode.domain.user.dto.request.UserUpdateRequest;
import com.wearei.finalsamplecode.domain.user.dto.resonse.UserUpdateResponse;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원정보변경
    public UserUpdateResponse userUpdate(Long userId, UserUpdateRequest userUpdateRequest) {

        User user = userRepository.findById(userId).
                orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_USER));

        // 비밀번호 확인
        validatePassword(userUpdateRequest.getPassword(), user.getPassword());

        // 사용자 정보 업데이트
        user.updateUser(
                userUpdateRequest.getName(),
                userUpdateRequest.getEmail(),
                passwordEncoder.encode(userUpdateRequest.getNewPassword()));

        // 응답 dto
        return new UserUpdateResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole().name());
    }

    // 회원 탈퇴
    public void deleteUser(Long userId, String password) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));

        // 비밀번호 확인
        validatePassword(password, user.getPassword());

        // 이미 탈퇴된 사용자일 경우
        if (user.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_USER);
        }

        // 사용자 삭제 처리
        user.markIsDeleted(); // 삭제 상태로 변경
        userRepository.save(user); // 변경된 상태 저장
    }

    // 공통 비밀번호 확인 메서드
    private void validatePassword(String Password, String newPassword) {
        if (!passwordEncoder.matches(Password, newPassword)) {
            throw new ApiException(ErrorStatus._PASSWORD_MISMATCH);
        }
    }
}