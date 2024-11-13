package com.wearei.finalsamplecode.core.domain.ground.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
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
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainGroundServiceTest {

    @InjectMocks
    private DomainGroundService domainGroundService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private GroundRepository groundRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private S3Api s3Api;

    private User adminUser;
    private Team team;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock 데이터 설정
        adminUser = new User("admin@example.com", "관리자", "password123", UserRole.ROLE_ADMIN);
        team = new Team("두산베어스", "url1", "url2", "url3", "url4");

        // 기본적인 Mock 동작 설정
        when(userRepository.findByIdOrThrow(anyLong())).thenReturn(adminUser);
        when(teamRepository.findById(anyLong())).thenReturn(Optional.of(team));
    }

    @Test
    void 구장_정상_생성() throws IOException {
        // given
        MultipartFile file = new MockMultipartFile(
                "file",
                "testGroundImage.jpg",
                "image/jpeg",
                "This is the file content".getBytes()
        );

        String uploadedImageUrl = "https://s3.amazonaws.com/testGroundImage.jpg";
        when(s3Api.uploadImageToS3(file)).thenReturn(uploadedImageUrl);

        // teamRepository에서 team을 찾을 수 있도록 Mock 설정 추가
        when(teamRepository.findById(team.getId())).thenReturn(Optional.of(team));

        // groundRepository.save()가 반환할 Ground 객체 설정
        Ground savedGround = new Ground("잠실주경기장", "서울", "02-1234-5678", uploadedImageUrl, team);
        when(groundRepository.save(any(Ground.class))).thenReturn(savedGround);

        // when
        Ground ground = domainGroundService.createGround(1L, team.getId(), "잠실주경기장", "서울", "02-1234-5678", file);

        // then
        assertNotNull(ground);
        assertEquals("잠실주경기장", ground.getGroundName());
        assertEquals("서울", ground.getLocation());
        assertEquals("02-1234-5678", ground.getTel());
        assertEquals(uploadedImageUrl, ground.getGroundImg());
        assertEquals(team, ground.getTeam());

        verify(groundRepository, times(1)).save(any(Ground.class));
    }


    @Test
    void 구장_생성시_존재하지_않는_팀_예외_테스트() {
        // given
        when(teamRepository.findById(anyLong())).thenReturn(Optional.empty());
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test content".getBytes());

        // when & then
        ApiException exception = assertThrows(ApiException.class, () ->
                domainGroundService.createGround(1L, 999L, "잠실주경기장", "서울", "02-1234-5678", file)
        );

        assertEquals(ErrorStatus._NOT_FOUND_TEAM.getMessage(), exception.getMessage());
    }

    @Test
    void 구장_생성시_파일_업로드_실패_예외_테스트() throws IOException {
        // given
        MultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Test content".getBytes());
        when(s3Api.uploadImageToS3(file)).thenThrow(new IOException("S3 upload failed"));

        // when & then
        ApiException exception = assertThrows(ApiException.class, () ->
                domainGroundService.createGround(1L, team.getId(), "잠실주경기장", "서울", "02-1234-5678", file)
        );

        assertEquals(ErrorStatus._NOT_FOUND_TEAM.getMessage(), exception.getMessage());
    }
}
