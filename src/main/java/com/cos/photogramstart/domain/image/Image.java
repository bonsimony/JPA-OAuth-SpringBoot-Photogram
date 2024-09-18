package com.cos.photogramstart.domain.image;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String caption;				// 오늘 나 너무 피곤해!
	
	private String postImageUrl;		// 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	
	@JsonIgnoreProperties({"images"}) 	// User 객체 내 images는 무시한다.
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	//이미지 좋아요
	
	//댓글
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void create() {
		this.createDate = LocalDateTime.now();
	}
	
	
	
/** 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않도록 함!!!!!	**********************************************************************/
	
// 순서 (1)	
// @Data 어노테이션을 사용하면 자동으로 toString() 함수가 생성된다!!!
// 사진 업로드 시 ImageService 내에 imageEntity를 출력할때 왜 오류가 나는지 확인하면서 toString() 함수를 만든 것이다...
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl + ", user=" + user
//				+ ", createDate=" + createDate + "]";
//	}
		

// 순서 (2)
// + ", user=" + user 제거하면 해결완료!!!!!
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl 
//				+ ", createDate=" + createDate + "]";
//	}

	
/*****************************************************************************************************************************/
}

