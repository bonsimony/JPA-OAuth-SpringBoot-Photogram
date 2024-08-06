package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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
		@Column(unique = true)
		private String username;
		private String password;
		
		private String name;
		private String website;					// 웹사이트
		private String bio;							// 자기소개
		private String email;
		private String phone;
		private String gender;
		
		private String profileImageUrl;		// 사진
		private String role; 						// 권한
		
		private LocalDateTime createDate;
		
		@PrePersist // DB에 INSERT 되기 직전에 실행
		public void createDate() {
			this.createDate = LocalDateTime.now();
		}
		
}

// 세팅이 끝난 후 user 테이블이 생성됨 - ORM (Object Relational Mapping)
