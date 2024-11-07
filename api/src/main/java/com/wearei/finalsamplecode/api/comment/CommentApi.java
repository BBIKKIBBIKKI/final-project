package com.wearei.finalsamplecode.api.comment;

import com.wearei.finalsamplecode.api.comment.dto.CommentRequest;
import com.wearei.finalsamplecode.api.comment.dto.CommentResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.service.DomainCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi {
    private final DomainCommentService domainCommentService;
    private final DefaultCommentService defaultCommentService;

    @PostMapping
    public ApiResponse<CommentResponse.Create> createComment(@Valid @RequestBody CommentRequest.Create request) {
        return ApiResponse.onSuccess(new CommentResponse.Create(domainCommentService.createComment(request.boardId(), request.contents())));
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<CommentResponse.Update> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest.Update request) {
        return ApiResponse.onSuccess(new CommentResponse.Update(domainCommentService.updateComment(commentId, request.boardId(), request.contents())));
    }

    @GetMapping
    public ApiResponse<List<CommentResponse.Search>> getComments(@RequestBody CommentRequest.Search request) {
        List<Comment> comments = defaultCommentService.getComments(request.boardId());
        return ApiResponse.onSuccess(comments.stream().map(CommentResponse.Search::new).toList());
    }

    @GetMapping("/{commentId}")
    public ApiResponse<CommentResponse.Detail> getComment(@PathVariable Long commentId, @RequestBody CommentRequest.Search request) {
        return ApiResponse.onSuccess(new CommentResponse.Detail(defaultCommentService.getComment(commentId, request.boardId())));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId, @RequestParam Long teamId, Long boardId) {
        domainCommentService.deleteComment(teamId,boardId,commentId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}