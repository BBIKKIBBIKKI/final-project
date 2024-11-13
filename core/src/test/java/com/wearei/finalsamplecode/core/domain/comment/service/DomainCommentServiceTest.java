package com.wearei.finalsamplecode.core.domain.comment.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.ropository.CommentRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class DomainCommentServiceTest {

    private User user;

    private Team team;

    private Board board;

    private Comment comment;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private DomainCommentService domainCommentService;

    @BeforeEach
    void init() {
        user = new User(
                "duduio2050@gmail.com",
                "오현택",
                "qwer123",
                UserRole.ROLE_ADMIN
        );

        board = new Board(
                team,
                user,
                "삼성이 이기나?",
                "과연...",
                "noimage"
        );

        comment = new Comment(
                user,
                board,
                "그럴거같냐?"
        );

        ReflectionTestUtils.setField(user, "id", 1L);
        ReflectionTestUtils.setField(board, "id", 1L);
        ReflectionTestUtils.setField(comment, "id", 1L);
    }

    @Test
    void 댓글_생성_성공_테스트() {

        Long userId = 1L;
        Long boardId = 1L;
        String content = "그럴거같냐?";

        given(userRepository.findByIdOrThrow(anyLong())).willReturn(user);
        given(boardRepository.findByBoardId(anyLong())).willReturn(board);

        given(commentRepository.save(any(Comment.class))).willReturn(comment);

        Comment comment = domainCommentService.createComment(
                userId,
                boardId,
                content
        );

        assertEquals(comment.getContents(), content);
    }


    @Test
    void 존재하지_않는_사용자_실패_테스트() {
        // Given
        Long userId = 999L;  // 존재하지 않는 사용자 ID
        Long boardId = 1L;
        String content = "그럴거같냐?";

        given(userRepository.findByIdOrThrow(anyLong())).willThrow(new ApiException(ErrorStatus._NOT_FOUND_USER));

        // When & Then
        ApiException exception = assertThrows(ApiException.class, () -> {
            domainCommentService.createComment(userId, boardId, content);
        });

        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(), exception.getMessage());
    }

    @Test
    void 존재하지_않는_게시판_실패_테스트() {
        // Given
        Long userId = 1L;
        Long boardId = 999L;  // 존재하지 않는 팀 ID
        String content = "그럴거같냐?";

        given(userRepository.findByIdOrThrow(anyLong())).willReturn(user);
        given(boardRepository.findByBoardId(anyLong())).willThrow(new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        // When & Then
        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainCommentService.createComment(userId, boardId, content);
        });

        assertEquals(ErrorStatus._NOT_FOUND_BOARD.getMessage(), apiException.getMessage());
    }

    @Test
    void 댓글_업데이트_성공_테스트() {
        Long userId = 1L;
        Long commentId = 1L;
        String newContents = "미안";

        // Stubbing
        given(commentRepository.findByCommentIdOrThrow(anyLong())).willReturn(comment);

        // When
        Comment updatedComment = domainCommentService.updateComment(userId, commentId, newContents);

        // Then
        assertEquals(newContents, updatedComment.getContents());
    }

    @Test
    void 존재하지_않는_댓글_실패_테스트() {
        Long userId = 1L;  // 다른 사용자 ID
        Long commentId = 999L;
        String newContents = "미안";

        given(commentRepository.findByCommentIdOrThrow(commentId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainCommentService.updateComment(userId, commentId, newContents);
        });

        assertEquals(ErrorStatus._NOT_FOUND_COMMENT.getMessage(), apiException.getMessage());
    }

    @Test
    void 권한이_없어_업데이트_실패_테스트() {
        Long userId =999L;  // 다른 사용자 ID
        Long commentId = 1L;
        String newContents = "미안";

        given(commentRepository.findByCommentIdOrThrow(anyLong())).willReturn(comment);

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainCommentService.updateComment(userId, commentId, newContents);
        });

        assertEquals(ErrorStatus._NO_PERMISSION_COMMENT_MODIFICATION.getMessage(), apiException.getMessage());
    }

    @Test
    void 댓글_삭제_성공_테스트() {
        Long userId = 1L;  // 다른 사용자 ID
        Long commentId = 1L;

        given(commentRepository.findByCommentIdOrThrow(commentId)).willReturn(comment);

        domainCommentService.deleteComment(userId, commentId);

        assertTrue(comment.isDeleted());

    }

    @Test
    void 댓글이_존재하지_않아_실패_테스트() {
        Long userId = 1L;  // 다른 사용자 ID
        Long commentId = 1L;

        given(commentRepository.findByCommentIdOrThrow(commentId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainCommentService.deleteComment(userId, commentId);
        });

        assertEquals(ErrorStatus._NOT_FOUND_COMMENT.getMessage(), apiException.getMessage());
    }

    @Test
    void 댓글이_권한이_없어_실패_테스트() {
        Long userId = 2L;  // 다른 사용자 ID
        Long commentId = 1L;

        given(commentRepository.findByCommentIdOrThrow(commentId)).willReturn(comment);

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainCommentService.deleteComment(userId, commentId);
        });

        assertEquals(ErrorStatus._NO_PERMISSION_COMMENT_MODIFICATION.getMessage(), apiException.getMessage());
    }
}
