package com.wearei.finalsamplecode.core.domain.follow.service;

import com.wearei.finalsamplecode.core.domain.follow.entity.Follow;
import com.wearei.finalsamplecode.core.domain.follow.repository.FollowRepository;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DomainFollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final RedisTemplate<String,Object> redisTemplate;

    private static final String FOLLOW_RANKING_KEY = "player:followRanking";

    @Transactional
    public Follow createFollow(Long userId, Long playerId) {
        User user = userRepository.findByIdOrThrow(userId);
        Player player = playerRepository.findByPlayerIdOrThrow(playerId);

        // 팔로우 수 증가
        player.incrementFollow();

        /*
        * Redis Sorted Set 에 팔로우 수 업데이트
        * sorted set : 랭킹 작업에 최적화된 자료구조
        * Key = FOLLOW_RANKING_KEY, Value = playerId, score = player.getFollow 팔로우 수로 저장
        * */
        redisTemplate.opsForZSet().incrementScore(FOLLOW_RANKING_KEY, player.getId(), player.getFollow());

        return followRepository.save(
                new Follow(user, player)
        );
    }

    public static String getFollowRankingKey() {
        return FOLLOW_RANKING_KEY;
    }
}
