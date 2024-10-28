package com.wearei.finalsamplecode.domain.board.entity;

import com.wearei.finalsamplecode.common.entity.Timestamped;
import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
public class Board extends Timestamped {
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment;

    private String title;

    private String contents;

    @Column(name="background_img")
    private String backgroundImage;

    private int likes = 0;

    private boolean isDeleted = false;

    public Board(Team team, String title, String contents, String backgroundImg) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.backgroundImage = backgroundImg;
    }

    public void increaseLike(){
        this.likes++;
    }

    public void decreaseLike(){
        if(this.likes>0) {
            this.likes--;
        }
    }

    public void updateBoard(Team team,String title, String contents, String backgroundImg) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.backgroundImage = backgroundImg;
    }

    public void deleted(){
        this.isDeleted = true;
    }

    public void setTitle(String title) {
        if(title != null){
            this.title = title;
        }
    }

    public void setContents(String contents) {
        if(contents != null){
            this.contents = contents;
        }
    }
}