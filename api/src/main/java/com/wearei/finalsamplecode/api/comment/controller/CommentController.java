package com.wearei.finalsamplecode.api.comment.controller;

import com.wearei.finalsamplecode.api.comment.dto.request.CommentCreateRequestDto;
import com.wearei.finalsamplecode.api.comment.dto.request.CommentSearchRequestDto;
import com.wearei.finalsamplecode.api.comment.dto.request.CommentUpdateRequestDto;
import com.wearei.finalsamplecode.api.comment.dto.response.CommentCreateResponseDto;
import com.wearei.finalsamplecode.api.comment.dto.response.CommentSearchResponseDto;
import com.wearei.finalsamplecode.api.comment.dto.response.CommentUpdateResponseDto;
import com.wearei.finalsamplecode.api.comment.service.CommentService;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public ApiResponse<CommentCreateResponseDto> createComment(@RequestBody CommentCreateRequestDto commentCreateRequestDto) {
        return ApiResponse.onSuccess(commentService.createComment(commentCreateRequestDto));
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<CommentUpdateResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return ApiResponse.onSuccess(commentService.updateComment(commentId,commentUpdateRequestDto));
    }

    @GetMapping()
    public ApiResponse<List<CommentSearchResponseDto>> getComments(@RequestBody CommentSearchRequestDto commentSearchRequestDto) {
        return ApiResponse.onSuccess(commentService.getComments(commentSearchRequestDto.getTeamId(), commentSearchRequestDto.getBoardId()));
    }

    @GetMapping("/{commentId}")
    public ApiResponse<CommentSearchResponseDto> getComment(@PathVariable Long commentId, @RequestBody CommentSearchRequestDto commentSearchRequestDto) {
        return ApiResponse.onSuccess(commentService.getComment(commentSearchRequestDto.getTeamId(),commentSearchRequestDto.getBoardId(),commentId));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId, @RequestParam Long teamId, Long boardId) {
        commentService.deleteComment(teamId,boardId,commentId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}