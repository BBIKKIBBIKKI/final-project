package com.wearei.finalsamplecode.core.domain.follow.service;


import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.follow.entity.Follow;
import com.wearei.finalsamplecode.core.domain.follow.repository.FollowRepository;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class DomainFollowServiceTest {

    private User user;

    private Team team;

    private Player player;

    private Follow follow;

    @Mock
    private FollowRepository followRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private DomainFollowService domainFollowService;

    @BeforeEach
    void init(){

        user = new User(
                "duduio2050@gmail.com",
                "오현택",
                "qwer123",
                UserRole.ROLE_ADMIN
        );

        team = new Team(
                "삼성 라이온즈",
                "uniformImg",
                "mascotImg",
                "equipmentImg",
                "themeSong"
        );

        player = new Player(
                20L,
                "오현택",
                team,
                "투수",
                "하트오브헐",
                "바디체크가뭐에요?"
        );

        follow = new Follow(
                user,
                player
        );

        ReflectionTestUtils.setField(user, "id", 1L);
        ReflectionTestUtils.setField(team, "id", 1L);
        ReflectionTestUtils.setField(player, "id", 1L);
        ReflectionTestUtils.setField(follow, "id", 1L);
    }

    @Test
    void 팔로우_성공_테스트(){
        Long userId = 1L;
        Long playerId = 1L;

        given(userRepository.findByIdOrThrow(userId)).willReturn(user);
        given(playerRepository.findByPlayerIdOrThrow(playerId)).willReturn(player);
        given(followRepository.save(any(Follow.class))).willReturn(follow);

        Follow follow = domainFollowService.createFollow(userId, playerId);

        assertEquals(follow.getUser().getId(), userId);
        assertEquals(follow.getPlayer().getId(), playerId);
    }

    @Test
    void 팔로우_실패_테스트_없는유저(){
        Long userId = 2L;
        Long playerId = 1L;

        given(userRepository.findByIdOrThrow(userId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_USER));

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainFollowService.createFollow(userId, playerId);
        });

        assertEquals(ErrorStatus._NOT_FOUND_USER.getMessage(), apiException.getMessage());
    }

    @Test
    void 팔로우_실패_테스트_존재하지_않는_선수(){
        Long userId = 1L;
        Long playerId = 99L;

        given(userRepository.findByIdOrThrow(userId)).willReturn(user);
        given(playerRepository.findByPlayerIdOrThrow(playerId)).willThrow(new ApiException(ErrorStatus._NOT_FOUND_PLAYER));

        ApiException apiException = assertThrows(ApiException.class, () -> {
            domainFollowService.createFollow(userId, playerId);
        });

        assertEquals(ErrorStatus._NOT_FOUND_PLAYER.getMessage(), apiException.getMessage());
    }

}
