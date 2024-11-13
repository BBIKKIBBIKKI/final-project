package com.wearei.finalsamplecode.core.domain.board.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DomainBoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;
    private final S3Api s3Api;

    @Transactional
    public Board createBoard(Long userId, Long teamId, String title , String contents, MultipartFile backgroundImg) {
        User user = userRepository.findByIdOrThrow(userId);
        Team team = teamRepository.findByIdOrThrow(teamId);

        String groundImageUrl = null;

        if(!backgroundImg.isEmpty()) {
            try {
                groundImageUrl = s3Api.uploadImageToS3(backgroundImg);
            } catch (IOException e) {
                throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
            }
        }

        return boardRepository.save(
                new Board(
                        team,
                        user,
                        title,
                        contents,
                        groundImageUrl
                )
        );
    }


    @Transactional
    public Board updateBoard(Long userId, Long boardId, String title, String contents, MultipartFile backgroundImg) {
        Board board = boardRepository.findByBoardId(boardId);

        Preconditions.validate(board.isNotSameBoardUserId(userId), ErrorStatus._NO_PERMISSION_BOARD_MODIFICATION);

        String updatedTitle = title.isBlank() ? board.getTitle() : title;
        String updatedContents = contents.isBlank() ? board.getContents() : contents;
        String updatedBackgroundImageUrl = board.getBackgroundImage();

        if(!backgroundImg.isEmpty()){
            try {
                updatedBackgroundImageUrl = s3Api.updateImageInS3(updatedBackgroundImageUrl, backgroundImg);
            } catch (IOException e) {
                throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
            }
        }

        board.update(
                updatedTitle,
                updatedContents,
                updatedBackgroundImageUrl
        );

        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long userId, Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        Preconditions.validate(board.isNotSameBoardUserId(userId), ErrorStatus._NO_PERMISSION_BOARD_MODIFICATION);

        try {
            s3Api.deleteImageFromS3(board.getBackgroundImage());
        } catch (IOException e) {
            throw new ApiException(ErrorStatus._FILE_UPLOAD_ERROR);
        }

        boardRepository.delete(board);
    }

    @Transactional
    public void increaseLike(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        board.increaseLike();
    }

    @Transactional
    public void decreaseLike(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);

        Preconditions.validate(board.getLikes() > 0, ErrorStatus._LIKES_DONT_ZERO);

        board.decreaseLike();
    }
}
