package com.wearei.finalsamplecode.api.board.controller;

import com.wearei.finalsamplecode.api.board.dto.request.BoardCreateRequestDto;
import com.wearei.finalsamplecode.api.board.dto.request.BoardUpdateRequestDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardCreateResponseDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardSearchDetailResponseDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardSearchResponseDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardUpdateResponseDto;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping()
    public ApiResponse<BoardCreateResponseDto> createBoard(@RequestPart BoardCreateRequestDto request,
                                                           @RequestPart(required = false) MultipartFile backgroundImg) {
        Board board = boardService.createBoard(request.getTeamId(), request.getTitle(), request.getContents(),backgroundImg);
        return ApiResponse.onSuccess(new BoardCreateResponseDto(board));
    }

    @GetMapping()
    public ApiResponse<Page<BoardSearchResponseDto>> getBoards(@RequestParam Long teamId,
                                                               @RequestParam(defaultValue = "0")int page,
                                                               @RequestParam(defaultValue = "10")int size) {
        Board board = boardService.getBoards(teamId, PageRequest.of(page,size));
        return ApiResponse.onSuccess(new BoardSearchResponseDto(board));
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardSearchDetailResponseDto> getBoard(@PathVariable Long boardId, @RequestParam Long teamId) {
        return ApiResponse.onSuccess(boardService.getBoard(teamId, boardId));
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<BoardUpdateResponseDto> updateBoard(@PathVariable Long boardId,
                                                           @RequestPart BoardUpdateRequestDto boardUpdateRequestDto,
                                                           @RequestPart(required = false) MultipartFile backgroundImg) {
        return ApiResponse.onSuccess(boardService.updateBoard(boardId, boardUpdateRequestDto,backgroundImg));
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<String> deleteBoard(@PathVariable Long boardId, @RequestParam Long teamId) {
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