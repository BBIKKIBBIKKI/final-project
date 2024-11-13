package com.wearei.finalsamplecode.core.domain.team.service;

import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.integration.s3.S3Api;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomainTeamServiceTest {

    @Mock
    private TeamRepository teamRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private S3Api s3Api;
    @InjectMocks
    private DomainTeamService teamService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("test123@naver.com","이재희","Abcdefg@123", UserRole.ROLE_ADMIN);
    }

    @Test
    void 구단_정상_생성() throws IOException {
        // given
        String teamName = "Seoul Tigers";
        String themeSong = "Eye of the Tiger";
        MultipartFile uniformImg = new MockMultipartFile("uniformImg", "uniform.jpg", "image/jpeg", "fake-image-data".getBytes());
        MultipartFile mascotImg = new MockMultipartFile("mascotImg", "mascot.jpg", "image/jpeg", "fake-image-data".getBytes());
        MultipartFile equipmentImg = new MockMultipartFile("equipmentImg", "equipment.jpg", "image/jpeg", "fake-image-data".getBytes());

        String uniformImageUrl = "https://s3.bucket/uniform.jpg";
        String mascotImageUrl = "https://s3.bucket/mascot.jpg";
        String equipmentImageUrl = "https://s3.bucket/equipment.jpg";

        when(userRepository.findByIdOrThrow(user.getId())).thenReturn(user);
        when(s3Api.uploadImageToS3(uniformImg)).thenReturn(uniformImageUrl);
        when(s3Api.uploadImageToS3(mascotImg)).thenReturn(mascotImageUrl);
        when(s3Api.uploadImageToS3(equipmentImg)).thenReturn(equipmentImageUrl);
        when(teamRepository.save(any(Team.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Team createdTeam = teamService.createTeam(user.getId(), teamName, themeSong, uniformImg, mascotImg, equipmentImg);

        // then
        assertNotNull(createdTeam);
        assertEquals(teamName, createdTeam.getTeamName());
        assertEquals(uniformImageUrl, createdTeam.getUniformImg());
        assertEquals(mascotImageUrl, createdTeam.getMascotImg());
        assertEquals(equipmentImageUrl, createdTeam.getEquipmentImg());
        assertEquals(themeSong, createdTeam.getThemeSong());

        verify(teamRepository, times(1)).save(any(Team.class));
    }
}