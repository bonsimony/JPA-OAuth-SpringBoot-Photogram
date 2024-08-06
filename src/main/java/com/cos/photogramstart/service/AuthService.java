package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service	// 1. IoC 등록 
				// 2. 트랜잭션 관리

/*
 	* @Service가 주석되어 있으면 IoC가 등록되어 있지 않으며 
 	* IoC 컨테이너에 없기 때문에 
 	* AuthController 쪽에서 오류가 난다.
 	* 왜냐하면, AuthController 생성자에 authService가 없어서
 	* AuthController 생성자를 만들지 못한다.
 */

@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	
	public User 회원가입(User user) {
		/*
		 * user 변수는 외부에서 받은 통신을 통해서 User Object에 담은 것이다.
		 */
		
		//회원가입 진행
		User userEntity = userRepository.save(user);
		/* 
		 * userEntity 변수는 DB에 들어가고 난 후에 응답받은 것이다.
		 * 데이베이스에 있는 데이터들을 User Object에 담은 것이다. 
		 */
		
		return userEntity;
	}
}
