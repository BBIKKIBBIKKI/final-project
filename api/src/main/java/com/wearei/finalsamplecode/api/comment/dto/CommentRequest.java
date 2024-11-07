package com.wearei.finalsamplecode.api.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.wearei.finalsamplecode.api.comment.dto.CommentRequest.*;

public sealed interface CommentRequest permits Create, Search, Update {

    record Create(
            @NotNull Long boardId,
            @NotBlank String contents
    ) implements CommentRequest {}

    record Search(
            @NotNull Long boardId
    ) implements CommentRequest {}

    record Update(
            @NotNull Long boardId,
            @NotBlank String contents
    ) implements CommentRequest {}
}
