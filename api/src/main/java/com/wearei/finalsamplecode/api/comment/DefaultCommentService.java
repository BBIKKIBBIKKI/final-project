package com.wearei.finalsamplecode.api.comment;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.comment.ropository.CommentRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public List<Comment> getComments(Long boardId) {
        boardRepository.findByBoardId(boardId);

        return commentRepository.findByBoardIdAndIsDeletedFalse(boardId);
    }

    public Comment getComment(Long commentId, Long boardId) {
        boardRepository.findByBoardId(boardId);

        return commentRepository.findById(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));
    }
}
