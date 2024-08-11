package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data //getter, setter 생성
public class UserUpdateDto {
	
	private String name;			// 필수
	private String password;		// 필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	// 조금 위험함
	// 코드 수정이 필요할 예정
	// 패스워드를 기재 안했으면 데이터베이스에 공백이 들어가서 문제가 된다 -> Validation 체크
	// 이름을 기재 안했으면 데이터베이스에 공백이 들어가서 문제가 된다 -> Validation 체크
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password) 
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
		
	}
}
