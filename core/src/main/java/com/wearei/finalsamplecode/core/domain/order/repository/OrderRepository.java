package com.wearei.finalsamplecode.core.domain.order.repository;

import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    default Order findByOrderIdOrThrow(Long orderId) {
        return findById(orderId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
