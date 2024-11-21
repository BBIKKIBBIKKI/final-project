package com.wearei.finalsamplecode.core.domain.comment.ropository;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardIdAndIsDeletedFalse(Long boardId);

    default Comment findByCommentIdOrThrow(Long commentId) {
        return findById(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));
    }
}