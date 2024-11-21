package com.wearei.finalsamplecode.core.domain.board.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class DomainBoardServiceTest {

    private User user;

    private Team team;

    private Board board;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private S3Api s3Api;

    @InjectMocks
    private DomainBoardService domainBoardService;

    @BeforeEach
    void init() {
        user = new User(
                "duduio2050@gmail.com",
                "오현택",
                "qwer123",
                UserRole.ROLE_ADMIN
        );

        team = new Team(
                "삼성 라이온즈",
                "uniformImg",
                "mascotImg",
                "equipmentImg",
                "themeSong"
        );

        board = new Board(
                team,
                user,
                "삼성이 이기나?",
                "과연...",
                "noimage"
        );

        ReflectionTestUtils.setField(user, "id", 1L);
        ReflectionTestUtils.setField(team, "id", 1L);
        ReflectionTestUtils.setField(board, "id", 1L);
    }

    @Test
    void 게시판_생성_성공_테스트() throws IOException {

        Long userId = 1L;
        Long teamId = 1L;
        String title = "삼성이 이기나?";
        String content = "과연...";

        given(userRepository.findByIdOrThrow(anyLong())).willReturn(user);
        given(teamRepository.findByIdOrThrow(anyLong())).willReturn(team);

        MockMultipartFile backgroundImg = new MockMultipartFile(
                "backgroundImg",                      // 파라미터 이름
                "background.jpg",                      // 파일명
                "image/jpeg",                          // 파일 타입
                "test image content".getBytes()        // 파일 내용
        );

        given(s3Api.uploadImageToS3(backgroundImg)).willReturn("http:naver.com");
        given(boardRepository.save(any(Board.class))).willReturn(board);

        Board board = domainBoardService.createBoard(
                userId,
                teamId,
                title,
                content,
                backgroundImg
        );

        assertEquals(board.getTitle(), title);
    }


    @Test
    void 존재하지_않는_사용자_실패_테스트() {
        // Given
        Long userId = 999L;  // 존재하지 않는 사용자 ID
        Long teamId = 1L;
        String title = "삼성이 이기나?";
        String content = "과연...";

        given(userRepository.findByIdOrThrow(userId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_USER));

        // When & Then
        ApiException exception = assertThrows(ApiException.class, () -> {
            domainBoardService.createBoard(userId, teamId, title, content, null);
        });

        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(), exception.getMessage());
    }

    @Test
    void 존재하지_않는_팀_실패_테스트() {
        // Given
        Long userId = 1L;
        Long teamId = 999L;  // 존재하지 않는 팀 ID
        String title = "삼성이 이기나?";
        String content = "과연...";

        given(userRepository.findByIdOrThrow(userId)).willReturn(user);
        given(teamRepository.findByIdOrThrow(teamId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_TEAM));

        // When & Then
        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainBoardService.createBoard(userId, teamId, title, content,  null);
        });

        assertEquals(ErrorStatus._NOT_FOUND_TEAM.getMessage(), apiException.getMessage());
    }

    @Test
    void 이미지_업로드_실패_테스트() throws IOException {
        // Given
        Long userId = 1L;
        Long teamId = 1L;
        String title = "삼성이 이기나?";
        String content = "과연...";

        given(userRepository.findByIdOrThrow(userId)).willReturn(user);
        given(teamRepository.findByIdOrThrow(teamId)).willReturn(team);

        MockMultipartFile backgroundImg = new MockMultipartFile(
                "backgroundImg",
                "background.jpg",
                "image/jpeg",
                "test image content".getBytes()
        );

        given(s3Api.uploadImageToS3(backgroundImg)).willThrow(new IOException("Failed to upload image"));

        // When & Then
        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainBoardService.createBoard(userId, teamId, title, content, backgroundImg);
        });

        assertEquals(ErrorStatus._FILE_UPLOAD_ERROR.getMessage(), apiException.getMessage());
    }

    @Test
    void 게시판_업데이트_성공_테스트() throws IOException {
        Long userId = 1L;
        Long boardId = 1L;
        String newTitle = "업데이트된 제목";
        String newContents = "업데이트된 내용";

        MockMultipartFile newImage = new MockMultipartFile(
                "backgroundImg",
                "new-image.jpg",
                "image/jpeg",
                "새 이미지 내용".getBytes()
        );

        // Stubbing
        given(boardRepository.findByBoardId(boardId)).willReturn(board);
        given(s3Api.updateImageInS3(anyString(), eq(newImage))).willReturn("updated-image-url");
        given(boardRepository.save(any(Board.class))).willReturn(board);

        // When
        Board updatedBoard = domainBoardService.updateBoard(userId, boardId, newTitle, newContents, newImage);

        // Then
        assertEquals(newTitle, updatedBoard.getTitle());
        assertEquals(newContents, updatedBoard.getContents());
        assertEquals("updated-image-url", updatedBoard.getBackgroundImage());
    }

    @Test
    void 권한이_없어_업데이트_실패_테스트() {
        Long userId = 2L;  // 다른 사용자 ID
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainBoardService.updateBoard(userId, boardId, "제목", "내용", null);
        });

        assertEquals(ErrorStatus._NO_PERMISSION_BOARD_MODIFICATION.getMessage(), apiException.getMessage());
    }

    @Test
    void 파일_업로드_실패_테스트() throws IOException {
        Long userId = 1L;
        Long boardId = 1L;

        MockMultipartFile faultyImage = new MockMultipartFile(
                "backgroundImg",
                "faulty-image.jpg",
                "image/jpeg",
                "이미지 내용".getBytes()
        );

        given(boardRepository.findByBoardId(boardId)).willReturn(board);
        willThrow(IOException.class).given(s3Api).updateImageInS3(anyString(), eq(faultyImage));

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainBoardService.updateBoard(userId, boardId, "새 제목", "새 내용", faultyImage);
        });

        assertEquals(ErrorStatus._FILE_UPLOAD_ERROR.getMessage(), apiException.getMessage());

    }

    @Test
    void 제목_또는_내용_미입력시_기존값_유지_테스트() {
        Long userId = 1L;
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);
        given(boardRepository.save(any(Board.class))).willReturn(board);

        Board updatedBoard = domainBoardService.updateBoard(userId, boardId, "", "", null);

        assertEquals("삼성이 이기나?", updatedBoard.getTitle());
        assertEquals("과연...", updatedBoard.getContents());
        assertEquals("noimage", updatedBoard.getBackgroundImage());
    }

    @Test
    void 게시판_삭제_성공_테스트() throws IOException {
        Long userId = 1L;
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);
        willDoNothing().given(s3Api).deleteImageFromS3(board.getBackgroundImage());

        // When
        domainBoardService.deleteBoard(userId, boardId);

        // Then
        verify(s3Api).deleteImageFromS3(board.getBackgroundImage());
        verify(boardRepository).delete(board);
    }

    @Test
    void 권한이_없어_게시판_삭제_실패_테스트() {
        Long userId = 2L;  // 다른 사용자 ID
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);

        assertThrows(ApiException.class, () -> {
            domainBoardService.deleteBoard(userId, boardId);
        });
    }

    @Test
    void 파일_삭제_실패_예외_테스트() throws IOException {
        Long userId = 1L;
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);
        willThrow(IOException.class).given(s3Api).deleteImageFromS3(board.getBackgroundImage());

        assertThrows(ApiException.class, () -> {
            domainBoardService.deleteBoard(userId, boardId);
        });
    }

    @Test
    void 좋아요_증가_테스트() {
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);

        domainBoardService.increaseLike(boardId);

        assertEquals(1, board.getLikes());
    }

    @Test
    void 좋아요_증가_실패_테스트() {
        Long boardId = 2L;

        given(boardRepository.findByBoardId(boardId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        assertThrows(ApiException.class, ()-> {
            domainBoardService.increaseLike(boardId);
        });
    }

    @Test
    void 좋아요_감소_테스트() {
        Long boardId = 1L;
        board.increaseLike();  // 좋아요 수 1로 설정

        given(boardRepository.findByBoardId(boardId)).willReturn(board);

        domainBoardService.decreaseLike(boardId);

        assertEquals(0, board.getLikes());
    }

    @Test
    void 좋아요_감소_예외_테스트() {
        Long boardId = 1L;

        given(boardRepository.findByBoardId(boardId)).willReturn(board);

        assertThrows(ApiException.class, () -> {
            domainBoardService.decreaseLike(boardId);
        });
    }
}
