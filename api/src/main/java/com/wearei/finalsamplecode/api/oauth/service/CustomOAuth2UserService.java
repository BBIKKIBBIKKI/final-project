package com.wearei.finalsamplecode.api.oauth.service;

import com.wearei.finalsamplecode.api.oauth.domain.OAuth2Attribute;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var oAuth2User = super.loadUser(userRequest);

        var providerName = userRequest.getClientRegistration().getRegistrationId();
        var attributeKey = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        var oAuth2Attribute = OAuth2Attribute.of(providerName, attributeKey, oAuth2User.getAttributes());


        return new DefaultOAuth2User(List.of(), oAuth2Attribute.convertToMap(), "id");
    }
}
