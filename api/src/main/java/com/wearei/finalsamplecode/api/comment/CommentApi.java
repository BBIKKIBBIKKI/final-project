package com.wearei.finalsamplecode.api.comment;

import com.wearei.finalsamplecode.api.comment.dto.CommentRequest;
import com.wearei.finalsamplecode.api.comment.dto.CommentResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.service.DomainCommentService;
import com.wearei.finalsamplecode.security.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi {
    private final DomainCommentService domainCommentService;
    private final DefaultCommentService defaultCommentService;

    @PostMapping
    public ApiResponse<CommentResponse.Create> createComment(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody CommentRequest.Create request) {
        return ApiResponse.onSuccess(new CommentResponse.Create(domainCommentService.createComment(authUser.getUserId(), request.boardId(), request.contents())));
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<CommentResponse.Update> updateComment(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long commentId, @RequestBody CommentRequest.Update request) {
        return ApiResponse.onSuccess(new CommentResponse.Update(domainCommentService.updateComment(authUser.getUserId(), commentId, request.contents())));
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
    public ApiResponse<Void> deleteComment(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long commentId) {
        domainCommentService.deleteComment(authUser.getUserId(), commentId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}