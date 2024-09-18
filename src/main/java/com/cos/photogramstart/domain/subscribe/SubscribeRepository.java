package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	
	@Modifying // 데이터베이스에 변경을 주기 때문에 @Modifying 어노테이션이 필요하다.
	 // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 @Modifying 어노테이션이 필요하다.

/*
*  :fromUserId, :toUserId
*  mSubscribe 함수 인자 변수값을 바인딩하여 쿼리를 실행시킨다.
*/
@Query
(
value = 
"INSERT INTO subscribe"
+ "("
+ "fromUserId"
+ ", toUserId, createDate"
+ ") "
+ "VALUES"
+ "("
+ ":fromUserId"
+ ", :toUserId"
+ ", now()"
+ ")"

, nativeQuery = true
)
void mSubscribe(int fromUserId, int toUserId);	
													




@Modifying // 데이터베이스에 변경을 주기 때문에 @Modifying 어노테이션이 필요하다.
	 // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 @Modifying 어노테이션이 필요하다.

/*
*  :fromUserId, :toUserId
*  mSubscribe 함수 인자 변수값을 바인딩하여 쿼리를 실행시킨다.
*/
@Query
(
value = 
"DELETE FROM subscribe "
+ "WHERE 1=1 "
+ "AND "		
+ "fromUserId = :fromUserId "
+ "AND "
+ "toUserId = :toUserId"

, nativeQuery = true
)
void mUnSubscribe(int fromUserId, int toUserId);	





// SELECT만 하기 때문에 @Modifying 어노테이션을 사용하지 않는다.
@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
int mSubscribeState(int principalId, int pageUserId);

@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
int mSubscribeCount(int pageUserId);









	
//	@Modifying // 데이터베이스에 변경을 주기 때문에 @Modifying 어노테이션이 필요하다.
//	 				 // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 @Modifying 어노테이션이 필요하다.
//	
//	/*
//	 *  :fromUserId, :toUserId
//	 *  mSubscribe 함수 인자 변수값을 바인딩하여 쿼리를 실행시킨다.
//	 */
//	@Query
//	(
//			value = 
//			"INSERT INTO subscribe"
//			+ "("
//			+ "fromUserId"
//			+ ", toUserId, createDate"
//			+ ") "
//			+ "VALUES"
//			+ "("
//			+ ":fromUserId"
//			+ ", :toUserId"
//			+ ", now()"
//			+ ")"
//			
//			, nativeQuery = true)
//	int mSubscribe(int fromUserId, int toUserId);	// 성공 시 1(변경된 행의 개수가 리턴됨)을 리턴하도록 정해져 있다.
//																	  	// 실패 시 -1을 리턴하도록 정해져 있다.
//																	
//	
//	
//	
//	
//	@Modifying // 데이터베이스에 변경을 주기 때문에 @Modifying 어노테이션이 필요하다.
//					 // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 @Modifying 어노테이션이 필요하다.
//	
//	/*
//	 *  :fromUserId, :toUserId
//	 *  mSubscribe 함수 인자 변수값을 바인딩하여 쿼리를 실행시킨다.
//	 */
//	@Query
//	(
//			value = 
//			"DELETE FROM subscribe"
//			+ "WHERE  1=1"
//			+ "fromUserId = :fromUserId "
//			+ "AND"
//			+ "toUserId = :toUserId"
//			
//			, nativeQuery = true)
//	int mUnSubscribe(int fromUserId, int toUserId);	// 성공 시 1(변경된 행의 개수가 리턴됨)을 리턴하도록 정해져 있다. 
//																			// 실패 시 -1을 리턴하도록 정해져 있다.
//																			// id값이 1인 유저가 없는데 DELETE를 하면 0을 리턴한다.
}
