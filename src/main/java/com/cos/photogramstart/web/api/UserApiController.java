package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
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
	
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
													@PathVariable int id
													
													, @Valid UserUpdateDto userUpdateDto
													, BindingResult bindingResult	// 꼭!!! @Valid가 적혀있는 다음 파라미터에 적어야됨!!!
													
													, @AuthenticationPrincipal PrincipalDetails principalDetails
												) 
	{
		
		
		/*
		 * UserUpdateDto 클래스에 @NotBlank 어노테이션이 적용되어 있는 name, password를
		 * UserApiController에서 update 함수 내 UserUpdateDto 객체 앞 @Valid 어노테이션 적용하여
		 * userUpdateDto 변수를 활용하여 BindingResult로 검증을 한다.
		 */
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
				
				System.out.println("===============================");
				System.out.println(error.getDefaultMessage());
				System.out.println("===============================");
			}
			
			throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
			
		}
		else {
			
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
											// UserUpdateDto가 아닌 userUpdateDto.toEntity()을 사용해야 한다.
											// UserService 클래스에 회원수정 함수에 2번째 인자는 User Object를 받기 때문이다.
			
			principalDetails.setUser(userEntity); // 세션 정보 변경
			
			return new CMRespDto<>(1, "회원수정완료", userEntity);
		}
		
	}
	
	
	
	
	
	
	
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
