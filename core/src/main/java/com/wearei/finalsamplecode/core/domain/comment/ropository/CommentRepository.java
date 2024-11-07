package com.wearei.finalsamplecode.core.domain.comment.ropository;

import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardIdAndIsDeletedFalse(Long boardId);
    Optional<Comment> findByIdAndIsDeletedFalse(Long commentId);
}