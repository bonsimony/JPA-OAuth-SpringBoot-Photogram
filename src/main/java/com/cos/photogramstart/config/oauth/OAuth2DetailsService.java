package com.cos.photogramstart.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	// Ctril + Space를 누르고 아래 함수를 오버라이드 해준다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		Map<String, Object> userInfo = oauth2User.getAttributes();
		
		String username = "facebook_" + (String) userInfo.get("id"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		String name = (String)userInfo.get("name"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
		String email = (String)userInfo.get("email"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) { // 페이스북 최초 로그인
			User user = User.builder()
					.username(username)
					.password(password)
					.name(name)
					.email(email)
					.role("ROLE_USER")
					.build();
			
			return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes());
			
		}else { // 페이스북으로 이미 회원가입이 되어 있다는 뜻
			
			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
		}
		
	}
	
	
	
	
	
	
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		
//		OAuth2User oauth2User = super.loadUser(userRequest);
//		
//		Map<String, Object> userInfo = oauth2User.getAttributes();
//		
//		String username = "facebook_" + (String) userInfo.get("id"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
//		String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());
//		String name = (String)userInfo.get("name"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
//		String email = (String)userInfo.get("email"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
//		
//		User userEntity = userRepository.findByUsername(username);
//		
//		if(userEntity == null) { // 페이스북 최초 로그인
//			User user = User.builder()
//					.username(username)
//					.password(password)
//					.name(name)
//					.email(email)
//					.role("ROLE_USER")
//					.build();
//			
//			return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes());
//			
//		}else { // 페이스북으로 이미 회원가입이 되어 있다는 뜻
//			
//			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
//		}
//		
//	}
	
	
	
	
	
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		
//		OAuth2User oauth2User = super.loadUser(userRequest);
//		
//		Map<String, Object> userInfo = oauth2User.getAttributes();
//		
//		String username = "facebook_" + (String) userInfo.get("id"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
//		String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());
//		String name = (String)userInfo.get("name"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
//		String email = (String)userInfo.get("email"); // Object 타입이기 때문에 String으로 다운캐스팅 해준다.
//		
//		
//		
//		User user = User.builder()
//				.username(username)
//				.password(password)
//				.name(name)
//				.email(email)
//				.build()
//		;
//		
//		User userEntity = userRepository.save(user);
//		
//		return super.loadUser(userRequest);
//	}
	
	
	
	
	
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
