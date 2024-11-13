package com.wearei.finalsamplecode.common.exception;

import com.wearei.finalsamplecode.common.BaseCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final BaseCode errorCode;

    public ApiException(BaseCode errorCode) {
        super(errorCode.getReasonHttpStatus().getMessage());
        this.errorCode = errorCode;
    }
}
