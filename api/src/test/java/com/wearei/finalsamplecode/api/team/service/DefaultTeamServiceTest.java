package com.wearei.finalsamplecode.api.team.service;

import com.wearei.finalsamplecode.api.team.DefaultTeamService;
import com.wearei.finalsamplecode.api.team.dto.TeamResponse;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class DefaultTeamServiceTest {

    @Autowired
    private DefaultTeamService defaultTeamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(new User(
                "testadmin123@example.com",
                "이재희",
                "2551321Aa!",
                UserRole.ROLE_ADMIN));
    }

    @Test
    void 팀_정상_검색() {
        // given
        teamRepository.save(new Team(
                "Kia Tigers",
                "uniformImgUrl",
                "mascotImgUrl",
                "equipmentImgUrl",
                "Eye of the Tiger"));
        // when
        TeamResponse.Search response = new TeamResponse.Search(defaultTeamService.findByTeamName("Kia Tigers"));
        // then
        assertNotNull(response);
        assertEquals("Kia Tigers", response.teamName());
        assertEquals("uniformImgUrl", response.uniformImg());
        assertEquals("mascotImgUrl", response.mascotImg());
        assertEquals("equipmentImgUrl", response.equipmentImg());
        assertEquals("Eye of the Tiger", response.themeSong());
    }

    @Test
    void 존재하지_않는_팀_검색_예외() {
        // given
        String nonExistentTeamName = "NonExistentTeam";
        // when, then
        assertThrows(ApiException.class, () -> defaultTeamService.findByTeamName(nonExistentTeamName));
    }
}