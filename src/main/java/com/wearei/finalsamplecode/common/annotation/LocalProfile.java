package com.wearei.finalsamplecode.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import org.springframework.context.annotation.Profile;

@Retention(RUNTIME)
@Profile({"default", "local"})
public @interface LocalProfile {

}
