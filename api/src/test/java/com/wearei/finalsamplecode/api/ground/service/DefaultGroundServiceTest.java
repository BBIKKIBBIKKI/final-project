package com.wearei.finalsamplecode.api.ground.service;

import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class DefaultGroundServiceTest {

    @Autowired
    private DefaultGroundService defaultGroundService;

    @Autowired
    private GroundRepository groundRepository;

    @Autowired
    private TeamRepository teamRepository;

    private Ground ground;

    @BeforeEach
    public void setUp() {
        Team team = teamRepository.save(new Team("두산베어스", "url1", "url2", "url3", "url4"));
        ground = groundRepository.save(new Ground("잠실주경기장", "서울", "02-1234-5678", "groundImageUrl", team));
    }

    @Test
    void 팀이름으로_구장_검색_정상_테스트() {
        Ground response = defaultGroundService.searchGround("두산베어스", null);

        assertEquals(ground.getGroundName(), response.getGroundName());
        assertEquals(ground.getLocation(), response.getLocation());
        assertEquals(ground.getTel(), response.getTel());
        assertEquals(ground.getGroundImg(), response.getGroundImg());
    }

    @Test
    void 구장이름으로_구장_검색_정상_테스트() {
        Ground response = defaultGroundService.searchGround(null, "잠실주경기장");

        assertEquals(ground.getGroundName(), response.getGroundName());
        assertEquals(ground.getLocation(), response.getLocation());
        assertEquals(ground.getTel(), response.getTel());
        assertEquals(ground.getGroundImg(), response.getGroundImg());
    }
}