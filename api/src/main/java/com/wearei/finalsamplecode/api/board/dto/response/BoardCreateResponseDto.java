package com.wearei.finalsamplecode.api.board.dto.response;

import java.time.LocalDateTime;

import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import lombok.Getter;

@Getter
public class BoardCreateResponseDto {
    private final Team team;
    private final Long id;
    private final String title;
    private final String contents;
    private final String backgroundImage;
    private final int likes;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public BoardCreateResponseDto(Board board) {
        this.team = board.getTeam();
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.backgroundImage = board.getBackgroundImage();
        this.likes = board.getLikes();
        this.createAt = board.getCreatedAt();
        this.modifiedAt =board.getModifiedAt();
    }
}