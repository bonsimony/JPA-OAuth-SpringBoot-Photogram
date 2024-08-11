package com.cos.photogramstart.web.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.web.dto.user.UserUpdateDto;

/*
	ajax로 인한 요청을 응답하기 위해서는
	파일이 아닌 데이터로 응답해줘야 한다.
	데이터로 응답하는 것을 API(Application Programming Interface) 서버라고 한다.
*/

//데이터로 응답하기 위해서는 @RestController를 사용한다.
//@RestController = @Controller + @RequestMapping + @ResponseBody
@RestController
public class UserApiController {
	
	@PutMapping("/api/user/{id}")
	public String update(UserUpdateDto userUpdateDto) {
		
		System.out.println("userUpdateDto");
		
		return "ok";
		
	}
}
