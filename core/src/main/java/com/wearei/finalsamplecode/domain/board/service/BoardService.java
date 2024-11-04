package com.wearei.finalsamplecode.domain.board.service;

import static java.util.stream.Collectors.toList;

import com.wearei.finalsamplecode.domain.board.dto.request.BoardCreateRequestDto;
import com.wearei.finalsamplecode.domain.board.dto.request.BoardUpdateRequestDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardCreateResponseDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardSearchDetailResponseDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardSearchResponseDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardUpdateResponseDto;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.comment.dto.response.CommentResponseDto;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;
    private final S3Api s3Api;

    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto, MultipartFile backgroundImg) {
        Team team = teamRepository.findByTeamId(boardCreateRequestDto.getTeamId());

        String groundImageUrl = null;
      
        try{
            groundImageUrl = s3Api.uploadImageToS3(backgroundImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        Board board = boardRepository.save(
                new Board(
                        team,
                        boardCreateRequestDto.getTitle(),
                        boardCreateRequestDto.getContents(),
                        groundImageUrl)
        );

        return new BoardCreateResponseDto(boardCreateRequestDto.getTeamId(),
                board.getId(),
                board.getTitle(),
                board.getContents(),
                groundImageUrl,
                board.getLikes(),
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }

    public Page<BoardSearchResponseDto> getBoards(Long teamId, Pageable pageable) {
        teamRepository.findByTeamId(teamId);

        Page<Board> boardPage = boardRepository.findByTeamId(teamId, pageable);

        if (boardPage.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0); // 빈 리스트와 함께 페이지 정보 반환
        }

        return boardPage.map(board -> new BoardSearchResponseDto(
                teamId,
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getBackgroundImage(),
                board.getLikes(),
                board.getCreatedAt(),
                board.getModifiedAt()
        ));
    }

    public BoardSearchDetailResponseDto getBoard(Long teamId, Long boardId) {
        teamRepository.findByTeamId(teamId);

        Board board = boardRepository.findByBoardId(boardId);

        List<CommentResponseDto> comments = board.getComment().stream().map(
                        comment -> new CommentResponseDto(comment.getId(), comment.getContents()))
                .collect(toList());

        return new BoardSearchDetailResponseDto(
                teamId,
                board.getId(),
                board.getTitle(),
                board.getContents(),
                board.getBackgroundImage(),
                board.getLikes(),
                board.getCreatedAt(),
                board.getModifiedAt(),
                comments
        );
    }

    @Transactional
    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto, MultipartFile backgroundImg) {
        Team team = teamRepository.findByTeamId(boardUpdateRequestDto.getTeamId());

        Board board = boardRepository.findByBoardId(boardId);

        String title = board.getTitle();
        String contents = board.getContents();
        String groundImageUrl = board.getBackgroundImage();

        if (!Objects.isNull(title)) {
            title = boardUpdateRequestDto.getTitle();
        }

        if (!Objects.isNull(contents)) {
            contents = boardUpdateRequestDto.getContents();
        }

        if (!Objects.isNull(backgroundImg)) {
            try {
                groundImageUrl = s3Api.updateImageInS3(groundImageUrl, backgroundImg);
            } catch (IOException e) {
                throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
            }
        }

        board.update(team,
                title,
                contents,
                groundImageUrl
        );

        boardRepository.save(board);

        return new BoardUpdateResponseDto(boardUpdateRequestDto.getTeamId(),
                board.getId(),
                board.getTitle(),
                board.getContents(),
                groundImageUrl,
                board.getLikes(),
                board.getCreatedAt(),
                board.getModifiedAt());
    }

    @Transactional
    public void deleteBoard(Long boardId, Long teamId) {
        teamRepository.findByTeamId(teamId);

        Board board = boardRepository.findByBoardId(boardId);

        s3Api.deleteImageFromS3(board.getBackgroundImage());

        boardRepository.delete(board);
    }

    @Transactional
    public void increaseLike(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        board.increaseLike();

        boardRepository.save(board);
    }

    @Transactional
    public void decreaseLike(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        if (board.getLikes() <= 0) {
            throw new ApiException(ErrorStatus._LIKES_DONT_ZERO);
        }

        board.decreaseLike();

        boardRepository.save(board);
    }
}