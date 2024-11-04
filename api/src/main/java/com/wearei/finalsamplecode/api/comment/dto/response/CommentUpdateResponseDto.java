package com.wearei.finalsamplecode.api.comment.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentUpdateResponseDto {
    private final Long teamId;
    private final Long boardId;
    private final Long id;
    private final String contents;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CommentUpdateResponseDto(Long teamId, Long boardId, Long id, String contents, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.teamId = teamId;
        this.boardId = boardId;
        this.id = id;
        this.contents = contents;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}