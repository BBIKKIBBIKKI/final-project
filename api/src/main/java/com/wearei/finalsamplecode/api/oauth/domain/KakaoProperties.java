package com.wearei.finalsamplecode.api.oauth.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
public class KakaoProperties {
	private String clientId;
	private String clientSecret;
	private String redirectUri;
}
