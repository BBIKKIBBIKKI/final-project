package com.wearei.finalsamplecode.domain.board.entity;

import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.common.entity.Timestamped;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "boards")
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
public class Board extends Timestamped {
    private String title;

    private String contents;

    @Column(name = "background_img")
    private String backgroundImage;

    private int likes = 0;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment;

    public Board(Team team, String title, String contents, String backgroundImg) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.backgroundImage = backgroundImg;
    }

    public void increaseLike() {
        this.likes++;
    }

    public void decreaseLike() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    public void update(String title, String contents, String backgroundImage) {
        if (!Objects.isNull(title)) {
            this.title = title;
        }
        if (!Objects.isNull(contents)) {
            this.contents = contents;
        }
        if (!Objects.isNull(backgroundImage)) {
            this.backgroundImage = backgroundImage;
        }
    }
}