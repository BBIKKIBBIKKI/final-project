package com.wearei.finalsamplecode.domain.comment.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {
    private final Long teamId;
    private final Long boardId;
    private final Long id;
    private final String contents;
    private final boolean isDeleted;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentCreateResponseDto(Long teamId, Long boardId, Long id, String contents, boolean isDeleted, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.teamId = teamId;
        this.boardId = boardId;
        this.id = id;
        this.contents = contents;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
