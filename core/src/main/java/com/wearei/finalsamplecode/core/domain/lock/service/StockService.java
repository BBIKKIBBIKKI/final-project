package com.wearei.finalsamplecode.core.domain.lock.service;

import com.wearei.finalsamplecode.core.domain.lock.property.StockProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final RedissonClient redissonClient;
    private final StockProperty stockProperty;
    private static final int EMPTY = 0;

    public String keyResolver(String domain, String keyId) {
        final String prefix = stockProperty.getPrefix() + ":" + domain + ":%s";
        return String.format(prefix, keyId);
    }

    public void decrease(final String key, final int count) {
        final String lockName = key + ":lock";
        final RLock lock = redissonClient.getLock(lockName);
        final String worker = Thread.currentThread().getName();

        try {
            if (!lock.tryLock(10, 10, TimeUnit.SECONDS)) {
                log.info("[{}] 락을 얻지 못했습니다.", worker);
                return;
            }

            // 현재 재고 조회 후 감소
            int stock = currentStock(key);
            if (stock <= EMPTY) {
                log.info("[{}] 현재 남은 재고가 없습니다. ({}개)", worker, stock);
                return;
            }

            if (stock < count) {
                log.info("[{}] 현재 재고가 주문 수량보다 적습니다. (재고: {}, 주문 수량: {})", worker, stock, count);
                return;
            }

            stock -= count;
            setStock(key, stock); // 감소된 재고를 Redis에 업데이트
            log.info("현재 진행중인 사람 : {} & 현재 남은 재고 : {}개", worker, stock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void decreaseNoLock(final String key, final int count) {
        final String worker = Thread.currentThread().getName();
        int stock = currentStock(key);
        log.info("[{}] 현재 남은 재고 : {}", worker, stock);

        if (stock <= EMPTY) {
            log.info("[{}] 현재 남은 재고가 없습니다. ({}개)", worker, stock);
            return;
        }

        log.info("현재 진행중인 사람 : {} & 현재 남은 재고 : {}개", worker, stock);
        stock -= count;
        setStock(key, stock);  // 변경된 재고를 Redis에 업데이트
    }

    public void setStock(String key, int amount) {
        redissonClient.getBucket(key).set(amount);
        int storedAmount = (int) redissonClient.getBucket(key).get(); // 저장 후 확인
        log.info("Redis에 재고 설정 완료 - key: {}, amount: {}, 실제 저장된 값: {}", key, amount, storedAmount);
    }

    public int currentStock(String key) {
        Object stockValue = redissonClient.getBucket(key).get();
        if (stockValue == null) {
            log.error("재고 정보를 찾을 수 없습니다. (key: {})", key);
            throw new IllegalStateException("재고 정보를 찾을 수 없습니다. (key: " + key + ")");
        }
        log.info("현재 Redis에서 조회된 재고: {}", stockValue); // 조회된 값 확인
        return (int) stockValue;
    }
}