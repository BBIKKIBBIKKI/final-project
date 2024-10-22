package com.wearei.finalsamplecode.aop;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;

@Slf4j
@Aspect
public class OrderLogAspect {
    private final HttpServletRequest servletRequest;

    public OrderLogAspect(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Pointcut("")
    private void orderMethods() {}

    @Around("orderMethods()")
    public void recordOrderLog(ProceedingJoinPoint joinPoint) throws Throwable {

//        return result;  // 메서드의 실제 결과 반환
    }
}
