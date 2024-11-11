package com.wearei.finalsamplecode.core.domain.team;

import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.team.service.DomainTeamService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@SpringBootTest
public class DomainTeamServiceTest {
    @Autowired
    private DomainTeamService teamService;
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

}
