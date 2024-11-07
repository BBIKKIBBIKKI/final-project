package com.wearei.finalsamplecode.core.domain.board.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DomainBoardService {
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;
    private final S3Api s3Api;

    @Transactional
    public Board createBoard(Long teamId, String title , String contents, MultipartFile backgroundImg) {
        Team team = teamRepository.findByTeamId(teamId);

        String groundImageUrl = null;

        try{
            groundImageUrl = s3Api.uploadImageToS3(backgroundImg);
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        return boardRepository.save(new Board(team, title, contents, groundImageUrl));
    }


    @Transactional
    public Board updateBoard(Long boardId, Long teamId, String title, String contents, MultipartFile backgroundImg) {
        Team team = teamRepository.findByTeamId(teamId);
        Board board = boardRepository.findByBoardId(boardId);

        String updatedTitle = title != null ? title : board.getTitle();
        String updatedContents = contents != null ? contents : board.getContents();
        String updatedBackgroundImageUrl = board.getBackgroundImage();

        if (!Objects.isNull(board.getBackgroundImage())) {
            try {
                updatedBackgroundImageUrl = s3Api.updateImageInS3(updatedBackgroundImageUrl, backgroundImg);
            } catch (IOException e) {
                throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
            }
        }

        board.update(updatedTitle, updatedContents, updatedBackgroundImageUrl);

        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long boardId, Long teamId) {
        Board board = boardRepository.findByTeamAndBoardId(teamId, boardId)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));

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
