package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		subscribeRepository.mSubscribe(fromUserId, toUserId);
		
		
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
		
	} 
	
	
	
	
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
