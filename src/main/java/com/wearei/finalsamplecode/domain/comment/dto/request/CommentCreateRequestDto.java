package com.wearei.finalsamplecode.domain.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentCreateRequestDto {
    private Long teamId;
    private Long boardId;
    private String contents;
}
