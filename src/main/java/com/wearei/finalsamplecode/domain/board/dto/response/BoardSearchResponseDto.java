package com.wearei.finalsamplecode.domain.board.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardSearchResponseDto {
    private final Long teamId;
    private final Long id;
    private final String contents;
    private final String backgroundImage;
    private final int likes;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public BoardSearchResponseDto(Long teamId, Long id, String contents, String backgroundImage, int likes, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.teamId = teamId;
        this.id = id;
        this.contents = contents;
        this.backgroundImage = backgroundImage;
        this.likes = likes;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
