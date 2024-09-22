package com.cos.photogramstart.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	// Ctril + Space를 누르고 아래 함수를 오버라이드 해준다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		return super.loadUser(userRequest);
	}
	
	
	
	
	
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		
//		// System.out.println("OAuth2 서비스 탐");
//		OAuth2User oauth2User = super.loadUser(userRequest);
//		System.out.println(oauth2User.getAttributes());
//		
//		return null;
//		//return super.loadUser(userRequest);
//	}
	
}

// SecuriyConfig.java 파일에 OAuth2DetailsService를 DI(Dependency Injection)해준다.
