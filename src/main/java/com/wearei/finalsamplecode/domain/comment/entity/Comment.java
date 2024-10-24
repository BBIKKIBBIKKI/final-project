package com.wearei.finalsamplecode.domain.comment.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntityTimestamped;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
public class Comment extends BaseEntityTimestamped {

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String contents;

    private Boolean isDeleted = false;
}