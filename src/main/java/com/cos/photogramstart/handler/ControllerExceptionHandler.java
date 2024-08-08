package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;


@RestController // @RestController을 통해 데이터를 응답하게 한다.
						// @RestContoller = @Controller + @ResponseBody
@ControllerAdvice // @ControllerAdive 어노테이션으로 모든 exception 처리가 가능하다.
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
			// 제네릭에 어떤 타입을 전달해야할지 모를때 "?"을 사용하면 알아서 찾아간다.
			// 제네릭을 사용하여 리턴할때 "?"를 사용하면 편하다.
		
			return new CMRespDto<Map<String,String>>(-1, e.getMessage(), e.getErrorMap());
		}

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
