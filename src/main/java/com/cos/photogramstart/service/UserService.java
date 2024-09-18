package com.cos.photogramstart.service;

import java.util.Optional;
import java.util.function.Supplier;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

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
	
	private final SubscribeRepository subscribeRepository;

	@Transactional(readOnly = true) 
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == principalId);
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        
		dto.setSubscribeState(subscribeState == 1);
		dto.setSubscribeCount(subscribeCount);
		
		return dto;
	}	
	
//		@Transactional(readOnly = true) 
//		public UserProfileDto 회원프로필(int pageUserId, int principalId) {
//			
//			UserProfileDto dto = new UserProfileDto();
//			
//			
//			// SELECT * FROM image WHERE userid = :userid;
//			User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
//				throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
//			});
//			
//			dto.setUser(userEntity);
////			dto.setIsPageOwner(pageUserId == principalId ? 1 : -1); // 삼항 연산자 
//														                                // 1은 페이지 주인, -1은 주인이 아님
//			
//			dto.setPageOwnerState(pageUserId == principalId);
//			dto.setImageCount(userEntity.getImages().size());
//												  
//			return dto;
//		}	
	
	
//	// import org.springframework.transaction.annotation.Transactional; 
//	@Transactional(readOnly = true) // SELECT할때 @Transactional(readOnly = true)를 사용하는 이유는?
//													// 유저 1번 정보를 update를 할때 유저 1번 정보를 들고오게 되는데
//													// Repository에서 영속성컨텍스트로 요청을 했을때 
//													// 영속성컨텍스트에 유저 1번의 정보가 없다면 
//													// 영속성컨텍스트에서 다시 DataBase에게 요청을 해서 유저 1번의 정보를 가져오고
//													// 영속성컨텍스트에서 Repository로 유저1번의 정보를 가져온다.
//													// Repository로 유저 1번의 정보를 가져왔다면 스프링컨테이너로 가져온 것이기 때문에
//													// Service로 보낼 수 있고 그 다음엔 Controller로 보낼 수 있다.
//													// 이때 영속성컨텍스트 정보와 Repository 정보와 Data정보는 다 동기화
//													// 즉, 똑같은 상태이다.
//													// 이 상태에서 Service에서 에서 유저1번 정보에 username이 ssar이었는데 cos로 변경하면 
//													// Repository도 동일하게 유저 1번의 username이 cos로 변경되고 
//													// 영속성컨텍스트는 Repository를 참조하고 있기 때문에 동일하게 username이 cos로 변경된다.
//													// Controller -> Service -> Repository -> 영속성컨텍스트 -> DataBase 해당 순으로 요청이 진행되고 
//													// DataBase -> 영속성컨텍스트 -> Repository -> Service -> Controller 순으로 응답을 하게 되는데
//													// 응답을 할때 Service -> Controller 해당 시점에서 영속성컨텍스트는 변경된 오브젝트 DataBase에 flush를 하게 된다.
//													// flush는 유저 1번 username이 ssar에서 cos로 변경된 정보를 DataBase로 집어넣는다는 말이다. 비워낸다.
//													// 영속성컨텍스트는 응답을 하는 과정에서 Service가 끝나는 시점에서 변경된 오브젝트를 감지해야 하는데
//													// 변경이 되어야지 영속성컨텍스트를 통해 flush해서 DataBase에서 값이 변경되는데
//													// 변경이 되기전 계속해서 감지를 하게 되고 연산을 하게 된다.
//													// 변경이 감지되면 영속성컨텍스트에서 flush에서 DataBase로 변경을 하게 되면 더티체킹이 일어난다.
//													// @Transactional(readOnly = true) 어노테이션을 사용하면 변경이 되었는지 안되었는지 감지를 하지 않는다.
//												    // 읽기전용이기 때문에 변경감지를 하지 않는다.
//												    // 조금 더 영속성컨텍스트 JPA가 일을 더 적게 할 수 있다.
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
//		return userEntity;
//	}
	
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
