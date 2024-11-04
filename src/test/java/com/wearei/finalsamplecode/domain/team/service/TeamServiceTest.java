package com.wearei.finalsamplecode.domain.team.service;

import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamCreateRequest;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamCreateResponse;
import com.wearei.finalsamplecode.domain.team.dto.response.TeamSearchResponse;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class TeamServiceTest {
    @Autowired
    private TeamService teamService;
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
    void 구단_정상_생성() {
        // given
        TeamCreateRequest request = new TeamCreateRequest("Seoul Tigers", "Eye of the Tiger");
        MultipartFile uniformImg = new MockMultipartFile("uniformImg", "uniform.jpg", "image/jpeg", "fake-image-data".getBytes());
        MultipartFile mascotImg = new MockMultipartFile("mascotImg", "mascot.jpg", "image/jpeg", "fake-image-data".getBytes());
        MultipartFile equipmentImg = new MockMultipartFile("equipmentImg", "equipment.jpg", "image/jpeg", "fake-image-data".getBytes());

        // when
        TeamCreateResponse response = teamService.createTeam(request, authUser, uniformImg, mascotImg, equipmentImg);

        // then
        assertNotNull(response);
        assertEquals("Seoul Tigers", response.getTeamName());
        assertNotNull(response.getUniformImg());
        assertNotNull(response.getMascotImg());
        assertNotNull(response.getEquipmentImg());
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
