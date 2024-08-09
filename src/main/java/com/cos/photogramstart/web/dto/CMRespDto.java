package com.cos.photogramstart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {
	private int code; // 1(성공), -1(실패)
	private String message;
	private T data;

}

//public class CMRespDto{
//
//	private String message;
//	private Map<String, String> errorMap;
//	
//
//}
