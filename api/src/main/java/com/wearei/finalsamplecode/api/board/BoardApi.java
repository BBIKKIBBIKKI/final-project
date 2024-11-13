package com.wearei.finalsamplecode.api.board;

import com.wearei.finalsamplecode.api.board.dto.BoardRequest;
import com.wearei.finalsamplecode.api.board.dto.BoardResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.service.DomainBoardService;
import com.wearei.finalsamplecode.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardApi {
    private final DomainBoardService domainBoardService;
    private final DefaultBoardService defaultBoardService;

    @PostMapping
    public ApiResponse<BoardResponse.Create> createBoard(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestPart BoardRequest.Create request,
            @RequestPart(required = false) MultipartFile backgroundImg
    ) {
        return ApiResponse.onSuccess(new BoardResponse.Create(domainBoardService.createBoard(authUser.getUserId(), request.teamId(), request.title(), request.contents(), backgroundImg)));
    }

    @GetMapping
    public ApiResponse<Page<BoardResponse.Search>> getBoards(@RequestParam Long teamId,
                                                               @RequestParam(defaultValue = "0")int page,
                                                               @RequestParam(defaultValue = "10")int size
    ) {
        Page<Board> boards = defaultBoardService.getBoards(teamId, PageRequest.of(page,size));
        return ApiResponse.onSuccess(boards.map(BoardResponse.Search::new));
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponse.Detail> getBoard(@PathVariable Long boardId, @RequestParam Long teamId) {
        return ApiResponse.onSuccess(new BoardResponse.Detail(defaultBoardService.getBoard(teamId, boardId)));
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<BoardResponse.Update> updateBoard(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long boardId,
            @RequestPart BoardRequest.Update request,
            @RequestPart(required = false) MultipartFile backgroundImg
    ) {
        return ApiResponse.onSuccess(new BoardResponse.Update(domainBoardService.updateBoard(authUser.getUserId(), boardId, request.title(), request.contents(), backgroundImg)));
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<Void> deleteBoard(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long boardId) {
        domainBoardService.deleteBoard(authUser.getUserId(), boardId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }

    @PostMapping("/{boardId}/likes")
    public ApiResponse<Void> increaseLike(@PathVariable Long boardId) {
        domainBoardService.increaseLike(boardId);
        return ApiResponse.onSuccess(SuccessStatus._LIKE_SUCCESS.getMessage());
    }

    @DeleteMapping("/{boardId}/likes")
    public ApiResponse<Void> decreaseLike(@PathVariable Long boardId) {
        domainBoardService.decreaseLike(boardId);
        return ApiResponse.onSuccess(SuccessStatus._LIKE_SUCCESS.getMessage());
    }
}