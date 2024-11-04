package com.wearei.finalsamplecode.integration.sqs;

import com.wearei.finalsamplecode.common.annotation.LocalProfile;
import org.springframework.stereotype.Component;

@Component
@LocalProfile
public class LocalSQSApi implements SQSApi {

    @Override
    public void send(String message) {
    }
}
