package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	
//	private int isPageOwner;
	
//	private boolean isPageOwner;

	private User user;
	
	private boolean pageOwnerState; // JSTL 사용할때 변수명 앞에 is가 붙으면 파싱이 잘 안된다고함..........
	
	private int imageCount;
	
	private boolean subscribeState;
	
	private int subscribeCount;
	
}
