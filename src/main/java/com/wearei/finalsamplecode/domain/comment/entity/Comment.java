package com.wearei.finalsamplecode.domain.comment.entity;

import com.wearei.finalsamplecode.common.entity.Timestamped;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
public class Comment extends Timestamped{
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String contents;

    private boolean isDeleted = false;

    public Comment(Team team, Board board, String contents, boolean isDeleted) {
        this.team = team;
        this.board = board;
        this.contents = contents;
        this.isDeleted = isDeleted;
    }

    public void updateComment(Team team, Board board, String contents) {
        this.team = team;
        this.board = board;
        this.contents = contents;
    }
}