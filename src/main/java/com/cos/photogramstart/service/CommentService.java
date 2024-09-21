package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository; 
	
	private final UserRepository userRepository;
	
	@Transactional
	public Comment 댓글쓰기(String content, int imageId, int userId) {
		
		Image image = new Image();
		image.setId(imageId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
		});
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(userEntity);
		
		return commentRepository.save(comment);
	}
	
	
	
	
	
//	@Transactional
//	public Comment 댓글쓰기(String content, int imageId, int userId) {
//		
//		// Tip (객체를 만들때 id값만 담아서 INSERT 할 수 있다)
//		// 대신 return 시에 image 객체와 user 객체의 id값만 가지고 있는 빈 객체를 리턴받는다.
//		Image image = new Image();
//		image.setId(imageId);
//		
//		User user = new User();
//		user.setId(userId);
//		
//		Comment comment = new Comment();
//		comment.setContent(content);
//		comment.setImage(image);
//		comment.setUser(user);
//		
//		return commentRepository.save(comment);
//	}
	
	
	
	
	
	// import org.springframework.transaction.annotation.Transactional;
//	@Transactional
//	public Comment 댓글쓰기(String content, int imageId, int userId) {
//		
//		return commentRepository.mSave(content, imageId, userId);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Transactional
	public void 댓글삭제(int id) {
		
		
		try {
			commentRepository.deleteById(id);
		}catch(Exception e) {
			throw new CustomApiException(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
}
