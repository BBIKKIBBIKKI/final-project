package com.wearei.finalsamplecode.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDto {

    private Long teamId;
    private Long id;
    private String title;
    private String contents;
    private String backgroundImage;
    private int likes;
    private boolean isDeleted;
}
