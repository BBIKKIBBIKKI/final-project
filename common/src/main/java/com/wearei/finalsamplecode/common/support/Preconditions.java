package com.wearei.finalsamplecode.common.support;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;

public class Preconditions {

    public static void validate(boolean expression, ErrorStatus status) {
        if (!expression) {
            throw new ApiException(status);
        }
    }
}
