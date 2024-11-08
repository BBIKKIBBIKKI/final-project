package com.wearei.finalsamplecode.core.domain.comment.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.ropository.CommentRepository;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainCommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    public Comment createComment(Long boardId, String contents) {
        Board board = boardRepository.findByBoardId(boardId);

        Comment comment = new Comment(board, contents);

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, Long boardId, String contents) {
        Board board = boardRepository.findByBoardId(boardId);

        // 수정
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        // checkMyComment

        // 자기가 쓴 댓글인지 확인
        comment.updateComment(contents);

        return comment;
    }

    public void deleteComment(Long teamId, Long boardId, Long commentId) {
        teamRepository.findByIdOrThrow(teamId);

        boardRepository.findByBoardId(boardId);

        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        comment.Deleted();
        commentRepository.save(comment);
    }

//    private Comment checkMyComment(Long teamId, Long boardId) {
//        Team team = teamRepository.findByTeamId(teamId);
//
//        Board board = boardRepository.findByBoardId(boardId);
//
//        // 수정
//        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));
//
//        // 코멘트가 내꺼인지 확인
//
//    }
}
