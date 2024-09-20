package com.cos.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
	
	private final CommentService commentService; 
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave
	(
			// CommentDto commentdto  
			// JSON 데이터를 객체로 바로 받지 못한다!!!
			// key=value 형태인 x-www-form-urlencoded만 받을 수 있다!!!
			// @RequestBody를 사용하면 JSON 형태를 객체로 받을 수 있다!!!
			
			@RequestBody CommentDto commentDto
			
			, @AuthenticationPrincipal PrincipalDetails principalDetails
	)
	{
															
		//System.out.println(commentdto);
		// CommentDto(content=들어오는지 확인!, imageId=4)
		
		Comment comment = commentService.댓글쓰기(
																		commentDto.getContent() 				// content
																		, commentDto.getImageId()				// imageId
																		, principalDetails.getUser().getId()     	// userId
																	); 
		
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기 성공", comment), HttpStatus.CREATED); // HttpStatus.CREATED -> 상태코드 201번
	}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		
		return null;
	}
	
}
