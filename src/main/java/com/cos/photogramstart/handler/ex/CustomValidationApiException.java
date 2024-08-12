package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;
	
	public CustomValidationApiException(String message, Map<String, String> errorMap){
		super(message);
		this.errorMap = errorMap;
		
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}

}
