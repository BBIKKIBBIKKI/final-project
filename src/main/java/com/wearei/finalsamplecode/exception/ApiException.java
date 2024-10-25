package com.wearei.finalsamplecode.exception;

import com.wearei.finalsamplecode.apipayload.BaseCode;
import com.wearei.finalsamplecode.apipayload.dto.ReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ApiException extends RuntimeException {
    private final BaseCode errorCode;

    public ApiException(BaseCode errorCode) {
        super(errorCode.getReasonHttpStatus().getMessage());
        this.errorCode = errorCode;
    }
}
