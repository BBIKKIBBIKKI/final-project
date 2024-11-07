package com.wearei.finalsamplecode.core.domain.board.repository;

import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTeamId(Long teamId, Pageable pageable);

    default Board findByBoardId(Long boardId) {
        return findById(boardId).orElseThrow(()-> new ApiException(ErrorStatus._NOT_FOUND_BOARD));
    }

    @Query("SELECT b FROM Board b WHERE b.team.id = :teamId AND b.id = :boardId")
    Optional<Board> findByTeamAndBoardId(@Param("teamId") Long teamId, @Param("boardId") Long boardId);


}