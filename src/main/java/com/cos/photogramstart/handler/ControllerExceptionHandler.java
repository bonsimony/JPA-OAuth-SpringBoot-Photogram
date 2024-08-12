package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;


@RestController // @RestController을 통해 데이터를 응답하게 한다.
						// @RestContoller = @Controller + @ResponseBody
@ControllerAdvice // @ControllerAdive 어노테이션으로 모든 exception 처리가 가능하다.
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		
			System.out.println("============validationApiException=============");
			
			
			// CMRespDto, Script 비교
			// 1. 클라이언트에게 응답할때는 Script
			// 2. Ajax 통신 - CMRespDto (개발자 응답)
			// 3. Android 통신 - CMRespDto (개발자 응답)
			
			
			/* 
			 * 	HttpStatus 상태코드 처리 : ResponseEntity 
			 */
			return new ResponseEntity<>(
															new CMRespDto<>(-1, e.getMessage(), e.getErrorMap())
															, HttpStatus.BAD_REQUEST 
														);
			
		}
	
	
	
	
	
	
//	@ExceptionHandler(CustomValidationApiException.class)
//	public CMRespDto<?> validationApiException(CustomValidationApiException e) {
//			
//			// CMRespDto, Script 비교
//			// 1. 클라이언트에게 응답할때는 Script
//			// 2. Ajax 통신 - CMRespDto (개발자 응답)
//			// 3. Android 통신 - CMRespDto (개발자 응답)
//		
//			return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
//	
//		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
			
			// CMRespDto, Script 비교
			// 1. 클라이언트에게 응답할때는 Script
			// 2. Ajax 통신 - CMRespDto (개발자 응답)
			// 3. Android 통신 - CMRespDto (개발자 응답)
		
			return Script.back(e.getErrorMap().toString());

// Script 클래스 back 함수 static이 선언되어 있지 않을때 객체를 생성한 후 진행해야 한다.
// 반대로 Script 클래스 back 함수에 static 선언되어 있다면 객체를 생성하지 않고 곧바로 사용이 가능하다.
//			Script sc = new Script();
//			return sc.back(e.getErrorMap().toString());
		}
	
	
	
	
	
	
//	@ExceptionHandler(CustomValidationException.class)
//	public CMRespDto<?> validationException(CustomValidationException e) {
//			// 제네릭에 어떤 타입을 전달해야할지 모를때 "?"을 사용하면 알아서 찾아간다.
//			// 제네릭을 사용하여 리턴할때 "?"를 사용하면 편하다.
//		
//			return new CMRespDto<Map<String,String>>(-1, e.getMessage(), e.getErrorMap());
//		}

	
	
	
	
	
//	@ExceptionHandler(CustomValidationException.class)
//	public CMRespDto<Map<String, String>> validationException(CustomValidationException e) {
//			
//			return new CMRespDto(-1, e.getMessage(), e.getErrorMap());
//		}
	
	
	
	
	
	
//	@ExceptionHandler(CustomValidationException.class)
//	public CMRespDto validationException(CustomValidationException e) {
//			
//			return new CMRespDto(e.getMessage(), e.getErrorMap());
//		}
	
	
	
	
	
	
//	@ExceptionHandler(CustomValidationException.class)
//	public Map<String, String> validationException(CustomValidationException e) {
//			
//			return e.getErrorMap();
//		}
	
	
	
	
	
	
	
	//@ExceptionHandler(RuntimeException.class)
	/*
	 * @ExceptionHandler 어노테이션 그리고 RuntimeException.class릁 통해 모든 RuntimeException 을
	 * ControllerExceptionHandler을 통해서 가능하게 한다.
	 */
//	public String validationException(RuntimeException e) {
//		
//		return e.getMessage();
//	}
	
	
	
	
	
}
