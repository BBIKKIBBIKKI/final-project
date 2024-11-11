package com.wearei.finalsamplecode.api.team.service;

import com.wearei.finalsamplecode.api.team.DefaultTeamService;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.security.AuthUser;
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
    private DefaultTeamService defaultteamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private AuthUser authUser;

    @BeforeEach
    void setUp() {
        user = userRepository.save(new User("testadmin1@example.com", "정은교", "2573758Aa!", UserRole.ROLE_ADMIN));
        authUser = new AuthUser(user.getId(), user.getEmail(), user.getUserRole());
    }



    @Test
    void 팀_정상_검색() {
        // given
        teamRepository.save(new Team("Seoul Tigers", "uniformImgUrl", "mascotImgUrl", "equipmentImgUrl", "Eye of the Tiger"));

        // when
        TeamSearchResponse response = teamService.searchTeam("Seoul Tigers");

        // then
        assertNotNull(response);
        assertEquals("Seoul Tigers", response.getTeamName());
        assertEquals("uniformImgUrl", response.getUniformImg());
        assertEquals("mascotImgUrl", response.getMascotImg());
        assertEquals("equipmentImgUrl", response.getEquipmentImg());
    }

    @Test
    void 존재하지_않는_팀_검색_예외() {
        // given
        String nonExistentTeamName = "NonExistentTeam";

        // when & then
        assertThrows(ApiException.class, () -> teamService.searchTeam(nonExistentTeamName));
    }
}