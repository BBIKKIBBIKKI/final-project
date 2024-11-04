package com.wearei.finalsamplecode.api.board.dto.response;

import com.wearei.finalsamplecode.api.comment.dto.response.CommentResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class BoardSearchDetailResponseDto {
    private final Long teamId;
    private final Long id;
    private final String title;
    private final String contents;
    private final String backgroundImage;
    private final int likes;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponseDto> comments;

    public BoardSearchDetailResponseDto(Long teamId, Long id, String title, String contents, String backgroundImage, int likes, LocalDateTime createAt, LocalDateTime modifiedAt, List<CommentResponseDto> comments) {
        this.teamId = teamId;
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.backgroundImage = backgroundImage;
        this.likes = likes;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}