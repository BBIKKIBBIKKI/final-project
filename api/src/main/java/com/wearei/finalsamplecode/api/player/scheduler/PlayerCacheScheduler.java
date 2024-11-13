package com.wearei.finalsamplecode.api.player.scheduler;

import com.wearei.finalsamplecode.core.domain.follow.service.DomainFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PlayerCacheScheduler {
    private final RedisTemplate<String, Object> redisTemplate;

    // 매일 밤 자정에 초기화
    @Scheduled(cron ="0 0 0 * * ?")
    public void resetFollowRanking(){
        redisTemplate.delete(DomainFollowService.getFollowRankingKey());
    }
}
