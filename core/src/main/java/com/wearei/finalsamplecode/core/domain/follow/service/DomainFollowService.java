package com.wearei.finalsamplecode.core.domain.follow.service;

import com.wearei.finalsamplecode.core.domain.follow.repository.FollowRepository;
import com.wearei.finalsamplecode.core.domain.follow.entity.Follow;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DomainFollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    private final PlayerRepository playerRepository;

    @Transactional
    public Follow createFollow(Long userId, Long playerId) {
        User user = userRepository.findByIdOrThrow(userId);
        Player player = playerRepository.findByPlayerIdOrThrow(playerId);

        // 팔로우 수 증가
        player.incrementFollow();

        return followRepository.save(
                new Follow(user, player)
        );
    }
}
