package com.cos.photogramstart.domain.image;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	@Query(
			value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY id DESC"
			, nativeQuery = true
	   )
	Page<Image> mStroy(int principalId, Pageable pageable);	// 두번째 인자에 Pageable을 추가하기만 하면 알아서 페이징이 된다!!!
	
//		@Query(
//						value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId)"
//						, nativeQuery = true
//				   )
//		List<Image> mStroy(int principalId);
		
		
		
		
}
