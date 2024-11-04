package com.wearei.finalsamplecode.domain.order.repository;

import com.wearei.finalsamplecode.domain.order.entity.Order;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    default Order findByOrderIdOrThrow(Long orderId) {
        return findById(orderId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
