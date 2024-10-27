package com.wearei.finalsamplecode.domain.board.repository;

import com.wearei.finalsamplecode.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTeamIdAndIsDeletedFalse(Long teamId);
   Optional<Board> findByIdAndIsDeletedFalse(Long boardId);

}
