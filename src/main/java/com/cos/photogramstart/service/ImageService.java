package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // ImageRepository 객체를 DI(Dependency Injection)를 하기 위해 
										// @RequiredArgsConstructor 어노테이션을 사용한다.
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	

//	private String uploadFolder = "C:/workspace/springbootwork/upload/";
// => 다양한 경로나 Service에서 사용한다면 파일 경로를 할때마다 정해져야 하기 때문에 yml.xml 에서 설정한 값을 가져와서 사용하도록 한다.
	
	
	
	
	
	/*
	 *  업로드한 파일 Path를 가져오기 위해 세팅한다.  
	 */
	@Value("${file.path}") // lombok이 아닌 springframework 어노테이션을 사용한다. 
	
//									   ${file.path}가 의미하는 것은 yml.xml 파일에서
//									   
//								       file:
//						    	          path: C:/workspace/springbootwork/upload/
//						    	          
//						    	      해당 부분을 말하는 것이다.
// 									  yml.xml 파일에서 file과 path는 정해져 있는 문법이 아닌 임의적으로 만들어서 사용하는 것이다.
						    	      
						    	      
	private String uploadFolder;
	
	
	public void 사진업로드(
									ImageUploadDto imageUploadDto
									, PrincipalDetails principalDetails
								) 
	{
		
		UUID uuid = UUID.randomUUID();
		/*
		 * 사용자가 "테스트.jpg" 파일을 두번 올렸을 경우에 서버에 있는 "테스트.jpg"가 덮어씌워진다. 
		 * UUID를 사용하여 이 문제를 해결한다. 
		 * UUID란 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약이다. 
		 * 주로 분산 컴퓨터 환경에서 사용되는 식별자이다.
		 * 중앙관리시스템이 있는 환경이라면 각 세션에 일련번호를 부여해줌으로써 유일성을 보장할 수 있겠지만 
		 * 중앙에서 관리되지 않는 분산 환경이라면 개별 시스템이 id를 발급하더라도 유일성이 보장되어야만 할 것이다. 
		 * 이를 위해 탄생한 것이 범용고유식별자 UUID(Universal Unique IDentifire) 이다.
		 */
		
		
		// String imageFileName = imageUploadDto.getFile().getOriginalFilename();
											// 예를들어, "테스트.jpg" 파일을 업로드 했을 경우에 "테스트.jpg"라는 해당 파일 이름을 가져온다.
		
		String imageFileName = uuid + "_" +imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지 파일 이름 :" + imageFileName);
		
		/* 
		 *  yml 파일에서 설정된 부분을 먼저 확인한다. 
		*/
//		servlet:
//		    multipart:
//		      enabled: true
//		      max-file-size: 2MB
//		      
//        file:
//    	    path: C:/workspace/springbootwork/upload/
		    	  
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		// 통신이 일어나거나 IO(하드디스크에 입력을 하거나 출력을 할때) -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		
		//System.out.println(imageEntity);
	}
}
