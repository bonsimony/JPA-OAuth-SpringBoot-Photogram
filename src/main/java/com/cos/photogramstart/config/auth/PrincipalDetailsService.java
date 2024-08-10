package com.cos.photogramstart.config.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IoC 등록
public class PrincipalDetailsService implements UserDetailsService{
													// UserDetailsService를 상속받는다.
													// PrincipalDetailsService 마우스를 올리고 Add unimplemented methods 클릭
	
	// final 사용 시 클래스에 @RequiredArgsConstructor을 명시해야 한다.
	private final UserRepository userRepository;
	
	// 1. 패스워드는 알아서 체크하기 때문에 신경쓸 필요가 없다.
	// 2. 리턴이 잘되면 자동적으로 내부에서 UserDetails 타입을 세션으로 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("loadUserByUsername 실행되는지 확인 >>" + " " + username);
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) 
		{
			return null;
		}
		else
		{
			return new PrincipalDetails(userEntity);
		}
		
		
	}
	
	
	
	
	
	
	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		System.out.println("loadUserByUsername 실행되는지 확인 >>" + " " + username);
//		
//		User userEntity = userRepository.findByUsername(username);
//		
//		if(userEntity == null) 
//		{
//			return null;
//		}
//		else
//		{
//			// 유저 정보를 가지고 있는 변수는 userEntity이지만
//			// loadUserByUsername 메소드의 리턴 타입이 UserDetails이기 때문에
//			// new UserDetails()를 통해 생성을 한다고 해도 UserDetails는 인터페이스이기 때문에 
//			// 인터페이스 내 모든 메소드에 대해 세팅을 해줘야 되서 복잡해진다.
//			return new UserDetails() {
//				
//				@Override
//				public boolean isEnabled() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean isCredentialsNonExpired() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean isAccountNonLocked() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean isAccountNonExpired() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public String getUsername() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//				
//				@Override
//				public String getPassword() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//				
//				@Override
//				public Collection<? extends GrantedAuthority> getAuthorities() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			};
//			
//			// return userEntity;
//		}
//		
//	}
	
	
	
													
}

//시큐리티 설정파일에서 post로 /auth/signin으로 들어오는지 계속 주시하고 있다.
//클라이언트가 /auth/signin 요청을 할때 
//시큐리티 설정파일에서 등록된 /auth/signin이 일치하면
//http Body에 username과 password를 들고 오는데
//IoC 컨테이너에 UserDetailsService가 메모리에 마운트 되어 있어 로그인을 진행한다.
//PrincipalDetailsService에 @Service 어노테이션을 활용하여 IoC 컨테이너에 등록하려고 보니
//UserDetailsService을 상속받았기 때문에 타입이 같아지고 UserDetailsService을 덮어씌운다.
//그러면 UserDetailsService가 아닌 PrincipalDetailsService가 IoC 컨테이너에 최종적으로 등록된다.
//signin.jsp를 통해 /sign/in 경로를 통해 시큐리티 설정파일에서 PrincipalDetailsService로 로그인을 진행하게 된다.

