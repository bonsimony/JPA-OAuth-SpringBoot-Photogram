package com.cos.photogramstart.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

/*
 *  DI(Dependency Injection) 시 함수에 final이 있을때 
 * @RequiredArgsConstructor 어노티이션을 통해 생성자를 만든다.
*/
@RequiredArgsConstructor

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
		User userEntity = userRepository.findById(id).get();
														// Optional<T> findById(ID id);
														// 1. 무조건 찾았으니 걱정마 -> get()
														// 2. 못찾아서 Exception 발동 -> orElseThrow()
		
		// 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		//userEntity.setPassword(user.getPassword());
		userEntity.setPassword(encPassword);
		
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	}	// 더티체킹이 일어나서 업데이트가 완료됨
	
	
	/*
	 * @Transactional public User 회원수정(int id, User user) { 
	 * // 1. 영속화 
	 * User userEntity = userRepository.findById(id).get(); 
	 * 													// Optional<T> findById(ID id); 
	 * 													// 1. 무조건 찾았으니 걱정마 -> get() 
	 * 													// 2. 못찾아서 Exception 발동 -> orElseThrow()
	 * 
	 * // 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
	 * 
	 * return null; }
	 */
}

/*
 * 스프링부터 서버를 통해 findById(1) 해서 DB에 id값이 1인 User 정보를 찾는다. 
 * 이때 스프링부터 서버와 DB 사이에 영속성
 * 켄텍스트라는 것이 있다. 영속성 컨텍스트 안에 찾은 1번 객체가 쏙 들어오는데 이것을 영속화 되었다고 한다. 
 * 영속성 컨텍스트 안에서 수정이 이루어지고 DB에 바로 반영이 된다.
 */
