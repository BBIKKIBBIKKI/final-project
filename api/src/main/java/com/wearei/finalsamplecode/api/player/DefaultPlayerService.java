package com.wearei.finalsamplecode.api.player;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.follow.service.DomainFollowService;
import com.wearei.finalsamplecode.core.domain.player.entity.Player;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultPlayerService {
    private final PlayerRepository playerRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    // 선수 단건 조회
    public List<Player> getPlayerByNameAndTeamName(String playerName, String teamName) {
        return playerRepository.findByPlayerNameAndOptionalTeamName(playerName, teamName);
    }

    // 선수 랭킹 조회
    public List<Player> getRankingByFollow(Integer limit) {
        // Redis에서 팔로우 랭킹을 가져오는 로직
        Set<ZSetOperations.TypedTuple<Object>> ranking = redisTemplate.opsForZSet()
                .reverseRangeWithScores(DomainFollowService.getFollowRankingKey(), 0, limit - 1);

        if (Objects.isNull(ranking) || ranking.isEmpty()) {
            return new ArrayList<>();
        }

        return ranking.stream()
                .map(tuple -> playerRepository.findById((Long) Objects.requireNonNull(tuple.getValue()))
                        .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_PLAYER)))
                .toList();
    }
}
