package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

/*
	ajax로 인한 요청을 응답하기 위해서는
	파일이 아닌 데이터로 응답해줘야 한다.
	데이터로 응답하는 것을 API(Application Programming Interface) 서버라고 한다.
*/

//final이 있는 메소드의 생성자를 만들기위해 @RequiredArgsConstructor 어노테이션을 사용한다.
@RequiredArgsConstructor


//데이터로 응답하기 위해서는 @RestController를 사용한다.
//@RestController = @Controller + @RequestMapping + @ResponseBody
@RestController
public class UserApiController {
	
	// 회원정보 수정에 대한 service를 DI(Dependecy Injection)를 해준다.
	private final UserService userService;
	
	private final SubscribeService subscribeService;
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate
	(
		@PathVariable int principalId
																	
		, MultipartFile profileImageFile
		// MultipartFile의 변수값은 profile.jsp 파일에 있는 name 값을 똑같이 써줘야 한다!!!!!
		
		, @AuthenticationPrincipal PrincipalDetails principalDetails
	)
	{
		
		User userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
		principalDetails.setUser(userEntity);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "프로필사진변경 성공", null), HttpStatus.OK);
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<SubscribeDto> subscribeDto = subscribeService. 구독리스트(principalDetails.getUser().getId(), pageUserId);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 리스트 가져오기 성공", subscribeDto), HttpStatus.OK);
	}
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
													@PathVariable int id
													
													, @Valid UserUpdateDto userUpdateDto
													, BindingResult bindingResult	// 꼭!!! @Valid가 적혀있는 다음 파라미터에 적어야됨!!!
													
													, @AuthenticationPrincipal PrincipalDetails principalDetails
												) 
	{
		
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
				
				
			}
			
			throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
			
		}
		else {
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			principalDetails.setUser(userEntity); // 세션 정보 변경
			return new CMRespDto<>(1, "회원수정완료", userEntity); // 응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
																							// 메시지컨버터가 JSON으로 변환되어서 응답해줄때!!!
																							// -> User 객체 내 images에 @JsonIgnoreProperties 어노테이션으로 막아준다!!!
																							// -> @JsonIgnoreProperties({"user"})
																							//
																							// User 객체 내부에 private List<Image> images;
																							// images의 getter를 호출하게 되면 
																							// private List<Image> images; 에 대한 정보들을 LAZY 로딩으로 모두 호출하는 것이다.
																							// Image 객체 내 모든 getter를 호출하고 private User user; 로 인하여 
																							// User 객체를 또 호출하게 된다.
																							// 무한참조가 발생한다.
		}
		
	}
	
	
	
	
	
//	@PutMapping("/api/user/{id}")
//	public CMRespDto<?> update(
//													@PathVariable int id
//													
//													, @Valid UserUpdateDto userUpdateDto
//													, BindingResult bindingResult	// 꼭!!! @Valid가 적혀있는 다음 파라미터에 적어야됨!!!
//													
//													, @AuthenticationPrincipal PrincipalDetails principalDetails
//												) 
//	{
//		
//		
//		/*
//		 * UserUpdateDto 클래스에 @NotBlank 어노테이션이 적용되어 있는 name, password를
//		 * UserApiController에서 update 함수 내 UserUpdateDto 객체 앞 @Valid 어노테이션 적용하여
//		 * userUpdateDto 변수를 활용하여 BindingResult로 검증을 한다.
//		 */
//		if(bindingResult.hasErrors()) {
//			Map<String, String> errorMap = new HashMap<>();
//			
//			for(FieldError error : bindingResult.getFieldErrors()) {
//				
//				errorMap.put(error.getField(), error.getDefaultMessage());
//				
//				System.out.println("===============================");
//				System.out.println(error.getDefaultMessage());
//				System.out.println("===============================");
//			}
//			
//			throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
//			
//		}
//		else {
//			
//			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
//											// UserUpdateDto가 아닌 userUpdateDto.toEntity()을 사용해야 한다.
//											// UserService 클래스에 회원수정 함수에 2번째 인자는 User Object를 받기 때문이다.
//			
//			principalDetails.setUser(userEntity); // 세션 정보 변경
//			
//			return new CMRespDto<>(1, "회원수정완료", userEntity);
//		}
//		
//	}
	
	
	
	
	
	
	
//	@PutMapping("/api/user/{id}")
//	public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//		
//		User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
//										// UserUpdateDto가 아닌 userUpdateDto.toEntity()을 사용해야 한다.
//										// UserService 클래스에 회원수정 함수에 2번째 인자는 User Object를 받기 때문이다.
//		
//		principalDetails.setUser(userEntity); // 세션 정보 변경
//		
//		return new CMRespDto<>(1, "회원수정완료", userEntity);
//		
//	}
	
	/*
	 * @PutMapping("/api/user/{id}") public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto) {
	 * 
	 * User userEntity = userService.회원수정(id, userUpdateDto.toEntity()); 
	 * 																//UserUpdateDto가 아닌 userUpdateDto.toEntity()을 사용해야 한다. 
	 * 																// UserService 클래스에 회원수정 함수에 2번째 인자는 User Object를 받기 때문이다.
	 * 
	 * return new CMRespDto<>(1, "회원수정완료", userEntity);
	 * 
	 * }
	 */
	
	
	
	
	
	
	
	/*
	 * @PutMapping("/api/user/{id}") 
	 * public String update(@PathVariable int id, UserUpdateDto userUpdateDto) {
	 * 
	 * User userEntity = userService.회원수정(id, userUpdateDto.toEntity()); 
	 * 																//UserUpdateDto가 아닌 userUpdateDto.toEntity()을 사용해야 한다. 
	 * 																// UserService 클래스에 회원수정 함수에 2번째 인자는 User Object를 받기 때문이다. 
	 * 
	 * return "ok";
	 * 
	 * }
	 */
	
	
	
	
	
	
	
	/*
	 * @PutMapping("/api/user/{id}") public String update(@PathVariable int id,
	 * UserUpdateDto userUpdateDto) {
	 * 
	 * System.out.println("userUpdateDto");
	 * 
	 * return "ok";
	 * 
	 * }
	 */
	
	
	
	
	
	
	
	/*
	 * @PutMapping("/api/user/{id}") public String update(UserUpdateDto
	 * userUpdateDto) {
	 * 
	 * System.out.println("userUpdateDto");
	 * 
	 * return "ok";
	 * 
	 * }
	 */
}
