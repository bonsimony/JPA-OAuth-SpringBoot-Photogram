package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JAP (Java Persistence API) : 자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공

@Builder

@AllArgsConstructor // 전체 생성자 생성
@NoArgsConstructor // 빈 생성자 생성
@Data	// Getter, Setter 생성
@Entity	// DB에 테이블을 생성
public class User {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
		private int id;
		
		/*
		 * @Column 어노테이션을 사용하여 제약조건을 설정하여 
		 * 동일한 username이 등록되지 않도록 한다.
		 */
		@Column(
						unique = true
						, length = 20
					  )
		private String username;
		
		@Column(nullable = false)
		private String password;
		
		@Column(nullable = false)
		private String name;
		
		private String website;					// 웹사이트
		private String bio;							// 자기소개
		
		@Column(nullable = false)
		private String email;
		private String phone;
		private String gender;
		
		private String profileImageUrl;		// 사진
		private String role; 						// 권한
		
		
		
		
		
		/*
		 * 양방향 매핑 처리 ****************************************************************
		 */
												// user -> Image 객체 내 User 변수인 user이다!!!
												// 나는 연관관계의 주인이 아니므로 테이블 컬럼을 만들지마
												// User를 SELECT할때 해당 User id로 등록된 image들을 다 가져와
												// fetch가 LAZY일 경우에 User를 SELECT 할때 해당 User id로 등록된 image들을 가져오지마
												// ㄴ 대신 getImages() 함수의 이미지들이 호출될때 가져와
												// fetch가 Eager일 경우에 User를 SELECT 할때 해당 User id로 등록된 image들을 전부 JOIN해서 가져와
		@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
		private List<Image> images;
		// yml 파일에서 jpa의 ddl-auto를 create를 해주면 컬럼을 만들어주는데 
		// images는 List로 여러개를 표현하는 것인데 데이터베이스는 컬렉션이 들어가는게 없다.....
		// 데이터베이스에서는 만들지 말라고 하는 작업을 해줘야한다.
		// @OneToMany 어노테이션으로 연관관계를 잡고 mappedBy로 Image 객체에 User 변수인 user를 넣어준다!!!
		// mappedBy는 나는 연관관계의 주인이 아니라는 말이다!!!
		// 그러므로 테이블에 컬럼을 만들지 말라는 말이다!!!
		// User를 SELECT할때 해당 User id로 등록된 image들을 다 가져오라는 말이다!!!
		// fetch가 LAZY일 경우에 User를 SELECT 할때 해당 User id로 등록된 image들을 가져오지마
		// ㄴ 대신 getImages() 함수의 이미지들이 호출될때 가져와
		// fetch가 Eager일 경우에 User를 SELECT 할때 해당 User id로 등록된 image들을 전부 JOIN해서 가져와
		
		/*
		 **************************************************************************** 
		 */
		
		
		
		
		
		private LocalDateTime createDate;
		
		@PrePersist // DB에 INSERT 되기 직전에 실행
		public void createDate() {
			this.createDate = LocalDateTime.now();
		}
		
}

// 세팅이 끝난 후 user 테이블이 생성됨 - ORM (Object Relational Mapping)
