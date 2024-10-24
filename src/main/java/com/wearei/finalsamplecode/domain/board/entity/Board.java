package com.wearei.finalsamplecode.domain.board.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntityTimestamped;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
public class Board extends BaseEntityTimestamped {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String title;

    private String contents;

    private String backgroundImage;

    private int likes;

    private Boolean isDeleted = false;

    public void increaseLike(){
        this.likes++;
    }

    public void decreaseLike(){
        if(this.likes>0) {
            this.likes--;
        }
    }
}