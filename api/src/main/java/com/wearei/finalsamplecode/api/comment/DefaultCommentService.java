package com.wearei.finalsamplecode.api.comment;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.ropository.CommentRepository;
import com.wearei.finalsamplecode.common.exception.ApiException;
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
