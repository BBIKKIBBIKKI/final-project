package com.wearei.finalsamplecode.domain.board.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.board.dto.*;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private TeamRepository teamRepository;

    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto) {
        Team team = findByTeamId(boardCreateRequestDto.getTeamId());

        Board board = new Board(team,
                boardCreateRequestDto.getTitle(),
                boardCreateRequestDto.getContents(),
                boardCreateRequestDto.getBackgroundImage(),
                boardCreateRequestDto.getLikes(),
                boardCreateRequestDto.isDeleted()
        );

        Board createBoard = boardRepository.save(board);
        return new BoardCreateResponseDto(boardCreateRequestDto.getTeamId(),
                createBoard.getId(),
                createBoard.getTitle(),
                createBoard.getContents(),
                createBoard.getBackgroundImage(),
                createBoard.getLikes(),
                createBoard.isDeleted(),
                createBoard.getCreatedAt(),
                createBoard.getModifiedAt());
    }

    public List<BoardSearchResponseDto> getBoards(Long teamId) {
        findByTeamId(teamId);

        return boardRepository.findById(teamId).stream()
                .map(board -> new BoardSearchResponseDto(teamId,
                        board.getId(),
                        board.getContents(),
                        board.getBackgroundImage(),
                        board.getLikes(),
                        board.isDeleted(),
                        board.getCreatedAt(),
                        board.getModifiedAt()))
                .collect(Collectors.toList());
    }

    public BoardSearchResponseDto getBoard(Long teamId, Long boardId) {
        findByTeamId(teamId);

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        return new BoardSearchResponseDto(teamId,
                board.getId(),
                board.getContents(),
                board.getBackgroundImage(),
                board.getLikes(),
                board.isDeleted(),
                board.getCreatedAt(),
                board.getModifiedAt());
    }

    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Team team = findByTeamId(boardUpdateRequestDto.getTeamId());

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        board.updateBoard(team,
                boardUpdateRequestDto.getTitle(),
                boardUpdateRequestDto.getContents(),
                boardUpdateRequestDto.getBackgroundImage(),
                boardUpdateRequestDto.getLikes(),
                boardUpdateRequestDto.isDeleted()
        );

        boardRepository.save(board);

        return new BoardUpdateResponseDto(boardUpdateRequestDto.getTeamId(),
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getBackgroundImage(),
                board.getLikes(),
                board.isDeleted(),
                board.getCreatedAt(),
                board.getModifiedAt());
    }

    public void deleteBoard(Long boardId, Long teamId) {
        findByTeamId(teamId);

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        boardRepository.delete(board);
    }

    public void increaseLike(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));
        board.increaseLike();
        boardRepository.save(board);
    }

    public void decreaseLike(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_BOARD));
        board.decreaseLike();
        boardRepository.save(board);
    }

    private Team findByTeamId(Long Id) {
        return teamRepository.findById(Id).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }
}