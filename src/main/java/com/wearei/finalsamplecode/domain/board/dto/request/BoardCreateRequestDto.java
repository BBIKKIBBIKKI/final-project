package com.wearei.finalsamplecode.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequestDto {
    private Long teamId;
    private String title;
    private String contents;
}
