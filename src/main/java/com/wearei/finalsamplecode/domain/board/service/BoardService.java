package com.wearei.finalsamplecode.domain.board.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.config.S3ClientUtility;
import com.wearei.finalsamplecode.domain.board.dto.request.BoardCreateRequestDto;
import com.wearei.finalsamplecode.domain.board.dto.request.BoardUpdateRequestDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardCreateResponseDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardSearchResponseDto;
import com.wearei.finalsamplecode.domain.board.dto.response.BoardUpdateResponseDto;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;
    private final S3ClientUtility s3ClientUtility;

    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto, MultipartFile backgroundImg) {
       Team team = findByTeamId(boardCreateRequestDto.getTeamId());

        String groundImageUrl = null;
        try{
            groundImageUrl = s3ClientUtility.uploadImageToS3(backgroundImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }
        Board board = boardRepository.save(new Board(team,
                boardCreateRequestDto.getTitle(),
                boardCreateRequestDto.getContents(),
                groundImageUrl));
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

    public List<BoardSearchResponseDto> getBoards(Long teamId) {
        findByTeamId(teamId);

        return boardRepository.findById(teamId).stream()
                .map(board -> new BoardSearchResponseDto(teamId,
                        board.getId(),
                        board.getContents(),
                        board.getBackgroundImage(),
                        board.getLikes(),
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
                board.getCreatedAt(),
                board.getModifiedAt());
    }

    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto, MultipartFile backgroundImg) {
        Team team = findByTeamId(boardUpdateRequestDto.getTeamId());

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        String groundImageUrl = board.getBackgroundImage();
        if(backgroundImg != null && backgroundImg.isEmpty()){
            try{
                groundImageUrl = s3ClientUtility.updateImageInS3(groundImageUrl, backgroundImg);
            } catch (IOException e){
                throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
            }
        }
        board.updateBoard(team,
                boardUpdateRequestDto.getTitle(),
                boardUpdateRequestDto.getContents(),
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

    public void deleteBoard(Long boardId, Long teamId) {
        findByTeamId(teamId);

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));

        s3ClientUtility.deleteImageFromS3(board.getBackgroundImage());

        board.Deleted();
        boardRepository.save(board);
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