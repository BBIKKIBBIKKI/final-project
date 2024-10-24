package com.wearei.finalsamplecode.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequestDto {
    private Long teamId;
    private String title;
    private String contents;
    private String backgroundImage;
    private int likes;
    private boolean isDeleted;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
