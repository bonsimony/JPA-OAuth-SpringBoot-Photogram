package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data //getter, setter 생성
public class UserUpdateDto {
	
	@NotBlank
	private String name;			// 필수
	
	@NotBlank
	private String password;		// 필수
	
	/*
	 * @NotBlank 어노테이션만 추가했다고 해서 
	 * 서버에서 DB로 향할때 유효성 검사가 완료가 되지 않고 
	 * UserApiController에서 update 함수 인자에 UserUpdateDto 객체 앞에 @Vailid 어노테이션을 추가한다.
	 */
	
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
