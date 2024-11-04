package com.wearei.finalsamplecode.api.comment.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentCreateResponseDto {
    private final Long teamId;
    private final Long boardId;
    private final Long id;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentCreateResponseDto(Long teamId, Long boardId, Long id, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.teamId = teamId;
        this.boardId = boardId;
        this.id = id;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}