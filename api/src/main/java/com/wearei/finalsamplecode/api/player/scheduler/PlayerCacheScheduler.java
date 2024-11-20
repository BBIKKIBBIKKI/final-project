package com.wearei.finalsamplecode.api.player.scheduler;

import com.wearei.finalsamplecode.core.domain.follow.service.DomainFollowService;
import com.wearei.finalsamplecode.core.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;


@Component
@RequiredArgsConstructor
public class PlayerCacheScheduler {
    private final RedisTemplate<String, Object> redisTemplate;
    private final PlayerRepository playerRepository;

    // 매일 밤 자정에 초기화
    @Scheduled(cron ="0 0 0 * * ?")
    public void resetFollowRanking(){
        // Redis 에서 기존 선수 랭킹 캐시 데이터 삭제 (DB 에는 영향 X -> 팔로우 수는 그대로 유지됨.)
        redisTemplate.delete(DomainFollowService.getFollowRankingKey());

        // DB 에서 모든 선수 데이터 조회 -> Object[] 로 필요한 값만 조회
        List<Object[]> players = playerRepository.findPlayerFollowCounts();

        // 각 선수의 팔로우 수를 Redis Sorted Set 에 반영
        players.forEach(record ->{
            Long playerId = (Long) record[0];
            Long followCount = (Long) record[1];
            redisTemplate.opsForZSet().add(DomainFollowService.getFollowRankingKey(), playerId, followCount);
        });

        redisTemplate.expire(DomainFollowService.getFollowRankingKey(), Duration.ofDays(1));
    }
}
