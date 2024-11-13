package com.wearei.finalsamplecode.api.player;

import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class DefaultPlayerServiceTest {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DefaultPlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        Team team = teamRepository.save(new Team("두산베어스", "url1", "url2", "url3", "url4"));
        Player player = playerRepository.save(new Player(1L, 20L, "박서연", "두산베어스", "외야수", "https://youtube.com", "Normal", 123L));
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
        assertEquals("두산베어스", players.get(0).getTeamName(), "조회된 팀 이름이 일치해야 합니다.");
    }
}