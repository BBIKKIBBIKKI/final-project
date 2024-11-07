package com.wearei.finalsamplecode.api.comment.dto;

import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.wearei.finalsamplecode.api.comment.dto.CommentResponse.*;

public sealed interface CommentResponse permits Create, Detail, Search, Update {
    record Create(
            Long id,
            String contents,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) implements CommentResponse {
        public Create(Comment comment){
            this(
                    comment.getId(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
        }
    }

    record Detail(
            @NotNull Long id,
            String contents,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) implements CommentResponse {
        public Detail(Comment comment){
            this(
                    comment.getId(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
        }
    }

    record Search(
            Long id,
            String contents,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) implements CommentResponse {
        public Search(Comment comment){
            this(
                    comment.getId(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
        }
    }

    record Update(Long boardId,
                  String contents,
                  LocalDateTime createdAt,
                  LocalDateTime modifiedAt
    ) implements CommentResponse {
        public Update(Comment comment){
            this(
                    comment.getId(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
        }
    }
}
