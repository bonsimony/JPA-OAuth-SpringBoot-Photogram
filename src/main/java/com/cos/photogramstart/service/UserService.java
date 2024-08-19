package com.cos.photogramstart.service;

import java.util.Optional;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

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
	
	public User 회원프로필(int userid) {
		// SELECT * FROM image WHERE userid = :userid;
		User userEntity = userRepository.findById(userid).orElseThrow(() -> {
														// findById 를 사용할때 userid를 찾았을때
														// userid가 있는 경우와 없는 경우를 처리해줘야 하기 때문에
														// orElseThrow 를 사용해서 처리해줘야 한다.
														// orElseThrow는 화살표 함수를 사용하여 userid가 없을때 CustomException으로 처리한다.
			
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		return userEntity;
	}
	
//	public User 회원프로필(int userid) {
//		// SELECT * FROM image WHERE userid = :userid;
//		User userEntity = userRepository.findById(userid).orElseThrow(() -> {
//														// findById 를 사용할때 userid를 찾았을때
//														// userid가 있는 경우와 없는 경우를 처리해줘야 하기 때문에
//														// orElseThrow 를 사용해서 처리해줘야 한다.
//														// orElseThrow는 화살표 함수를 사용하여 userid가 없을때 CustomException으로 처리한다.
//			
//			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
//		});
//		
//		System.out.println("===============================================");
//		System.out.println(userEntity.getImages().get(0));
//		System.out.println("===============================================");
//		
//		return userEntity;
//	}
	
	
//	public void 회원프로필(int userid) {
//		// SELECT * FROM image WHERE userid = :userid;
//		User userEntity = userRepository.findById(userid).orElseThrow(() -> {
//														// findById 를 사용할때 userid를 찾았을때
//														// userid가 있는 경우와 없는 경우를 처리해줘야 하기 때문에
//														// orElseThrow 를 사용해서 처리해줘야 한다.
//														// orElseThrow는 화살표 함수를 사용하여 userid가 없을때 CustomException으로 처리한다.
//			
//			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
//		});
//		
//	
//	}
	
	
	
	
	
	
	
	
	
	
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
								// Optional<T> findById(ID id);
								// 1. 무조건 찾았으니 걱정마 -> get()
								// 2. 못찾아서 Exception 발동 -> orElseThrow()

		User userEntity = userRepository.findById(id).orElseThrow
				(
					//CustomValidationApiException 공통처리
					() ->
					{	
							return new CustomValidationApiException("찾을 수 없는 id입니다.");
							// CustomValidationApiException 클래스에 message만 받는 생성자 추가
					}
				);
		
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
	
	
	
	
	
//	@Transactional
//	public User 회원수정(int id, User user) {
//		// 1. 영속화
//								// Optional<T> findById(ID id);
//								// 1. 무조건 찾았으니 걱정마 -> get()
//								// 2. 못찾아서 Exception 발동 -> orElseThrow()
//		//User userEntity = userRepository.findById(10).get();
//														/*
//														 * 현재 DB에는 id값이 1인 데이터 밖에 없는 상태이며 
//														 * id값을 강제로 10으로 입력하고 실행하면 서버 오류가 발생한다.
//														 * userRepository.findById(10).get()
//														 * get()을 사용하는 것이 아니라 orElseThrow()를 사용해야 한다.
//														 */
//		
//		User userEntity = userRepository.findById(10).orElseThrow
//				(
//					//람다식으로 변경
//					() ->
//					{	
//							return new IllegalArgumentException("찾을 수 없는 id입니다.");
//					}
//				);
//		
//		// 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
//		userEntity.setName(user.getName());
//		
//		String rawPassword = user.getPassword();
//		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//		
//		//userEntity.setPassword(user.getPassword());
//		userEntity.setPassword(encPassword);
//		
//		userEntity.setBio(user.getBio());
//		userEntity.setWebsite(user.getWebsite());
//		userEntity.setPhone(user.getPhone());
//		userEntity.setGender(user.getGender());
//		
//		return userEntity;
//	}	// 더티체킹이 일어나서 업데이트가 완료됨
	
	
	
	
	
//	@Transactional
//	public User 회원수정(int id, User user) {
//		// 1. 영속화
//								// Optional<T> findById(ID id);
//								// 1. 무조건 찾았으니 걱정마 -> get()
//								// 2. 못찾아서 Exception 발동 -> orElseThrow()
//		//User userEntity = userRepository.findById(10).get();
//														/*
//														 * 현재 DB에는 id값이 1인 데이터 밖에 없는 상태이며 
//														 * id값을 강제로 10으로 입력하고 실행하면 서버 오류가 발생한다.
//														 * userRepository.findById(10).get()
//														 * get()을 사용하는 것이 아니라 orElseThrow()를 사용해야 한다.
//														 */
//		
//		User userEntity = userRepository.findById(10).orElseThrow
//				(
//					new Supplier<IllegalArgumentException>() 
//					{	// IllegalArgumentException
//						// id값이 10번을 잘못 넣었어. Argument가 잘못된 Exception이야.
//						// 빈공간에 Ctrl + Space 해서 Override를 해준다.
//						
//						@Override
//						public IllegalArgumentException get() {
//							return new IllegalArgumentException("찾을 수 없는 id입니다.");
//						}
//					}
//				);
//		
//		// 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
//		userEntity.setName(user.getName());
//		
//		String rawPassword = user.getPassword();
//		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//		
//		//userEntity.setPassword(user.getPassword());
//		userEntity.setPassword(encPassword);
//		
//		userEntity.setBio(user.getBio());
//		userEntity.setWebsite(user.getWebsite());
//		userEntity.setPhone(user.getPhone());
//		userEntity.setGender(user.getGender());
//		
//		return userEntity;
//	}	// 더티체킹이 일어나서 업데이트가 완료됨
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Transactional
//	public User 회원수정(int id, User user) {
//		// 1. 영속화
//		User userEntity = userRepository.findById(id).get();
//														// Optional<T> findById(ID id);
//														// 1. 무조건 찾았으니 걱정마 -> get()
//														// 2. 못찾아서 Exception 발동 -> orElseThrow()
//		
//		// 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
//		userEntity.setName(user.getName());
//		
//		String rawPassword = user.getPassword();
//		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//		
//		//userEntity.setPassword(user.getPassword());
//		userEntity.setPassword(encPassword);
//		
//		userEntity.setBio(user.getBio());
//		userEntity.setWebsite(user.getWebsite());
//		userEntity.setPhone(user.getPhone());
//		userEntity.setGender(user.getGender());
//		
//		return userEntity;
//	}	// 더티체킹이 일어나서 업데이트가 완료됨
	
	
	
	
	
	
	
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
