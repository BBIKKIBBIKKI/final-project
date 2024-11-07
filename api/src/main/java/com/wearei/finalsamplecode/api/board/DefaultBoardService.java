package com.wearei.finalsamplecode.api.board;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class DefaultBoardService {
    private final BoardRepository boardRepository;
    private final TeamRepository teamRepository;

    public Page<Board> getBoards(Long teamId, Pageable pageable) {
        teamRepository.findByTeamId(teamId);

        Page<Board> boardPage = boardRepository.findByTeamId(teamId, pageable);

        if (boardPage.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0); // 빈 리스트와 함께 페이지 정보 반환
        }
        return boardPage;
    }

    public Board getBoard(Long teamId, Long boardId) {
        return boardRepository.findByTeamAndBoardId(teamId, boardId)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_BOARD));
    }
}
