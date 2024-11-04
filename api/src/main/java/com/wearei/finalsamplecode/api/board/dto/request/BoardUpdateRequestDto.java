package com.wearei.finalsamplecode.api.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDto {
    private Long teamId;
    private String title;
    private String contents;
}