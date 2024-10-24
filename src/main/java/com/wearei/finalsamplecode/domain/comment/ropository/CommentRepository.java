package com.wearei.finalsamplecode.domain.comment.ropository;

import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTeamIdAndBoardId(Long teamId, Long boardId);
}
