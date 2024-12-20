package com.wearei.finalsamplecode.api.oauth.domain;

import java.util.LinkedHashMap;
import lombok.Builder;
import java.util.Map;

@Builder
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String nickname;
    private String picture;

    public static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        return switch (provider) {
            case "kakao" -> ofKakao(attributeKey, attributes);
            default -> throw new IllegalStateException("Unexpected value: " + provider);
        };
    }

    private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes) {
        LinkedHashMap kakaoAccount = (LinkedHashMap) attributes.get("kakao_account");

        LinkedHashMap kakaoProfile = (LinkedHashMap) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .nickname(kakaoProfile.get("nickname").toString())
                .email((String) kakaoAccount.get("email"))
                .picture((String) attributes.get("profile_image"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    public Map<String, Object> convertToMap() {
        return Map.of(
                "id", attributeKey,
                "nickname", nickname,
                "email", email
        );
    }
}
