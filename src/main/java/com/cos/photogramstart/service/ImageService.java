package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	// import org.springframework.transaction.annotation.Transactional;
			@Transactional(readOnly = true)
			public Page<Image> 이미지스토리(int principalId, Pageable pageable){
				Page<Image> images = imageRepository.mStroy(principalId, pageable);
				
				
				/*
				 * 2(cos) 로그인하면 2번이 구독한 이미지들이 쭉 보이게 되고 그 이미지들을 for문을 돌려 하나씩 뽑아내고 첫번째 이미지에 대한 좋아요
				 * 정보를 가져와서 그 좋아요가 2(cos)가 좋아요 했는지 확인하면 된다.
				 */
				
				// images에 좋아요 상태 담기
				images.forEach((image)->{
					
					image.getLikes().forEach((like) -> {
						if(like.getUser().getId() == principalId) { // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한것이지 비교
							image.setLikeState(true);
						}
					});
					
				});
				
				
				return images;
			}
	
	
	// import org.springframework.transaction.annotation.Transactional;
//		@Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서 더티체킹을 하고 데이터베이스 flush(반영)를 하는데
//													//  readOnly = true 이렇게 설정하면 읽기전용이기 때문에 데이터베이스에 반영하는 것을 하지 않는다.
//		public Page<Image> 이미지스토리(int principalId, Pageable pageable){
//			Page<Image> images = imageRepository.mStroy(principalId, pageable);
//			return images;
//		}
	
		
	// import org.springframework.transaction.annotation.Transactional;
//	@Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서 더티체킹을 하고 데이터베이스 flush(반영)를 하는데
//												//  readOnly = true 이렇게 설정하면 읽기전용이기 때문에 데이터베이스에 반영하는 것을 하지 않는다.
//	public List<Image> 이미지스토리(int principalId){
//		List<Image> images = imageRepository.mStroy(principalId);
//		return images;
//	}

	
	
	
	
	
	
	
	
	
	
	@Value("${file.path}") // lombok이 아닌 springframework 어노테이션을 사용한다.
	private String uploadFolder;
	
	// import org.springframework.transaction.annotation.Transactional;
	@Transactional// Service에서 데이터베이스에 영향을 준다면 무조건 @Transaction 어노테이션을 사용해야 한다!!!
						   // 그 이유는?
						   // 송금 서비스가 있다고 가정하자!
						  // 계좌 테이블에 id, money, userid 컬럼이 있으며 userid가 2번과 1번이 있을때
	                      // 유저 2번은 id값 1, money 10000, userid 2
						  // 유저 1번은 id값 2, money 30000, userid 1 
						  // Service에 송금이라는 함수가 있다.
	 					  // 유저 2번이 유저 1번한테 5000원을 송금한다고 하면 유저 1번은 money 값이 35000원이 된다.
						  // 유저 2번 money를 update를 해야하고  유저 1번 money를 update를 해야한다.
						  // 이때 update가 두번 이루어져야 하는데 
						  // 유저 2번의 money 값이 10000에서 5000으로 update 성공하고 유저 1번의 money 값이 30000에서 35000으로 update 실패했다면 
						  // 유저 1번의 money 값은 그대로 30000원이 될것이다.
						  // 사진 업로드같은 경우에는 한번에 하나의 insert가 되기 때문에 @@Transactional 어노테이션을 사용하지 않더라도 잘된다.
						  // 하지만 여러 insert나 update가 있다고 가정할때 여러 트랜잭션이 있는 것이고 
	  					  // 트랜잭션이란 일의 최소단위라고 한다.
						  // @@Transactional 어노테이션을 사용하면 내부적으로 트랜잭션을 타게 되는데
						  // 트랜잭션을 타게되면 여러 트랜잭션 중에 하나가 실패하면 전부 rollback을 시키고 원상복귀 시킨다.
						  // 모든 트랜잭션이 성공을 하게 되면 정상적으로 commit을 하게 된다.
						 //  Service단에서는 @@Transactional 어노테이션을 까먹지 말자!!!!!
	   					 // SELECT가 되는 함수 위에는 @@Transactional(readOnly = true) 로 설정해야 한다!!!
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

		UUID uuid = UUID.randomUUID();
		
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지 파일 이름 :" + imageFileName);

		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		imageRepository.save(image);

	}

/****************************************************************************************************************************************************************/

////	private String uploadFolder = "C:/workspace/springbootwork/upload/";
//// => 다양한 경로나 Service에서 사용한다면 파일 경로를 할때마다 정해져야 하기 때문에 yml.xml 에서 설정한 값을 가져와서 사용하도록 한다.
//	
//	/*
//	 *  업로드한 파일 Path를 가져오기 위해 세팅한다.  
//	 */
//	@Value("${file.path}") // lombok이 아닌 springframework 어노테이션을 사용한다. 
//	
////									   ${file.path}가 의미하는 것은 yml.xml 파일에서
////									   
////								       file:
////						    	          path: C:/workspace/springbootwork/upload/
////						    	          
////						    	      해당 부분을 말하는 것이다.
//// 									  yml.xml 파일에서 file과 path는 정해져 있는 문법이 아닌 임의적으로 만들어서 사용하는 것이다.
//						    	      
//						    	      
//	private String uploadFolder;
//	
//	
//	public void 사진업로드(
//									ImageUploadDto imageUploadDto
//									, PrincipalDetails principalDetails
//								) 
//	{
//		
//		UUID uuid = UUID.randomUUID();
//		/*
//		 * 사용자가 "테스트.jpg" 파일을 두번 올렸을 경우에 서버에 있는 "테스트.jpg"가 덮어씌워진다. 
//		 * UUID를 사용하여 이 문제를 해결한다. 
//		 * UUID란 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약이다. 
//		 * 주로 분산 컴퓨터 환경에서 사용되는 식별자이다.
//		 * 중앙관리시스템이 있는 환경이라면 각 세션에 일련번호를 부여해줌으로써 유일성을 보장할 수 있겠지만 
//		 * 중앙에서 관리되지 않는 분산 환경이라면 개별 시스템이 id를 발급하더라도 유일성이 보장되어야만 할 것이다. 
//		 * 이를 위해 탄생한 것이 범용고유식별자 UUID(Universal Unique IDentifire) 이다.
//		 */
//		
//		
//		// String imageFileName = imageUploadDto.getFile().getOriginalFilename();
//											// 예를들어, "테스트.jpg" 파일을 업로드 했을 경우에 "테스트.jpg"라는 해당 파일 이름을 가져온다.
//		
//		String imageFileName = uuid + "_" +imageUploadDto.getFile().getOriginalFilename();
//		System.out.println("이미지 파일 이름 :" + imageFileName);
//		
//		/* 
//		 *  yml 파일에서 설정된 부분을 먼저 확인한다. 
//		*/
////		servlet:
////		    multipart:
////		      enabled: true
////		      max-file-size: 2MB
////		      
////        file:
////    	    path: C:/workspace/springbootwork/upload/
//		    	  
//		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
//		
//		// 통신이 일어나거나 IO(하드디스크에 입력을 하거나 출력을 할때) -> 예외가 발생할 수 있다.
//		try {
//			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		//Image 테이블에 저장
//		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
//		Image imageEntity = imageRepository.save(image);
//		
//		
//		//System.out.println(imageEntity);
//			/*
//			 * 
//			 * 사진 업로드 시 System.out.println(imageEntity); 때문에 오류가 난다
//			 * 왜냐하면?
//			 * Image 객체를 System.out.println 하면 Image 객체의 private로 캡슐화된 getter들이 모두 출력된다.
//			 * private User user; 
//			 * User 객체를 살펴보면 User 객체의 private로 캡슐화된 getter들이 모두 출력되고
//			 * private List<Image> images; 
//			 * List 타입인 Image를 출력되며 Image 출력하면 또 User 객체를 출력하고
//			 * User 객체를 출력하면 다시 Image 객체를 출력하게 된다.
//			 * System.out.println로 객체(Object)를 출력하면 toString() 붙어서 
//			 * 실제로는 imageEntity.toString()이 출력이 된다.
//			 * @Data 어노테이션을 사용하면 toString() 함수가 자동으로 생성된다.
//			 * 
//			 * 무한참조가 일어난 것이다!!!!
//			 * JPA를 할때 객체(object)를 출력할때 조심해야 한다!!!
//			 * 
//			 * */
//		
//		
//		
//		
//		System.out.println("==========================================");
//		
//			/*
//			 * System.out.println(imageEntity.toString()); ==  System.out.println(imageEntity);
//			 */
//		//System.out.println(imageEntity.toString());
//		System.out.println(imageEntity);
//		
//		System.out.println("==========================================");
//	}

	/****************************************************************************************************************************************************************/

}
