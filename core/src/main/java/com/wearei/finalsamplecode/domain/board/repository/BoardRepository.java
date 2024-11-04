package com.wearei.finalsamplecode.domain.board.repository;

import com.wearei.finalsamplecode.domain.board.entity.Board;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTeamId(Long teamId, Pageable pageable);

    default Board findByBoardId(Long boardId) {
        return findById(boardId).orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_BOARD));
    }
}