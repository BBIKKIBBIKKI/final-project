package com.wearei.finalsamplecode.core.domain.board.entity;

import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.common.entity.Timestamped;
import java.util.List;
import java.util.Objects;

import com.wearei.finalsamplecode.core.domain.user.entity.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(Team team, User user, String title, String contents, String backgroundImg) {
        this.team = team;
        this.user = user;
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

    public boolean isSameBoardUserId(Long userId) {
        return Objects.equals(this.user.getId(), userId);
    }

    public boolean isNotSameBoardUserId(Long userId) {
        return !isSameBoardUserId(userId);
    }
}