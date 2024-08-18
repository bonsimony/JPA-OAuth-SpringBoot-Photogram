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
