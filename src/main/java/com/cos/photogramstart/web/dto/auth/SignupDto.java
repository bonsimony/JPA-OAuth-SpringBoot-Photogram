package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

//DTO(Data Transfer Obejct)
@Data // Getter, Setter를 만들어 주는 어노테이션
public class SignupDto {
		
	/*
	 * pom.xml에 mavenRepository 사이트에서 Validation 태그 추가하여 
	 * Validation 종류중에 @Max 어노테이션 활용
	 */
		@Max(20)
		private String username;
		
		/* 
		 * username과 마찬가지고 Validation 종류 중에 @NotBlack 사용 
		 */
		@NotBlank
		private String password;
		
		@NotBlank
		private String email;
		
		@NotBlank
		private String name;
		
		public User toEntity() {
			return User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.build();
			
		}
}
