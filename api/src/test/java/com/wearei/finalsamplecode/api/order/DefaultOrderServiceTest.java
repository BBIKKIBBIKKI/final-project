//package com.wearei.finalsamplecode.api.order;
//
//import com.wearei.finalsamplecode.core.domain.order.entity.Order;
//import com.wearei.finalsamplecode.core.domain.order.repository.OrderRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class DefaultOrderServiceTest {
//    @Autowired
//    private DefaultOrderService defaultOrderService;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    public void 주문_조회_성공(){
//        Order order = orderRepository.save(new Order(1L));
//
//        Order foundOrder = defaultOrderService.getOrder(order.getId());
//
//        assertNotNull(foundOrder);
//        assertEquals(order.getId(), foundOrder.getId());
//    }
//}