package com.wearei.finalsamplecode.api.board.dto;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import jakarta.validation.constraints.NotNull;

import static com.wearei.finalsamplecode.api.board.dto.BoardResponse.*;
import java.time.LocalDateTime;

public sealed interface BoardResponse permits Create, Detail, Search, Update {
    record Create(
            @NotNull Long id,
            String title,
            String contents,
            String backgroundImage,
            int likes,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) implements BoardResponse {
        public Create(Board board) {
            this(
                    board.getId(),
                    board.getTitle(),
                    board.getContents(),
                    board.getBackgroundImage(),
                    board.getLikes(),
                    board.getCreatedAt(),
                    board.getModifiedAt()
            );
        }
    }

    record Detail(
            @NotNull Long id,
            String title,
            String contents,
            String backgroundImage,
            int likes,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) implements BoardResponse {
        public Detail(Board board) {
            this(
                    board.getId(),
                    board.getTitle(),
                    board.getContents(),
                    board.getBackgroundImage(),
                    board.getLikes(),
                    board.getCreatedAt(),
                    board.getModifiedAt()
            );
        }
    }

    record Search(
            @NotNull Long id,
            String title,
            String contents,
            String backgroundImage,
            int likes
    ) implements BoardResponse {
        public Search(Board board) {
            this(
                    board.getId(),
                    board.getTitle(),
                    board.getContents(),
                    board.getBackgroundImage(),
                    board.getLikes()
            );
        }
    }

    record Update(
            @NotNull Long id,
            String title,
            String contents,
            String backgroundImage,
            int likes,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) implements BoardResponse {
        public Update(Board board) {
            this(
                    board.getId(),
                    board.getTitle(),
                    board.getContents(),
                    board.getBackgroundImage(),
                    board.getLikes(),
                    board.getCreatedAt(),
                    board.getModifiedAt()
            );
        }
    }
}