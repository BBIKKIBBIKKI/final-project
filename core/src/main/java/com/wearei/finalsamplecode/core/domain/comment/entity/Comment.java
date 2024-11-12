package com.wearei.finalsamplecode.core.domain.comment.entity;

import com.wearei.finalsamplecode.common.entity.Timestamped;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
public class Comment extends Timestamped {
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isDeleted = false;

    public Comment(User user, Board board, String contents) {
        this.user = user;
        this.board = board;
        this.contents = contents;
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }

    public void Deleted() {
        this.isDeleted = true;
    }

    public boolean isSameCommentUserId(Long userId) {
        return Objects.equals(this.user.getId(), userId);
    }

    public boolean isNotSameCommentUserId(Long userId) {
        return !isSameCommentUserId(userId);
    }
}