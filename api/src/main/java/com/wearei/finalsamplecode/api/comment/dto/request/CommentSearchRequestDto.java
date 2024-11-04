package com.wearei.finalsamplecode.api.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSearchRequestDto {
    private Long teamId;
    private Long boardId;
}