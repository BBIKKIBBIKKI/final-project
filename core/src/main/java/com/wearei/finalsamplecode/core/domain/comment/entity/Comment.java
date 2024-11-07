package com.wearei.finalsamplecode.core.domain.comment.entity;

import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private boolean isDeleted = false;

    public Comment(Board board, String contents) {
        this.board = board;
        this.contents = contents;
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }

    public void Deleted() {
        this.isDeleted = true;
    }
}