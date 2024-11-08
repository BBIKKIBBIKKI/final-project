package com.wearei.finalsamplecode.api.board.dto;

import com.wearei.finalsamplecode.api.board.dto.BoardRequest.Create;
import com.wearei.finalsamplecode.api.board.dto.BoardRequest.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public sealed interface BoardRequest permits Create, Update {
    record Create (
            @NotNull Long teamId,
            @NotBlank String title,
            @NotBlank String contents
    ) implements BoardRequest {}

    record Update (
            @NotNull Long teamId,
            @NotBlank String title,
            @NotBlank String contents
    ) implements BoardRequest {}
}
