package com.wearei.finalsamplecode.api.order;

import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import com.wearei.finalsamplecode.core.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultOrderService {
    private final OrderRepository orderRepository;

    // 주문 조회
    public Order getOrder(Long orderId) {

        return orderRepository.findByOrderIdOrThrow(orderId);
    }
}
