package com.wearei.finalsamplecode.domain.ground.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.dto.request.GroundCreateRequest;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundCreateResponse;
import com.wearei.finalsamplecode.domain.ground.dto.response.GroundSearchResponse;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
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
public class GroundServiceTest {
    @Autowired
    private GroundService groundService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GroundRepository groundRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private AuthUser authUser;
    private Team team;
    private Ground ground;

    @BeforeEach
    void setUp() {
        user = userRepository.save(new User("admin@example.com", "관리자", "password123", UserRole.ROLE_ADMIN));
        authUser = new AuthUser(user.getId(), user.getEmail(), user.getUserRole());
        team = teamRepository.save(new Team("두산베어스", "url1", "url2", "url3", "url4"));
    }

    @Test
    void 구장_정상_생성() {
        // 이미지 파일 생성 (MockMultipartFile 사용)
        MultipartFile file = new MockMultipartFile(
                "file",
                "testGroundImage.jpg",
                "image/jpeg",
                "This is the file content".getBytes()
        );

        // 구장 생성 요청 객체
        GroundCreateRequest groundCreateRequest = new GroundCreateRequest(team.getId(), "잠실주경기장", "서울", "02-1234-5678");

        // when: 구장 생성
        GroundCreateResponse groundCreateResponse = groundService.createGround(groundCreateRequest, authUser, file);

        // then: 구장 정상 생성 확인
        Ground savedGround = groundRepository.searchGroundByTeamOrGroundName(team.getTeamName(), groundCreateResponse.getGroundName())
                .orElse(null);

        // 검증
        assertEquals("잠실주경기장", savedGround.getGroundName());
        assertEquals("서울", savedGround.getLocation());
        assertEquals("02-1234-5678", savedGround.getTel());
        assertNotNull(savedGround.getGroundImg()); // 이미지 URL이 null이 아님을 확인
        assertEquals(team.getId(), savedGround.getTeam().getId()); // 팀 ID가 일치하는지 확인
    }

    @Test
    void 구장_생성시_존재하지_않는_팀_예외_테스트() {
        // given
        MultipartFile file = new MockMultipartFile(
                "file",
                "testGroundImage.jpg",
                "image/jpeg",
                "This is the file content".getBytes()
        );

        // 존재하지 않는 팀 ID 사용
        GroundCreateRequest request = new GroundCreateRequest(999L, "잠실 주경기장", "서울", "02-1234-5678");

        // when/then: 팀이 없을 때 예외가 발생하는지 확인
        ApiException exception = assertThrows(ApiException.class, () -> {
            groundService.createGround(request, authUser, file);
        });

        // 예외 메시지 검증
        assertEquals(ErrorStatus._NOT_FOUND_TEAM.getMessage(), exception.getMessage());
    }

    @Test
    void 팀이름으로_구장_검색_정상_테스트() {
        // given
        ground = groundRepository.save(new Ground("잠실주경기장", "서울", "02-1234-5678", "groundImageUrl", team));

        // when
        GroundSearchResponse response = groundService.searchGround("두산베어스", null);

        // then
        assertEquals(ground.getGroundName(), response.getGroundName());
        assertEquals(ground.getLocation(), response.getLocation());
        assertEquals(ground.getTel(), response.getTel());
        assertEquals(ground.getGroundImg(), response.getGroundImg());
    }

    @Test
    void 구장이름으로_구장_검색_정상_테스트() {
        // given
        ground = groundRepository.save(new Ground("잠실주경기장", "서울", "02-1234-5678", "groundImageUrl", team));
        // when
        GroundSearchResponse response = groundService.searchGround(null, "잠실주경기장");

        // then
        assertEquals(ground.getGroundName(), response.getGroundName());
        assertEquals(ground.getLocation(), response.getLocation());
        assertEquals(ground.getTel(), response.getTel());
        assertEquals(ground.getGroundImg(), response.getGroundImg());
    }

    @Test
    void 존재하지_않는_팀_검색_예외_테스트() {
        // given
        ground = groundRepository.save(new Ground("잠실주경기장", "서울", "02-1234-5678", "groundImageUrl", team));
        // when/then
        ApiException exception = assertThrows(ApiException.class, () -> {
            groundService.searchGround("없는팀", null);
        });

        // 예외 메시지 검증
        assertEquals(ErrorStatus._NOT_FOUND_TEAM.getMessage(), exception.getMessage());
    }

    @Test
    void 존재하지_않는_구장_검색_예외_테스트() {
        // given
        ground = groundRepository.save(new Ground("잠실주경기장", "서울", "02-1234-5678", "groundImageUrl", team));
        // when/then
        ApiException exception = assertThrows(ApiException.class, () -> {
            groundService.searchGround(null, "없는구장");
        });

        // 예외 메시지 검증
        assertEquals(ErrorStatus._NOT_FOUND_GROUND.getMessage(), exception.getMessage());
    }

    @Test
    void 검색_조건_부적합_예외_테스트() {
        // given
        ground = groundRepository.save(new Ground("잠실주경기장", "서울", "02-1234-5678", "groundImageUrl", team));
        // when/then
        ApiException exception = assertThrows(ApiException.class, () -> {
            groundService.searchGround(null, null);  // 검색 조건 없음
        });

        // 예외 메시지 검증
        assertEquals(ErrorStatus._INVALID_SEARCH_CRITERIA.getMessage(), exception.getMessage());
    }
}
