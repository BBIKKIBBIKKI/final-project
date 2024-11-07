package com.wearei.finalsamplecode.api.board.dto.response;

import java.time.LocalDateTime;

import com.wearei.finalsamplecode.domain.board.entity.Board;
import lombok.Getter;

    @Getter
    public class BoardSearchResponseDto {
        private final Long teamId;
        private final Long id;
        private final String title;
        private final String contents;
        private final String backgroundImage;
        private final int likes;
        private final LocalDateTime createAt;
        private final LocalDateTime modifiedAt;

        public BoardSearchResponseDto(Board board) {
            this.teamId = board.getTeam().getId();
            this.id = board.getId();
            this.title = board.getTitle();
            this.contents = board.getContents();
            this.backgroundImage = board.getBackgroundImage();
            this.likes = board.getLikes();
            this.createAt = board.getCreatedAt();
            this.modifiedAt = board.getModifiedAt();
        }

        public BoardSearchResponseDto(Long teamId, Long id, String title, String contents, String backgroundImage, int likes, LocalDateTime createAt, LocalDateTime modifiedAt) {
            this.teamId = teamId;
            this.id = id;
            this.title = title;
            this.contents = contents;
            this.backgroundImage = backgroundImage;
            this.likes = likes;
            this.createAt = createAt;
            this.modifiedAt = modifiedAt;
        }
    }
