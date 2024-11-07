package com.wearei.finalsamplecode.api.board;

import com.wearei.finalsamplecode.api.board.dto.request.BoardCreateRequestDto;
import com.wearei.finalsamplecode.api.board.dto.request.BoardUpdateRequestDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardCreateResponseDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardSearchDetailResponseDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardSearchResponseDto;
import com.wearei.finalsamplecode.api.board.dto.response.BoardUpdateResponseDto;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.service.DomainBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardApi {
    private final DomainBoardService domainBoardService;
    private final DefaultBoardService defaultBoardService;

    @PostMapping()
    public ApiResponse<BoardCreateResponseDto> createBoard(@RequestPart BoardCreateRequestDto request,
                                                           @RequestPart(required = false) MultipartFile backgroundImg
    ) {
        return ApiResponse.onSuccess(new BoardCreateResponseDto(domainBoardService.createBoard(request.getTeamId(), request.getTitle(), request.getContents(), backgroundImg)));
    }

    @GetMapping()
    public ApiResponse<Page<BoardSearchResponseDto>> getBoards(@RequestParam Long teamId,
                                                               @RequestParam(defaultValue = "0")int page,
                                                               @RequestParam(defaultValue = "10")int size
    ) {
        Page<Board> boards = defaultBoardService.getBoards(teamId, PageRequest.of(page,size));
        return ApiResponse.onSuccess(boards.map(BoardSearchResponseDto::new));
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardSearchDetailResponseDto> getBoard(@PathVariable Long boardId, @RequestParam Long teamId) {
        return ApiResponse.onSuccess(new BoardSearchDetailResponseDto(defaultBoardService.getBoard(teamId, boardId)));
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<BoardUpdateResponseDto> updateBoard(@PathVariable Long boardId,
                                                           @RequestPart BoardUpdateRequestDto request,
                                                           @RequestPart(required = false) MultipartFile backgroundImg
    ) {
        return ApiResponse.onSuccess(new BoardUpdateResponseDto(domainBoardService.updateBoard(boardId, request.getTeamId(), request.getTitle(), request.getContents(), backgroundImg)));
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<String> deleteBoard(@PathVariable Long boardId, @RequestParam Long teamId) {
        domainBoardService.deleteBoard(boardId, teamId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }

    @PostMapping("/{boardId}/likes")
    public ApiResponse<String> increaselikePost(@PathVariable Long boardId) {
        domainBoardService.increaseLike(boardId);
        return ApiResponse.onSuccess(SuccessStatus._LIKE_SUCCESS.getMessage());
    }

    @DeleteMapping("/{boardId}/likes")
    public ApiResponse<String> decreaselikePost(@PathVariable Long boardId) {
        domainBoardService.decreaseLike(boardId);
        return ApiResponse.onSuccess(SuccessStatus._LIKE_SUCCESS.getMessage());
    }
}