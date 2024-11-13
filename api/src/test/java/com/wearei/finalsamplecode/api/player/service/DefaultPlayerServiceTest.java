package com.wearei.finalsamplecode.api.player.service;

import com.wearei.finalsamplecode.api.player.DefaultPlayerService;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@SpringBootTest
public class DefaultPlayerServiceTest {

    private Team team;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DefaultPlayerService defaultPlayerService;

    @Autowired
    private PlayerRepository playerRepository;

    private static final int PLAYER_COUNT = 10; // 인덱싱 테스트를 위해 100만명의 선수를 생성하는데 사용하였으나, 배포시 10명으로 수정
    private static final String[] FAMILY_NAMES = {"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"};
    private static final String[] GIVEN_NAMES = {"민수", "지우", "서연", "준호", "수민", "예은", "현우", "유진", "지원", "재영"};
    private static final String[] TEAMS = {"KIA 타이거즈", "삼성 라이온즈", "LG 트윈스", "두산베어스",
            "KT 위즈", "SSG 랜더스", "롯데 자이언츠", "한화 이글스",
            "NC 다이노스", "키움 히어로즈"};

    @BeforeEach
    public void indexingSetUp() {
        insertRandomPlayers(PLAYER_COUNT);

        team = new Team(
                "롯데 자이언트",
                "noimage",
                "noimage",
                "noimage",
                "noSong"

        );

    }

    private void insertRandomPlayers(int count) {
        List<Player> players = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String playerName = generateRandomName(random);
            String teamName = TEAMS[random.nextInt(TEAMS.length)]; // 랜덤으로 팀 선택

            Player player = new Player(
                    (long) (18 + random.nextInt(40)), // 임의의 나이 (18~57)
                    playerName,                    // 임의의 플레이어 이름
                    team,                      // 랜덤으로 선택된 팀 이름
                    "포지션" + (i % 5),              // 임의의 포지션 (5개 포지션)
                    "응원가" + (i % 5),              // 임의의 응원가
                    "체력 상태"                     // 임의의 체력 상태
//                    (long) (random.nextInt(10000)) // 임의의 팔로워 수 (0~9999)
            );
            players.add(player);

            // 1000개씩 한 번에 저장하여 성능 최적화
            if (players.size() == 1000) {
                playerRepository.saveAllAndFlush(players);
                players.clear();
                System.out.println(i+" players");
            }
        }

        // 남은 플레이어 데이터 저장
        if (!players.isEmpty()) {
            playerRepository.saveAll(players);
        }
    }

    @BeforeEach
    public void setUp() {
        Team team = teamRepository.save(new Team("두산베어스", "url1", "url2", "url3", "url4"));
        Player player = playerRepository.save(new Player(20L, "박서연", team, "외야수", "https://youtube.com", "Normal"));
    }

    @Test
    public void testGetPlayerByName() {
        // Given
        String playerName = "박서연";

        // When
        List<Player> players = defaultPlayerService.getPlayerByNameAndTeamName(playerName, null);

        // Then
        assertFalse(players.isEmpty(), "선수 정보가 조회되어야 합니다.");
        assertEquals(playerName, players.get(0).getPlayerName(), "조회된 선수 이름이 일치해야 합니다.");
        assertEquals("두산베어스", players.get(0).getTeam().getTeamName(), "조회된 팀 이름이 일치해야 합니다.");
    }

    private String generateRandomName(Random random) {
        // 성과 이름을 랜덤으로 선택
        String familyName = FAMILY_NAMES[random.nextInt(FAMILY_NAMES.length)];
        String givenName = GIVEN_NAMES[random.nextInt(GIVEN_NAMES.length)];
        return familyName + givenName;
    }
}
