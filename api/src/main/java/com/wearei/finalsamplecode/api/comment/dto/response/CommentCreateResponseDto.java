package com.wearei.finalsamplecode.api.comment.dto.response;

import java.time.LocalDateTime;
import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentCreateResponseDto {
    private final Long id;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentCreateResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}