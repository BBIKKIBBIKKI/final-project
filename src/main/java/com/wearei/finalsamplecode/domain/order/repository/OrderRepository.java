package com.wearei.finalsamplecode.domain.order.repository;


import com.wearei.finalsamplecode.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
