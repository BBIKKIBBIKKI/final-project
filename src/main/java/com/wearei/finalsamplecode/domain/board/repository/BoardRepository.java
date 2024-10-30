package com.wearei.finalsamplecode.domain.board.repository;

import com.wearei.finalsamplecode.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTeamId(Long teamId);
    Page<Board> findByTeamId(Long teamId, Pageable pageable);
}