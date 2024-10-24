package com.wearei.finalsamplecode.domain.board.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardCreateResponseDto {
    private final Long teamId;
    private final Long id;
    private final String title;
    private final String contents;
    private final String backgroundImage;
    private final int likes;
    private final boolean isDeleted;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public BoardCreateResponseDto(Long teamId, Long id, String title, String contents, String backgroundImage, int likes, boolean isDeleted, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.teamId = teamId;
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.backgroundImage = backgroundImage;
        this.likes = likes;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}