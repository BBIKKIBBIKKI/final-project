package com.wearei.finalsamplecode.integration.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import org.springframework.context.annotation.Profile;

@Retention(RUNTIME)
@Profile({"default"})
public @interface LocalProfile {

}
