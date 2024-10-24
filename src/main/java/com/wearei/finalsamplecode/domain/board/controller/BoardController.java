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
        BoardCreateResponseDto createBoard = boardService.createBoard(boardCreateRequestDto);
        return ApiResponse.onSuccess(createBoard);
    }

    @GetMapping()
    public ApiResponse<List<BoardSearchResponseDto>> getBoards(@RequestBody Long teamId) {
        List<BoardSearchResponseDto> getBoards = boardService.getBoards(teamId);
        return ApiResponse.onSuccess(getBoards);
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardSearchResponseDto> getBoard(@PathVariable Long boardId, @RequestBody Long teamId) {
        BoardSearchResponseDto getboard = boardService.getBoard(teamId, boardId);
        return ApiResponse.onSuccess(getboard);
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<BoardUpdateResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        BoardUpdateResponseDto boardupdateResponseDto = boardService.updateBoard(boardId, boardUpdateRequestDto);
        return ApiResponse.onSuccess(boardupdateResponseDto);
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