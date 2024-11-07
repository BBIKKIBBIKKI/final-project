package com.wearei.finalsamplecode.api.board.dto.response;

import com.wearei.finalsamplecode.api.comment.dto.response.CommentResponseDto;
import java.time.LocalDateTime;
import java.util.List;

import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import lombok.Getter;

import static java.util.stream.Collectors.toList;

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

    public BoardSearchDetailResponseDto(Board board) {
        this.teamId = board.getTeam().getId();
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.backgroundImage = board.getBackgroundImage();
        this.likes = board.getLikes();
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.comments = board.getComment().stream().map(
                        comment -> new CommentResponseDto(comment.getId(), comment.getContents()))
                        .collect(toList());
    }

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