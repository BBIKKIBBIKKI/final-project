package com.wearei.finalsamplecode.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchRequestDto {
    private Long teamId;
    private Long id;
    private String title;
    private String contents;
    private String backgroundImage;
    private int likes;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
