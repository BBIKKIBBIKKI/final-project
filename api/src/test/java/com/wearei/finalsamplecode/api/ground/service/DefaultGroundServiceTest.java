package com.wearei.finalsamplecode.api.ground.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class DefaultGroundServiceTest {

    @Autowired
    private DefaultGroundService defaultGroundService;

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