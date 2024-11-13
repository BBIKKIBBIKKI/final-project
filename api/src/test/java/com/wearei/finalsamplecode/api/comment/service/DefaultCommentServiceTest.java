package com.wearei.finalsamplecode.api.comment.service;

import com.wearei.finalsamplecode.api.board.DefaultBoardService;
import com.wearei.finalsamplecode.api.comment.DefaultCommentService;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.board.entity.Board;
import com.wearei.finalsamplecode.core.domain.board.repository.BoardRepository;
import com.wearei.finalsamplecode.core.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.core.domain.comment.ropository.CommentRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class DefaultCommentServiceTest {

    private Long userId;

    private Long teamId;

    private Long boardId;

    private Long commentId;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DefaultCommentService defaultCommentService;

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

        Board board = new Board(
                newTeam,
                newUser,
                "삼성 라이온즈 이번엔 우승각인가?",
                "이번시즌 삼성 라이온즈 이번엔 우승각인가?",
                "no image"
        );

        Board newBoard = boardRepository.save(board);
        boardId = newBoard.getId();

        for(int i = 0; i < 5; i++){
            Comment comment = new Comment(
                    newUser,
                    newBoard,
                    "그럴일 없다" + i
            );

            Comment newComment = commentRepository.save(comment);
            if(i == 4){
                commentId = newComment.getId();
            }

        }
    }

    @Test
    void 게시판아이디로_댓글_다건_조회() {

        List<Comment> comments = defaultCommentService.getComments(boardId);

        assertEquals(comments.size(), 5, "검색된 댓글 개수가 올바르지 않습니다.");

        // 각 게시글이 올바른 팀과 연결되어 있는지 확인합니다.
        comments.forEach(comment -> {
            assertEquals(boardId, comment.getBoard().getId(), "팀 ID가 일치하지 않습니다.");
            assertTrue(comment.getContents().startsWith("그럴일 없다"), "게시글 제목이 예상과 다릅니다.");
        });

    }

    @Test
    void 게시판아이디로_댓글_단건_조회() {

        Comment comment = defaultCommentService.getComment(commentId, boardId);

        assertEquals(comment.getContents(), "그럴일 없다4");
    }

}