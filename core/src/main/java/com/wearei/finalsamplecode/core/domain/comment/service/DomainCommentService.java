package com.wearei.finalsamplecode.core.domain.comment.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.ropository.CommentRepository;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainCommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Comment createComment(Long userId, Long boardId, String contents) {
        User user = userRepository.findByIdOrThrow(userId);
        Board board = boardRepository.findByBoardId(boardId);

        return commentRepository.save(
                new Comment(
                        user,
                        board,
                        contents
                )
        );
    }

    @Transactional
    public Comment updateComment(Long userId, Long commentId, String contents) {
        Comment comment = commentRepository.findByCommentIdOrThrow(commentId);

        Preconditions.validate(!comment.isNotSameCommentUserId(userId), ErrorStatus._NO_PERMISSION_COMMENT_MODIFICATION);

        comment.updateComment(contents);

        return comment;
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findByCommentIdOrThrow(commentId);

        Preconditions.validate(!comment.isNotSameCommentUserId(userId), ErrorStatus._NO_PERMISSION_COMMENT_MODIFICATION);

        comment.Deleted();
        commentRepository.save(comment);
    }
}
