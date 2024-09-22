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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//		@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
		
		@JsonIgnoreProperties({"user"}) // Image 객체 내부에 있는 user를 무시하고 JSON 파싱을 한다.
														 // User 객체의 getter가 호출되지 않게 막는다.
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

		
		
		
		
		
		
		
		
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
					+ ", website=" + website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender="
					+ gender + ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate=" + createDate + "]";
		}
		
		
		
		
		
		
//		@Override
//		public String toString() {
//			return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
//					+ ", website=" + website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender="
//					+ gender + ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", images=" + images
//					+ ", createDate=" + createDate + "]";
//		}
			
}

// 세팅이 끝난 후 user 테이블이 생성됨 - ORM (Object Relational Mapping)






// <open-in-view 개념정리>
//클라이언트가 요청을 하면 톰캣이라는 성안에 들어오게 된다.
//톰캣 내부에 앞쪽에는 디스패처라는 것이 있고 그 다음 스프링컨테이너가 있다.
//디스패처는 클라이언트가 요청을 할때 어떤 컨트롤러인지 정해준다.
//스프링컨테이너 성안에서 우선 디스패처를 통해 컨트롤러로 온다.
//Controller는 Service를 호출하고 
//Service는 Repository를 호출하고 Repository는 영속성컨텍스트를 가지고 있으며
//Repository는 실제 DataBase를 호출하게 된다.
//
//
//Client 요청이 들어오면 Dispacther에서 어떤 Controller를 찾을때 Session이 만들어진다.
//이때, Session이란 DataBase에 접근할 수 있는 Session을 말한다.
//그렇게 되면 Controller부터 SELECT를 할 수 있다는 것이다.
//Controller, Service, Repository 순으로 요청을 하고 
//영속성컨텍스트에 데이터가 있으면 바로 응답이 될것이고
//영속성컨텍스트가 DataBase에 요청을 해서 데이터가 없으면 응답을 받고 다시 Repository로 응답을 한다.
//영속성 컨텍스트나 DataBase에 있는 데이터를 Repository가 받고 Service로 응답을 하고
//Service의 비즈니스 로직을 통해 처리를 하고 Controller로 응답을 하고
//Controller는 데이터로 응답을 할것인지 html로 응답을 할 것인지 정한다.
//데이터로 응답을 할때는 @RestController로 할 것이고 html로 응답을 할때는 @Controller로 할 것이다.
//그리고 Controller에서 Dispatcher로 돌려준다.
//DataBase 접근할 수 있는 Session은 Service에서 Controller로 응답을 할때 Session이 종료된다.
//이렇게 되면 Controller에서 Lazy 로딩을 할 수가 없다.
//Controller로 왔을때 DataBase 접근할 수 있는 Session이 종료되므로 Lazy인 지연로딩을 할 수가 없다.
//
//
//이러한 Lazy 로딩을 할 수 없는 경우에 해결방법은 Eager로 변경을 하면 된다.
//application.yml 파일에서 open-in-view가 true가 되어 있는것을 false로 변경 후
//유저1이 사진등록을 한다고 가정할때 유저1의 프로필 페이지도 들어가지지 않는다.
//User 객체에서 캡슐화된 id부터 role까지 쭉 읽게되고 
//profile.jsp를 보면 ${user.images}을 통해 for문을 돌면서 
//${image.postImageUrl}를 쭉 뿌리는 부분에서 ${user.images}가 동작을 하지 않는다.
//왜냐하면 application.yml 파일에서 open-in-view가 false로 잡혀 있으면 
//DataBase로 접근하는 Session이 Service에서 Controller로 응답하는 그 사이에서 Session이 종료되기 때문이다.
//
//
//User 객체 내 images를 Eager로 변경을 하게 되면 
//DataBase에서 Repository의 영속성컨텍스트에 User와 Image를 조인해서 모두 들고오기 때문에 세션이 종료된다고 하더라도 상관이 없다
//
//
// Lazy인 경우에 늦은 지연로딩을 하기 때문에
// 세션이 종료가 되어서 User 객체 내 images를 들고오지 못하는 것이다.
//
//
// User 객체에서 images를 Lazy로 변경 후
// application.yml에서 open-in-view를 true로 변경을 하면 잘된다!!!
// 
//
// application.yml 파일에서 open-in-view를 true로 하게되면
// view단까지 세션을 open 한다는 말이다!!!!
// Session 종료가 Controller에서 Dispacter 응답하기 전 중간에서 Session이 종료가 된다!!!!
// Lazy가 가능하게 되며 DataBase에서 image를 준다.

