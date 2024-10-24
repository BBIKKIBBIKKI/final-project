package com.wearei.finalsamplecode.domain.order.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.order.dto.request.CreateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.request.UpdateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.response.CreateOrderResponse;
import com.wearei.finalsamplecode.domain.order.dto.response.UpdateOrderResponse;
import com.wearei.finalsamplecode.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ApiResponse<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request, @AuthenticationPrincipal AuthUser authUser){
        return ApiResponse.onSuccess(orderService.createOrder(request, authUser));
    }

    // 주문 수정
    @PatchMapping("/{orderId}")
    public ApiResponse<UpdateOrderResponse> updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderRequest request){
        return ApiResponse.onSuccess(orderService.updateOrder(orderId, request));
    }


    // 주문 조회
//    @GetMapping("/{orderId}")
//    public ApiResponse<GetOrderResponse> getOrder(@PathVariable Long orderId){
//        return ApiResponse.onSuccess(orderService.getOrder(orderId));
//    }
}
