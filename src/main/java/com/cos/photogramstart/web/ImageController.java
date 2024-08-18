package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	
	private final ImageService imageService;

	@GetMapping(
								{
									"/"
									, "/image/story"
								}
						)
	public String story() {
		
		return "image/story";
		
	}
	
	
	@GetMapping("/image/popular")
	public String popular() {
		
		return "image/popular";
	}
	
	
	@GetMapping("/image/upload")
	public String upload() {
		
		return "image/upload";
	}
	
	
	@PostMapping("/image")
	public String imageUpload(
											ImageUploadDto imageUploadDto
											, @AuthenticationPrincipal PrincipalDetails principalDetails
											/*
											 *  jsp 파일에서 name 값을 TransferObejct를 통해 imageUploadDto 값을 받고
											 *  UserDetails를 상속받은 PrincipalDetails를 통해 세션 값을 받는다.
											 * 
											 * */
										  ) 
	{
		// 서비스 호출
		// Controller는 사용자로부터 데이터를 받아 Service로 보내주면 역할을 다한 것이다.
		imageService.사진업로드(imageUploadDto, principalDetails);
		
		return "redirect:/user/" + principalDetails.getUser().getId();
	}
	
}

// 업로드 파일을 프로젝트 외부에 두는 이유?
//workspace 안에 photogram을 만들었다.
//photogram 이라는 폴더와 uplodate 폴더가 있을 것이다.
//photogram 이라는 폴더 내 static 폴더 내에 upload 폴더를 만들어서 사용하는 것이 아니라 photogram 이라는 폴더와 동일 경로에 두었다.
//photogram 이라는 폴더내에 .java 파일 내 코드들이 있는데 
//서버를 실행하면 .java 파일 내 코드들이 컴파일이 되어서 클래스 파일로 바뀐 후 실행이 되어야 한다.
//컴파일 되어서 실행이 되려면 phtogram 프로젝트 내 target이라는 폴더가 보이는데
//해당 폴더 안에 서버가 실행될때 컴파일을 해서 target 폴더 내 .class 파일이 생기게 된다.
//실행이 될때는 target 폴더 내 .class 파일들이 실행된다.
//
//
//photogram 폴더 내 static 폴더에 upload 폴더가 있을 경우에는
//서버가 실행되면 upload 폴더에 있는 파일들이 target 폴더로 반영이 되어야 한다.
//target 폴더에 .class와 .정적인 파일을 넣는것을 deploy 한다고 한다.
//서버가 실행되고 사용자가 사진업로드를 했을 경우 프로젝트 내 static 폴더 내에 upload 폴더에 업로가 될 것이고
//upload 폴더 내에 있는 사진파일이 target 폴더에 들어가게 되고 target 폴더 내에 있는 사진파일이 실행이 된다.
//예를들어, .java 파일을 컴파일해서 .class 파일로 target 폴더에 deploy(반영) 하는 시간이 0.1초 걸린다고 하고
//사진파일을 target 폴더에 deploy(반영)하는 시간이 1초 걸린다(사진파일은 사진마다 용량이 있기 때문에...)고 하면 
//서버실행될때 .class 파일과 정적파일을 모두 실행시켜야 하는데 사진파일이 target 폴더에 들어가기전에 실행이 될수도 있다.
//사진업로드를 하고 target 폴더에 deploy(반영) 되는 시간이 길면 메인페이지에서 사진이 엑박으로 나오게 된다.
//
//
//upload 폴더를 phtogram 프로젝트 폴더 외부에 두게 되면 upload 폴더 내 사진파일을 target 폴더로 deploy(반영)할 필요가 없다.
//파일을 찾을때 외부에 있는 upload 폴더를 찾는 것이다.
//외부에 있는 upload 폴더는 움직일 필요가 없으니까 엑박이 뜨지 않는다.
//
//
//업로드 -> 프로젝트 내부 -> deploy
//사진파일은 deploy 되는 시간이 1초
//사진업로드 후 redirect 되는 페이지로 넘어가는 시간이 0.5초
//redirect 되는 페이지로 가는 시간이 더 빠르기 때문에 해당 사진은 엑박이 뜬다.