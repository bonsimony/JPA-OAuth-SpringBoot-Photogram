package com.cos.photogramstart.handler.ex;

import java.util.Map;


// exception이 되기 위해서는 RuntimeException을 상속받으면 된다.
public class CustomValidationException extends RuntimeException {
	
	/*
	 * CustomValidationException 클래스에 마우스 커서를 옮긴 후에 
	 * Add default serial version ID를 클릭한다.
	 */
	
	//객체를 구분할 때 사용된다.
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;
	
	// 생성자 생성
	public CustomValidationException(String message, Map<String, String> errorMap) {
		
		// 상속받은 RuntimeException에 message 값을 보낸다.
		super(message);
		this.errorMap = errorMap;
		
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}


	
	
	
}
