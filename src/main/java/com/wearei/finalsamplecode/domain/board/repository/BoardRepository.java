package com.wearei.finalsamplecode.domain.board.repository;

import com.wearei.finalsamplecode.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
