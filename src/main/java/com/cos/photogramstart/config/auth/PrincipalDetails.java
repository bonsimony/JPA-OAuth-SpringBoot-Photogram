package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
									
	private static final long serialVersionUID = 1L;
	//PrincipalDetails 마우스를 올려 Add default serial version ID를 클릭한다.

	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user){
		this.user = user;
	}
	
	//OAuth2 처리를 위해 오버로딩을 해준다.
	public PrincipalDetails(User user, Map<String, Object> attributes){
		this.user = user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collector = new ArrayList<>();

		collector.add
		(
				() -> { 
								return user.getRole();
						 }
		);
		
		return collector;
	}	





//@Data
//public class PrincipalDetails implements UserDetails{
//										// UserDetails를 상속받는다.
//										// PrincipalDetails 마우스를 올려 Add unimplemented methods를 클릭한다.
//	
//
//	private static final long serialVersionUID = 1L;
//	//PrincipalDetails 마우스를 올려 Add default serial version ID를 클릭한다.
//
//	private User user;
//	
//	public PrincipalDetails(User user){
//		this.user = user;
//	}
//	
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		// 문법적으로 잘 몰을때 아래와 같이 진행한다.
//		// Collection 객체를 생성하는데 인자에 상속받은 GrantedAuthority을 사용한다.
//		// Collection 부모는 ArrayList인걸 확인할 수 있다.
//		Collection<GrantedAuthority> collector = new ArrayList<>();
//		
//		// Collection 객체에서 add 함수 인자에 GrantedAuthority 를 생성해준다.
//		// 람다식으로 정리해준다.
//		// 람다식을 사용할 수 있는 이유는?
//		// collector.add 안에 함수를 넣고 싶은게 목적이다.
//		// 자바에서 함수를 넘기고 싶으면 인터페이스를 사용하거나 클래스 오브젝트를 넘긴다.
//		// 목적이 함수를 넘기기 때문에 람다식을 사용하여 함수를 넘겨준다.
//		collector.add
//		(
//				() -> { 
//								return user.getRole();
//						 }
//		);
//		
//		return collector;
//	}	
	
	
	
	
	
	
/**************************************************************************************************/	
	
//	// 권한 : 한개가 아닐 수 있음 (3개 이상의 권한)
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		// 문법적으로 잘 몰을때 아래와 같이 진행한다.
//		// Collection 객체를 생성하는데 인자에 상속받은 GrantedAuthority을 사용한다.
//		// Collection 부모는 ArrayList인걸 확인할 수 있다.
//		Collection<GrantedAuthority> collector = new ArrayList<>();
//		
//		// Collection 객체에서 add 함수 인자에 GrantedAuthority 를 생성해준다.
//		// GrantedAuthority는 인터페이스이기 때문에 override된 함수인 getAuthority가 생성된다.
//		collector.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// TODO Auto-generated method stub
//				return user.getRole();
//			}
//		});
//		
//		return collector;
//	}
	
	
/**************************************************************************************************/	
	
	
	
	
	
	
	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public Map<String, Object> getAttributes() {
		return attributes; 
	}


	@Override
	public String getName() {
		return (String)attributes.get("name");
	}
										
	
	
}
