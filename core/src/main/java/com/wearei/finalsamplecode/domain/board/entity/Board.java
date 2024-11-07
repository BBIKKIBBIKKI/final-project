package com.wearei.finalsamplecode.domain.board.entity;

import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.common.entity.Timestamped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "boards")
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
public class Board extends Timestamped {
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment;

    private String title;

    private String contents;

    @Column(name = "background_img")
    private String backgroundImage;

    private int likes = 0;

    public Board(Team team, String title, String contents, String backgroundImg) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.backgroundImage = backgroundImg;
    }

    public Board(Team team, Long boardId, String title, String contents, String groundImageUrl, int likes, LocalDateTime createdAt, LocalDateTime modifiedAt) {
         this.team = team;
         this.id = boardId;
         this.title = title;
         this.contents = contents;
         this.backgroundImage = groundImageUrl;
         this.likes = likes;
    }

    public void increaseLike() {
        this.likes++;
    }

    public void decreaseLike() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    public void update(Team team, String title, String contents, String backgroundImage) {
        this.team = team;
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