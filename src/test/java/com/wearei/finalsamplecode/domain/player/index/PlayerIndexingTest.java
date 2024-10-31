package com.wearei.finalsamplecode.domain.player.index;

import com.wearei.finalsamplecode.domain.player.dto.response.PlayerSearchResponse;
import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.domain.player.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class PlayerIndexingTest {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    private static final int PLAYER_COUNT = 1000000; // 50만 명의 플레이어
    private static final String[] FAMILY_NAMES = {"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"};
    private static final String[] GIVEN_NAMES = {"민수", "지우", "서연", "준호", "수민", "예은", "현우", "유진", "지원", "재영"};
    private static final String[] TEAMS = {"KIA 타이거즈", "삼성 라이온즈", "LG 트윈스", "두산베어스",
            "KT 위즈", "SSG 랜더스", "롯데 자이언츠", "한화 이글스",
            "NC 다이노스", "키움 히어로즈"};

    @BeforeEach
    public void setUp() {
        insertRandomPlayers(PLAYER_COUNT);
    }

    private void insertRandomPlayers(int count) {
        List<Player> players = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String playerName = generateRandomName(random);
            String teamName = TEAMS[random.nextInt(TEAMS.length)]; // 랜덤으로 팀 선택

            Player player = new Player(
                    null,                          // playerId (null for auto-generation)
                    (long) (18 + random.nextInt(40)), // 임의의 나이 (18~57)
                    playerName,                    // 임의의 플레이어 이름
                    teamName,                      // 랜덤으로 선택된 팀 이름
                    "포지션" + (i % 5),              // 임의의 포지션 (5개 포지션)
                    "응원가" + (i % 5),              // 임의의 응원가
                    "체력 상태",                     // 임의의 체력 상태
                    (long) (random.nextInt(10000)) // 임의의 팔로워 수 (0~9999)
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

    private String generateRandomName(Random random) {
        // 성과 이름을 랜덤으로 선택
        String familyName = FAMILY_NAMES[random.nextInt(FAMILY_NAMES.length)];
        String givenName = GIVEN_NAMES[random.nextInt(GIVEN_NAMES.length)];
        return familyName + givenName;
    }

    @Test
    public void testGetPlayerByName() {
        // Given
        String playerName = "박서연";

        // When
        List<PlayerSearchResponse> players = playerService.getPlayerByNameAndTeamName(playerName, null);

        // Then
        assertFalse(players.isEmpty(), "선수 정보가 조회되어야 합니다.");
        assertEquals(playerName, players.get(0).getPlayerName(), "조회된 선수 이름이 일치해야 합니다.");
        assertEquals("KIA 타이거즈", players.get(0).getTeamName(), "조회된 팀 이름이 일치해야 합니다.");
    }
}