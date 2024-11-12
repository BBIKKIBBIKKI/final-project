package com.wearei.finalsamplecode.api.board.service;

import com.wearei.finalsamplecode.api.board.DefaultBoardService;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class DefaultBoardServiceTest {

    private Long userId;

    private Long teamId;

    private Long boardId;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DefaultBoardService defaultBoardService;

    @BeforeEach
    void init(){
        User user = new User(
                "duduio2050@gmail.com",
                "오현택",
                "qwer123",
                UserRole.ROLE_ADMIN
        );

        User newUser = userRepository.save(user);
        userId = newUser.getId();

        Team team = new Team(
                "삼성 라이온즈",
                "uniformImg",
                "mascotImg",
                "equipmentImg",
                "themeSong"
        );

        Team newTeam = teamRepository.save(team);
        teamId = newTeam.getId();

        for (int i = 0; i < 15; i++){
            Board board = new Board(
                    newTeam,
                    newUser,
                    "삼성 라이온즈 이번엔 우승각인가?" + i,
                    "이번시즌 삼성 라이온즈 이번엔 우승각인가?" + i,
                    "no image" + i
            );

            Board newBoard = boardRepository.save(board);
            if(i == 14){
                boardId = newBoard.getId();
            }
        }

    }

    @Test
    void 팀아이디로_게시판_다건_조회() {

        int pageSize = 10;

        Page<Board> boards = defaultBoardService.getBoards(teamId, PageRequest.of(0, pageSize));

        assertEquals(pageSize, boards.getContent().size(), "검색된 게시글의 개수가 올바르지 않습니다.");

        // 각 게시글이 올바른 팀과 연결되어 있는지 확인합니다.
        boards.getContent().forEach(board -> {
            assertEquals(teamId, board.getTeam().getId(), "팀 ID가 일치하지 않습니다.");
            assertTrue(board.getTitle().startsWith("삼성 라이온즈"), "게시글 제목이 예상과 다릅니다.");
        });

    }

    @Test
    void 팀아이디로_게시판_단건_조회() {

        Board boards = defaultBoardService.getBoard(teamId, boardId);

        assertEquals(boards.getTitle(), "삼성 라이온즈 이번엔 우승각인가?14");
        assertEquals(boards.getContents(), "이번시즌 삼성 라이온즈 이번엔 우승각인가?14");

    }

}