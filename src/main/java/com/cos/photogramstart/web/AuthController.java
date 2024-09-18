package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 필드를 DI(Dependency Injection)할때 사용

@Controller // 1. Ioc  
				  // 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
		private static final Logger log = LoggerFactory.getLogger(AuthController.class);
		
		/*
		 * AuthService 객체를 사용하기 위해 DI(Dependency Injection) 해서 불러온다. DI(Dependency
		 * Injection)을 해서 불러올때 2가지 방법이 있다. 1. @Autowired 어노테이션을 사용한다. 2. AuthController의
		 * 생성자를 만든다.
		 */
		
		// 1. @Autowired
		//private AuthService authService;
		
		
		// 2. 생성자
		// private AuthService authService;
		// public AuthController(AuthService authService) {
		//	this.authService = authService;
		// }
		
		/*
		 * 자바에서 전역변수에 final을 사용하면 오류가 나는데 
		 * final 필드는 무조건 생성자나 객체를 실행할때 초기화를 해줘야한다.
		 * @RequiredArgsConstructor을 어노테이션을 사용하면 
		 * final 필드가 명시되어 있는 모든 부분에 대해 생성자를 만들어준다.
		 */
		 
		private final AuthService authService;
		
		@GetMapping("/auth/signin")
		public String signinForm() {
			
			return "auth/signin";
		}
		
		@GetMapping("/auth/signup")
		public String signupForm() {
			
			return "auth/signup";
		}

		
/****************************************************************/
		
//		// 회원가입 -> /auth/signup -> /auth/signin
//		@PostMapping("/auth/signup")
//		public @ResponseBody String signup(
//				/*
//				 * 컨트롤러이지만 @ResponseBody 어노테이션을 활용하면 
//				 * return 타입이 파일이 아닌 데이터 값을 리턴한다.
//				 */
//											@Valid SignupDto signupDto
//											, BindingResult bindingResult
//										) 
//		{
//			
//			if(bindingResult.hasErrors()) {
//				Map<String, String> errorMap = new HashMap<>();
//				
//				// 에러가 나면 BindingResult의 getFieldErrors 컬렉션에 모아준다.
//				for(FieldError error : bindingResult.getFieldErrors()) {
//					errorMap.put(error.getField(), error.getDefaultMessage());
//					System.out.println("===================");
//					System.out.println(error.getDefaultMessage());
//					System.out.println("===================");
//				}
//				
//				return "오류남";
//				
//			}
//			else
//			{
//				
//				User user = signupDto.toEntity();
//				User userEntity = authService.회원가입(user);
//				
//				System.err.println(userEntity);
//				
//				return "auth/signin";
//				
//			}
//			
//			// username 길이 20 초과 - Validation 체크
////			if(signupDto.getUsername().length() > 20){
////				
////				System.out.println("username 갈이 20 초과");
////			
////			}
////			else
////			{
////				User user = signupDto.toEntity();
////				User userEntity = authService.회원가입(user);
////			}
//			
//			
//			
//			/*
//			 * signup.jsp 파일에서 회원가입 form 태그 내 action과 method 속성 action = "/auth/signup"
//			 * method = "post" username, password, email, name이라는 name 값이 key가 되고 input box에
//			 * 입력된 값은 value가 된다. 데이터가 {key : value} 형식으로 들어오게 되는데 이 방식을
//			 * x-www-form-url-encoded라고 한다.
//			 */
//		
//			//log.info(signupDto.toString());
//			// 문자열만 받을 수 있어서 toString()으로 형변환을 해준다.
//			
//			
//		     
//			//System.out.println("siginup 살행됨?");
//			
//			
//			//User <- SignupDto
//			//User user = signupDto.toEntity();
//			//log.info(user.toString());
//			
//			//User userEntity = authService.회원가입(user);
//			//System.out.println(userEntity);
//			
//			//return "auth/signin";
//			// 회원가입을 성공하면 로그인 화면으로 이동
//		}
		
/****************************************************************/	

//		// 회원가입 -> /auth/signup -> /auth/signin
		@PostMapping("/auth/signup")
		public String signup(
											@Valid SignupDto signupDto
											, BindingResult bindingResult
										) 
		{

			
//			if(bindingResult.hasErrors()) {
//				Map<String, String> errorMap = new HashMap<>();
//				
//				// 에러가 나면 BindingResult의 getFieldErrors 컬렉션에 모아준다.
//				for(FieldError error : bindingResult.getFieldErrors()) {
//					errorMap.put(error.getField(), error.getDefaultMessage());
//					System.out.println("===================");
//					System.out.println(error.getDefaultMessage());
//					System.out.println("===================");
//				}
//			}
			
			// username 길이 20 초과 - Validation 체크
//			if(signupDto.getUsername().length() > 20){
//				
//				System.out.println("username 갈이 20 초과");
//			
//			}
//			else
//			{
//				User user = signupDto.toEntity();
//				User userEntity = authService.회원가입(user);
//			}
			
			

			
			if(bindingResult.hasErrors()) {
				Map<String, String> errorMap = new HashMap<>();
				
				// 에러가 나면 BindingResult의 getFieldErrors 컬렉션에 모아준다.
				for(FieldError error : bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
					System.out.println("===================");
					System.out.println(error.getDefaultMessage());
					System.out.println("===================");
				}
				
				/*
				 * SignupDto 클래스에서 유효성 검사가 하나라도 실패하면 
				 * bindingResult 변수에 담기게 되고 
				 * bindingResult에 에러가 하나라도 있으면 
				 * HashMap<>에 모두 담고 throw를 통해 exception을 강제로 발동시켜서 에러를 던진다.
				 */
				throw new CustomValidationException("유효성 검사 실패함", errorMap);
				
				//throw new RuntimeException("유효성 검사 실패함");
				/*
				 * RuntimeException을 ControllerExceptionHandler 클래스에서 
				 * validationException 함수 값으로 리턴이 되어 화면에 표출
				 */
			}
			else
			{
				
				User user = signupDto.toEntity();
				User userEntity = authService.회원가입(user);
				
				System.out.println(userEntity);
				
				return "auth/signin";
				
			}
			
			
		     
			//System.out.println("siginup 살행됨?");
			
			
			//User <- SignupDto
//			User user = signupDto.toEntity();
//			log.info(user.toString());
//			
//			User userEntity = authService.회원가입(user);
//			System.out.println(userEntity);
//			
//			return "auth/signin";
			// 회원가입을 성공하면 로그인 화면으로 이동
		}
		
	

		}		




/*
 * 스프링은 IoC 컨테이너로 빈을 관리한다. 스프링은 DI를 사용한다. DI 방법에는 생성자 주입, setter 주입, 필드 주입 등이
 * 있다.
 */
