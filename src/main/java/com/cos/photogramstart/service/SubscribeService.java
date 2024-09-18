package com.cos.photogramstart.service;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; // 모든 Repository는 EntityManager를 구현해서 만들어져 있는 구현체
	
	// import org.springframework.transaction.annotation.Transactional;
	// SELECT만 하기 때문에 readOnly = true 로 해준다.
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){		
		
//		SELECT u.id, u.username, u.profileImageUrl,
//		if((SELECT 1 FROM subscribe WHERE fromUserId = 1 AND toUserId = u.id),1,0) subscribeState,
//		if((1=u.id),1,0) equalUserState
//		FROM user u INNER JOIN subscribe s
//		ON u.id = s.toUserId
//		WHERE s.fromUserId = 2;

		
		
		
		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id),1,0) subscribeState, ");
		sb.append("if((?=u.id),1,0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?"); // 세미콜론 첨부하면 안됨
		
//		1. 물음표  principalId
//		2. 물음표  principalId
//		3. 마지막 물음표 pageUserId
		
		
		
		
		// 쿼리 완성
		// import javax.persistence.Query;
		Query query = em.createNativeQuery(sb.toString())
									.setParameter(1, principalId)
									.setParameter(2, principalId)
									.setParameter(3, pageUserId);
		
		
		
		// 쿼리 실행 (qlrm 라이브러리 필요 = Dto에 DB결과를 매핑하기 위해서)
		// import org.qlrm.mapper.JpaResultMapper;
			// qlrm은 스프링부트에서 제공하는 라이브러리가 아니다.
		    // pom.xml에 설정함
			// qlrm(Query Language Result Mapper)이란? 데이터베이스에서 결과를 자바 클래스를 매핑해주는 라이브러리이다.
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
	}
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		}catch(Exception e){
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}	
		
		
	
//	@Transactional
//	public void 구독하기(int fromUserId, int toUserId) {
//		subscribeRepository.mSubscribe(fromUserId, toUserId);
//		
//		
//	}
//	
//	@Transactional
//	public void 구독취소하기(int fromUserId, int toUserId) {
//		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
//		
//	} 
	
	
	
	
//	@Transactional
//	public int 구독하기(int fromUserId, int toUserId) {
//		int result = subscribeRepository.mSubscribe(fromUserId, toUserId);
//		return result;
//		
//	}
//	
//	@Transactional
//	public int 구독취소하기(int fromUserId, int toUserId) {
//		int result = subscribeRepository.mUnSubscribe(fromUserId, toUserId);
//		return result;
//	} 
}
