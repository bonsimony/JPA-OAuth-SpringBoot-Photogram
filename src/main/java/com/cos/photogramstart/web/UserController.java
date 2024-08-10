package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

	// 브라우저 주소
	// http://localhost:8080/user/1
	// http://localhost:8080/user/2
	// http://localhost:8080/user/3
	// ... 
	// user의 id값 마다 해당 id 값에 해당하는 유저의 프로필 화면을 보여준다.
	@GetMapping("/user/{id}")
	public String profile
	(
			// GetMappring의 {id}는 변수를 뜻한다.
			// id 변수를 담기 위해 @PathVariable 을 사용한다.
			@PathVariable int id
			
	) 
	{
		return "user/profile";
	}
	
	
	@GetMapping("/user/{id}/update")
	public String update
	(
			// GetMappring의 {id}는 변수를 뜻한다.
			// id 변수를 담기 위해 @PathVariable 을 사용한다.
			@PathVariable int id
	) 
	{
		
		return "user/update";
	}
	
}
