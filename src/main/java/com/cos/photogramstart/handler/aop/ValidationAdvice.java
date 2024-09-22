package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

// 여기서 말하는 Advice는 공통기능이라는 말이다!!!

@Component // @RestController, @Service 등 모든 것들이 @Component의 구현체이며
                    // @RestController, @Service 등 모든 것들은 @Component를 상속 받아서 만들어져 있다.
@Aspect
public class ValidationAdvice {
	
	
	
	// @Before은 어떤 함수가 실행되기 직전에 실행되는 것이다.
	// @After은 어떤 함수가 실행된 이후에 실행되는 것이다.
    // @Around는 어떤 함수가 실행되기 전과 후에 모두 관여하는 것이다.
	
	
	
	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	// execution(*)에서 *는 접근지정자인 public, private, protected, 접근지정자 없는 경우 모두 포괄한다는 뜻이다.
	/*
	 * execution(* com.cos.photogramstart.web.api.*Controller.*(..))에서 (..)를 부분에서 *은 모든
	 * 함수(메소드)를 말하는 것이고 (..)은 파라미터가 무엇이든 상관 없다는 말이다.
	 */
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		/*
		 * CommentApiController 내에 commentSave 함수가 실행한다고 가정을 하면 
		 * commentSave 함수를 확인하고 
		 * ProceedingJoinPoint proceedingJoinPoint 이렇게 매개변수에 적어놓으면 
		 * commentSave 함수 파라미터 
		 * 뿐만 아니라 
		 * 내부정보까지 접근할 수 있는 파라미터가 ProceedingJoinPoint proceedingJoinPoint인 것이다.
		 */
		
		// commentSave 함수를 실행하면 commentSave 함수부터 실행되는 것이 아니라
		// commentSave 함수의 파라미터부터 내부정보까지 담고 apiAdvice 함수의 내부를 실행하는 것이다!!! 
		
		/*********************************************************************/
		
		// *** proceedingJoinPoint => commentSave 함수의 모든 곳에 접근할 수 있는 변수
		// *** commentSave 함수보다 먼저 실행
		
		/*********************************************************************/
		
		//System.out.println("web api 컨트롤러 =========================");
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			//System.out.println(arg);
			
			if(arg instanceof BindingResult) {
				// System.out.println("유효성 검사를 하는 함수 입니다.");
			
				
				
			BindingResult bindingResult = (BindingResult) arg; // BindingResult로 다운캐스팅 한다.
				
				
				
				
				
				if(bindingResult.hasErrors()) {
					
					//System.out.println("================");
					//System.out.println("bindingResult.hasErrors()");
					//System.out.println("================");
					
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						
						errorMap.put(error.getField(), error.getDefaultMessage());
						
						
					}
					
					throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
					
				}
				
				
				
				
				
				
			}
		}
		
		//System.out.println("=================================");
		//System.out.println("return proceedingJoinPoint.proceed() 전까지 왔는지 확인");
		//System.out.println("=================================");
		
		return proceedingJoinPoint.proceed(); // *** commentSave 함수가 실행됨
		// return proceedingJoinPoint.proceed()이란? 해당 함수로 다시 돌아가라는 것을 뜻한다.
		
		
	} 
	
	
	
	
	
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint)  throws Throwable{
		
		//System.out.println("web 컨트롤러 =========================");
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			// System.out.println(arg);
			
			if(arg instanceof BindingResult) {
				
				//System.out.println("유효성 검사를 하는 함수 입니다.");
				
				BindingResult bindingResult = (BindingResult) arg; // BindingResult로 다운캐스팅 한다.
				
				
				
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					// 에러가 나면 BindingResult의 getFieldErrors 컬렉션에 모아준다.
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						System.out.println("===================");
						System.out.println(error.getDefaultMessage());
						System.out.println("===================");
					}
					
					throw new CustomValidationException("유효성 검사 실패함", errorMap);
					
				}
				
				
				
				
				
			}
		}
		
		return proceedingJoinPoint.proceed();
	}
	
}
