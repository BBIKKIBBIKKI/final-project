package com.wearei.finalsamplecode.domain.comment.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentUpdateResponseDto {
    private final Long teamId;
    private final Long boardId;
    private final Long id;
    private final String contents;
    private final boolean isDeleted;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CommentUpdateResponseDto(Long teamId, Long boardId, Long id, String contents, boolean isDeleted, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.teamId = teamId;
        this.boardId = boardId;
        this.id = id;
        this.contents = contents;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
