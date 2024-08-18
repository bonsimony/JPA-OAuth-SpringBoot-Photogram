package com.cos.photogramstart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data // @Data를 사용하여 Getter, Setter을 만들어 준다.
public class ImageUploadDto {

	// 파일을 받을때는 MiltipartiFile로 받아야 한다.
	// 리턴타입은 MiltipartiFile이다.
	private MultipartFile file;
	
	private String cation;
}
