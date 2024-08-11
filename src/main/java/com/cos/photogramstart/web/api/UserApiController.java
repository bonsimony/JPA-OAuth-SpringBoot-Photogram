package com.cos.photogramstart.web.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
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
	public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
										// UserUpdateDto가 아닌 userUpdateDto.toEntity()을 사용해야 한다.
										// UserService 클래스에 회원수정 함수에 2번째 인자는 User Object를 받기 때문이다.
		
		principalDetails.setUser(userEntity); // 세션 정보 변경
		
		return new CMRespDto<>(1, "회원수정완료", userEntity);
		
	}
	
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
