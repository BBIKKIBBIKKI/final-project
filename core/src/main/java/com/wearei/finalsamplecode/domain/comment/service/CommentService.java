package com.wearei.finalsamplecode.domain.comment.service;

import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.comment.dto.request.CommentCreateRequestDto;
import com.wearei.finalsamplecode.domain.comment.dto.request.CommentUpdateRequestDto;
import com.wearei.finalsamplecode.domain.comment.dto.response.CommentCreateResponseDto;
import com.wearei.finalsamplecode.domain.comment.dto.response.CommentSearchResponseDto;
import com.wearei.finalsamplecode.domain.comment.dto.response.CommentUpdateResponseDto;
import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.comment.ropository.CommentRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService{
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {
      Team team = teamRepository.findByTeamId(commentCreateRequestDto.getTeamId());

       Board board = boardRepository.findByBoardId(commentCreateRequestDto.getBoardId());

        Comment comment = new Comment(team, board,
                commentCreateRequestDto.getContents()
        );

        Comment createComment = commentRepository.save(comment);
        return new CommentCreateResponseDto(commentCreateRequestDto.getTeamId(),
                commentCreateRequestDto.getBoardId(),
                createComment.getId(),
                createComment.getContents(),
                createComment.getCreatedAt(),
                createComment.getModifiedAt());
    }

    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Team team = teamRepository.findByTeamId(commentUpdateRequestDto.getTeamId());

        Board board = boardRepository.findByBoardId(commentUpdateRequestDto.getBoardId());

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        comment.updateComment(team,board,
                commentUpdateRequestDto.getContents());

        commentRepository.save(comment);

        return new CommentUpdateResponseDto(commentUpdateRequestDto.getTeamId(),
                commentUpdateRequestDto.getBoardId(),
                comment.getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    public List<CommentSearchResponseDto> getComments(Long teamId, Long boardId) {
        teamRepository.findByTeamId(teamId);

        boardRepository.findByBoardId(boardId);

        return commentRepository.findByTeamIdAndBoardIdAndIsDeletedFalse(teamId,boardId).stream()
                .map(comment -> new CommentSearchResponseDto(teamId, boardId,
                comment.getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()))
                .collect(Collectors.toList());
    }

    public CommentSearchResponseDto getComment(Long teamId, Long boardId, Long commentId) {
        teamRepository.findByTeamId(teamId);

        boardRepository.findByBoardId(boardId);

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        return new CommentSearchResponseDto(teamId, boardId,
                comment.getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }

    public void deleteComment(Long teamId, Long boardId, Long commentId) {
        teamRepository.findByTeamId(teamId);

        boardRepository.findByBoardId(boardId);

        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_COMMENT));

        comment.Deleted();
        commentRepository.save(comment);
    }

}
