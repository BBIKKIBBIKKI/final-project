package com.wearei.finalsamplecode.domain.comment.ropository;

import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTeamIdAndBoardIdAndIsDeletedFalse(Long teamId, Long boardId);
    Optional<Comment> findByIdAndIsDeletedFalse(Long commentId);
}