package com.wearei.finalsamplecode.api.order;

import com.wearei.finalsamplecode.api.order.dto.request.CreateOrderRequest;
import com.wearei.finalsamplecode.api.order.dto.request.UpdateOrderRequest;
import com.wearei.finalsamplecode.api.order.dto.request.UpdateOrderStatusRequest;
import com.wearei.finalsamplecode.api.order.dto.response.CreateOrderResponse;
import com.wearei.finalsamplecode.api.order.dto.response.GetOrderResponse;
import com.wearei.finalsamplecode.api.order.dto.response.UpdateOrderResponse;
import com.wearei.finalsamplecode.api.order.dto.response.UpdateOrderStatusResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import com.wearei.finalsamplecode.core.domain.order.service.DomainOrderService;
import com.wearei.finalsamplecode.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi {
    private final DomainOrderService domainOrderService;
    private final DefaultOrderService defaultOrderService;

    // 주문 생성
    @PostMapping
    public ApiResponse<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request, @AuthenticationPrincipal AuthUser authUser){
        Order order = domainOrderService.createOrder(authUser.getUserId(), request.getStoreId(), request.getMenuId(), request.getQuantity());
        return ApiResponse.onSuccess(new CreateOrderResponse(order));
    }

    // 주문 수정
    @PatchMapping("/{orderId}")
    public ApiResponse<UpdateOrderResponse> updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderRequest request){
        Order order = domainOrderService.updateOrder(orderId, request.getStoreId(), request.getMenuId(), request.getQuantity());
        return ApiResponse.onSuccess(new UpdateOrderResponse(order));
    }

    // 주문 상태 수정(사장님 권한)
    @PatchMapping("/{orderId}/status")
    public ApiResponse<UpdateOrderStatusResponse> updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest request, @AuthenticationPrincipal AuthUser authUser){
        Order order = domainOrderService.updateOrderStatus(authUser.getUserId(), orderId, request.getOrderStatus());
        return ApiResponse.onSuccess(new UpdateOrderStatusResponse(order));
    }

    // 주문 조회
    @GetMapping("/{orderId}")
    public ApiResponse<GetOrderResponse> getOrder(@PathVariable Long orderId){
        Order order = defaultOrderService.getOrder(orderId);
        return ApiResponse.onSuccess(new GetOrderResponse(order));
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ApiResponse<String> deleteOrder(@PathVariable Long orderId){
        domainOrderService.deleteOrder(orderId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
