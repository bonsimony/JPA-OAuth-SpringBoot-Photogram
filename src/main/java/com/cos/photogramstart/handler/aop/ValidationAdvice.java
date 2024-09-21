package com.cos.photogramstart.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
		
		System.out.println("web api 컨트롤러 =========================");
		
		return proceedingJoinPoint.proceed(); // *** commentSave 함수가 실행됨
		// return proceedingJoinPoint.proceed()이란? 해당 함수로 다시 돌아가라는 것을 뜻한다.
	} 
	
	
	
	
	
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint)  throws Throwable{
		
		System.out.println("web 컨트롤러 =========================");
		
		return proceedingJoinPoint.proceed();
	}
	
}
