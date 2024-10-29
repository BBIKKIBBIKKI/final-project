package com.wearei.finalsamplecode.common.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SQSEvent {
    private final String message;
}
