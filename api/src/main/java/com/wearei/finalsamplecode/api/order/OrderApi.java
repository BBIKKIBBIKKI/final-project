package com.wearei.finalsamplecode.api.order;

import com.wearei.finalsamplecode.api.order.dto.request.OrderRequest;
import com.wearei.finalsamplecode.api.order.dto.response.OrderResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.order.service.DomainOrderService;
import com.wearei.finalsamplecode.security.AuthUser;
import jakarta.validation.Valid;
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
    public ApiResponse<OrderResponse.Create> createOrder(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody OrderRequest.Create request){
        return ApiResponse.onSuccess(new OrderResponse.Create(domainOrderService.createOrder(authUser.getUserId(), request.storeId(), request.menuId(), request.quantity())));
    }

    // 주문 수정
    @PatchMapping("/{orderId}")
    public ApiResponse<OrderResponse.Update> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest.Update request){
        return ApiResponse.onSuccess(new OrderResponse.Update(domainOrderService.updateOrder(orderId, request.storeId(), request.menuId(), request.quantity())));
    }

    // 주문 상태 수정(사장님 권한)
    @PatchMapping("/{orderId}/status")
    public ApiResponse<OrderResponse.UpdateOrderStatus> updateOrderStatus(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long orderId, @RequestBody OrderRequest.UpdateOrderStatus request){
        return ApiResponse.onSuccess(new OrderResponse.UpdateOrderStatus(domainOrderService.updateOrderStatus(authUser.getUserId(), orderId, request.orderStatus())));
    }

    // 주문 조회
    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse.GetOrder> getOrder(@PathVariable Long orderId){
        return ApiResponse.onSuccess(new OrderResponse.GetOrder(defaultOrderService.getOrder(orderId)));
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ApiResponse<Void> deleteOrder(@PathVariable Long orderId){
        domainOrderService.deleteOrder(orderId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
