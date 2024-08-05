package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller // 1. Ioc  
				  // 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
		private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
		@GetMapping("/auth/signin")
		public String signinForm() {
			
			return "auth/siginin";
		}
		
		@GetMapping("/auth/signup")
		public String signupForm() {
			
			return "auth/signup";
		}
		
		// 회원가입 -> /auth/signup -> /auth/signin
		@PostMapping("/auth/signup")
		public String signup(SignupDto signupDto) {
			
			/*
			 * signup.jsp 파일에서 회원가입 form 태그 내 action과 method 속성 action = "/auth/signup"
			 * method = "post" username, password, email, name이라는 name 값이 key가 되고 input box에
			 * 입력된 값은 value가 된다. 데이터가 {key : value} 형식으로 들어오게 되는데 이 방식을
			 * x-www-form-url-encoded라고 한다.
			 */
		
			log.info(signupDto.toString());
			// 문자열만 받을 수 있어서 toString()으로 형변환을 해준다.
			
			
		     
			
			//System.out.println("siginup 살행됨?");
			
			return "auth/signin";
			// 회원가입을 성공하면 로그인 화면으로 이동
		}
		
		//

}
