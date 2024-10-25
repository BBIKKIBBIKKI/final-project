package com.wearei.finalsamplecode.domain.board.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.domain.board.dto.*;
import com.wearei.finalsamplecode.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping()
    public ApiResponse<BoardCreateResponseDto> createBoard(@RequestBody BoardCreateRequestDto boardCreateRequestDto) {
        return ApiResponse.onSuccess(boardService.createBoard(boardCreateRequestDto));
    }

    @GetMapping()
    public ApiResponse<List<BoardSearchResponseDto>> getBoards(@RequestBody Long teamId) {
        return ApiResponse.onSuccess(boardService.getBoards(teamId));
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardSearchResponseDto> getBoard(@PathVariable Long boardId, @RequestBody Long teamId) {
        return ApiResponse.onSuccess(boardService.getBoard(teamId, boardId));
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<BoardUpdateResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return ApiResponse.onSuccess(boardService.updateBoard(boardId, boardUpdateRequestDto));
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<String> deleteBoard(@PathVariable Long boardId, @RequestBody Long teamId) {
        boardService.deleteBoard(teamId, boardId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }

    @PostMapping("/{boardId}/likes")
    public ApiResponse<String> increaselikePost(@PathVariable Long boardId) {
        boardService.increaseLike(boardId);
        return ApiResponse.onSuccess(SuccessStatus._LIKE_SUCCESS.getMessage());
    }

    @DeleteMapping("/{boardId}/likes")
    public ApiResponse<String> decreaselikePost(@PathVariable Long boardId) {
        boardService.decreaseLike(boardId);
        return ApiResponse.onSuccess(SuccessStatus._LIKE_SUCCESS.getMessage());
    }
}