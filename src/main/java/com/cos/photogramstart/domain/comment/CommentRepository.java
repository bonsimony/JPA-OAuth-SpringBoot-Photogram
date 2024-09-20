package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
//	@Modifying
//	@Query(value = "INSERT INTO comment(content, imageId, userId, createDate) VALUES(:content, :imageId, :userId, now())", nativeQuery = true)
//	Comment mSave(String content, int imageId, int userId);
//
// Modifying queries can only use void or int/Integer as return type!
// Modifying queries는 리턴타입이 void나 int로 밖에 할 수가 없다...
// 그러나 리턴타입을 void나 int로 할 경우에 기본키 값을 넣을 수 없기 때문에 삭제를 할 수가 없다!!!
//
// ex) id  content  imageId  userId
//	     1   댓글내용       1	   		 2
//
// 그래서 Repository를 활용해서 처리해야한다!!!
}
