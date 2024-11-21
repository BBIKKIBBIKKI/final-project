package com.wearei.finalsamplecode.api.oauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.security.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtUtil jwtUtil;
	private final ObjectMapper objectMapper;
	private final UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		var authUser = (OAuth2User) authentication.getPrincipal();
		var email = authUser.getAttribute("email").toString();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ApiException(ErrorStatus._BAD_FOUND_EMAIL));

		var token = jwtUtil.createToken(user.getId(), email, UserRole.ROLE_USER);

		writeTokenResponse(response, token);
	}

	private void writeTokenResponse(HttpServletResponse response, String token) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		var writer = response.getWriter();

		writer.println(objectMapper.writeValueAsString(token));

		writer.flush();
	}
}
