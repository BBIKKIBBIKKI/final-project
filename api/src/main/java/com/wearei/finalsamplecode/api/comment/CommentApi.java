package com.wearei.finalsamplecode.api.comment;

import com.wearei.finalsamplecode.api.comment.dto.request.CommentCreateRequestDto;
import com.wearei.finalsamplecode.api.comment.dto.request.CommentSearchRequestDto;
import com.wearei.finalsamplecode.api.comment.dto.request.CommentUpdateRequestDto;
import com.wearei.finalsamplecode.api.comment.dto.response.CommentCreateResponseDto;
import com.wearei.finalsamplecode.api.comment.dto.response.CommentSearchResponseDto;
import com.wearei.finalsamplecode.api.comment.dto.response.CommentUpdateResponseDto;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import java.util.List;
import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.comment.service.DomainCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi {
    private final DomainCommentService domainCommentService;
    private final DefaultCommentService defaultCommentService;

    @PostMapping()
    public ApiResponse<CommentCreateResponseDto> createComment(@RequestBody CommentCreateRequestDto request) {
        return ApiResponse.onSuccess(new CommentCreateResponseDto(domainCommentService.createComment(request.getBoardId(), request.getContents())));
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<CommentUpdateResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto request) {
        return ApiResponse.onSuccess(new CommentUpdateResponseDto(domainCommentService.updateComment(commentId, request.getBoardId(), request.getContents())));
    }

    @GetMapping()
    public ApiResponse<List<CommentSearchResponseDto>> getComments(@RequestBody CommentSearchRequestDto request) {
        List<Comment> comments = defaultCommentService.getComments(request.getBoardId());
        return ApiResponse.onSuccess(comments.stream().map(CommentSearchResponseDto::new).toList());
    }

    @GetMapping("/{commentId}")
    public ApiResponse<CommentSearchResponseDto> getComment(@PathVariable Long commentId, @RequestBody CommentSearchRequestDto request) {
        return ApiResponse.onSuccess(new CommentSearchResponseDto(defaultCommentService.getComment(commentId, request.getBoardId())));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId, @RequestParam Long teamId, Long boardId) {
        domainCommentService.deleteComment(teamId,boardId,commentId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}