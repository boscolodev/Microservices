package dev.boscolo.hroauth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import dev.boscolo.hroauth.dto.UserDTO;
import dev.boscolo.hroauth.services.UserService;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserService service;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		UserDTO userDTO = service.findByEmail(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();

		map.put("userId", userDTO.getId());
		map.put("name", userDTO.getName());
		map.put("authorities", userDTO.getAuthorities());

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);

		return accessToken;
	}

}