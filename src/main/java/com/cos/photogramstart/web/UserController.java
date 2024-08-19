package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userSerivce;
	
	@GetMapping("/user/{id}")
	public String profile
	(
			@PathVariable int id
			, Model model
			// 데이터를 들고 가야하기 때문에 Model 객체를 추가한다.
			
	) 
	{
		User userEntity = userSerivce.회원프로필(id);
		model.addAttribute("user", userEntity);
		
//		회원프로필 화면에는 회원정보, 이미지 정보, 게시물 개수, 구독정보를 들고가야한다.
//		현재시점에서 Image 객체를 보면 이미지 정보와 유저 정보를 가져오지만
//		User 객체를 보면 유저 정보만 가져오지 이미지 정보는 가져오지 않게 되어있다.
//     User 객체에 양방향 매핑 처리를 해줘야 한다.
		
		return "user/profile";
	}
	
	
	
	
//	@GetMapping("/user/{id}")
//	public String profile
//	(
//			@PathVariable int id
//			, Model model
//			// 데이터를 들고 가야하기 때문에 Model 객체를 추가한다.
//			
//	) 
//	{
//		userSerivce.회원프로필(id);
//		model.addAttribute("images", null);
//		
//		return "user/profile";
//	}
	
	
	
	
	
//	// 브라우저 주소
//	// http://localhost:8080/user/1
//	// http://localhost:8080/user/2
//	// http://localhost:8080/user/3
//	// ... 
//	// user의 id값 마다 해당 id 값에 해당하는 유저의 프로필 화면을 보여준다.
//	@GetMapping("/user/{id}")
//	public String profile
//	(
//			// GetMappring의 {id}는 변수를 뜻한다.
//			// id 변수를 담기 위해 @PathVariable 을 사용한다.
//			@PathVariable int id
//			
//	) 
//	{
//		return "user/profile";
//	}
	

/*********************************************************************************************************/	
	
	
//	@GetMapping
//	("/user/{id}/update")
//	public String update
//	(
//			// GetMappring의 {id}는 변수를 뜻한다.
//			// id 변수를 담기 위해 @PathVariable 을 사용한다.
//			@PathVariable int id
//	) 
//	{
//		
//		
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		
//		
//		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
//															// auth.getPrincipal() 값은 Obejct이기 때문에
//															// (PrincipalDetails)auth.getPrincipal()으로 다운캐스팅을 해준다.
//		
//		System.out.println("직접 찾은 세션 정보 : "+mPrincipalDetails.getUser());
//		
//		return "user/update";
//	}
	
	
	
/*********************************************************************************************************/	
	
	
/*********************************************************************************************************/		
//	@GetMapping
//	("/user/{id}/update")
//	public String update
//	(
//			// GetMappring의 {id}는 변수를 뜻한다.
//			// id 변수를 담기 위해 @PathVariable 을 사용한다.
//			@PathVariable int id
//			
//			// @AuthenticationPrincipal 어노테이션으로 접근하면 Authentication 객체에 바로 접근이 가능하다. (자세한 내용은 제일 밑에 주석내용 확인)
//			, @AuthenticationPrincipal PrincipalDetails principalDetails
//	) 
//	{
//		// 1. 추천
//		System.out.println("세션 정보 : " + principalDetails.getUser());
//		
//		
//		// 2. 극혐
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
//															// auth.getPrincipal() 값은 Obejct이기 때문에
//															// (PrincipalDetails)auth.getPrincipal()으로 다운캐스팅을 해준다.
//		
//		System.out.println("직접 찾은 세션 정보 : "+mPrincipalDetails.getUser());
//		
//		return "user/update";
//	}
	
/*********************************************************************************************************/		
	

	@GetMapping
	("/user/{id}/update")
	public String update
	(
			// GetMappring의 {id}는 변수를 뜻한다.
			// id 변수를 담기 위해 @PathVariable 을 사용한다.
			@PathVariable int id
			
			// @AuthenticationPrincipal 어노테이션으로 접근하면 Authentication 객체에 바로 접근이 가능하다. (자세한 내용은 제일 밑에 주석내용 확인)
			, @AuthenticationPrincipal PrincipalDetails principalDetails
			
			//, Model mode
			// Model 객체로 처리하는것이 아닌 jsp 시류리티 태그로 처리하여 주석l
			/*
			 * header.jsp 에서 아래 태그로 처리 <sec:authorize access = "isAuthenticated()">
			 * <sec:authentication property="principal" var = "principal">
			 * </sec:authentication> </sec:authorize>
			 */
	) 
	{

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// 1. 추천
		System.out.println("세션 정보 : " + principalDetails.getUser());
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// 2. 극혐
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
															// auth.getPrincipal() 값은 Obejct이기 때문에
															// (PrincipalDetails)auth.getPrincipal()으로 다운캐스팅을 해준다.
		
		System.out.println("직접 찾은 세션 정보 : "+mPrincipalDetails.getUser());

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		// Model 객체로 처리하는것이 아닌 jsp 시류리티 태그로 처리하여 주석
		// model.addAttribute("principal", principalDetails.getUser()); */
		/*
		 * header.jsp 에서 아래 태그로 처리 <sec:authorize access = "isAuthenticated()">
		 * <sec:authentication property="principal" var = "principal">
		 * </sec:authentication> </sec:authorize>
		 */
		
		
		/*
		 * pom.xml 내에 있는 시큐리티 태그 라이브러리가 설정되어 있어야 한다. 
		 *	해당 header.jsp 파일에 taglib 태그에 prefix 속성인 prefix c와 sec가  있어야 한다.	
		*/
	
	 /*pom.xml */
	/*시큐리티 태그 라이브러리 */
			
				
				/*<dependency>
					<groupId>org.springframework.security</groupId>
					<artifactId>spring-security-taglibs</artifactId>
				</dependency> */
			
			
	/*header.jsp*/ 
	
			/*
			 * <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			 * <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
			 */

		/*
		 * <sec:authorize access = "isAuthenticated()">
		 *
		 * </sec:authorize> 
		 */

		/*
		 * 하나의 문법이다!!!
		 * isAuthenticated() 해서 인증된 정보에 접근하는 방법이다.
		 * 즉, 세션에 접근하는 방법이다. 
		 * 다음에 사용할때는 <sec:authentication property="principal" var = "principal"> </sec:authentication>
		 * 해당 부분에 var = "principal" 이라는 변수값만 변경하여 사용하고자 하는 변수만 맞춰서 사용하면 된다.
		 */
	

			




		/*
		 * 01. UserController Model 객체 활용 : O UserController에서 Model 객체를 활용하여
		 * model.addAttribute("principal", principalDetails.getUser()); 위의 해당 코드를 시용하기
		 * 위해서는 아래와 같이 EL(Expresiion Language) 표현식을 사용한다. ${principal.username}
		 * ${principal.name} ${principal.website} ${principal.phone} ${principal.bio}
		 * 
		 * 02. UserController Model 객체 활용 : X ->> <sec:authorize access =
		 * "isAuthenticated()"> <sec:authentication property="principal" var =
		 * "principal"> </sec:authentication></sec:authorize> <sec:authorize access =
		 * "isAuthenticated()"> <sec:authentication property="principal" var =
		 * "principal"> </sec:authentication> </sec:authorize> 위의 해당 코드로 EL(Expresiion
		 * Language)을 사용할 수 있다. var = "principal" 에서 봤듯이 변수명이 principal이기 때문에 principal은
		 * UserDetails를 가져오는 것인데 상속을 받아서 PrincipalDetails를 최종적으로 가져오게 되며
		 * PrincipalDetails 안에 username을 가져오기 위해서는 아래와 같이 처리한다.
		 * 
		 * ${principal.user.username} ${principal.user.name} ${principal.user.website}
		 * ${principal.user.phone} ${principal.user.bio}
		 */
	
		
		
		return "user/update";
	}
	
}

// 클라이언트가 POST 방식으로 /auth/siginin 요청을 할때
// SecuriyConfig 설정 때문에 Security가 서버로 요청을 한다. (클라이언트 -> 시큐리티 -> 서버)

/// auth/siginin 요청이 왔을때
// 시큐리티가 중간에서 낚아챈 후 시큐리티는 PrincipalDetailsService로 넘긴다.  (PrincipalDetailsService의 타입은 UserDetails이다.)
// Principal 용어의 뜻은 접근주체, 인증주체이다.
// PrincipalDetailsService에서 username을 확인을 한다.
// PrincipalDetailsService에서 username이 없으면 접속 로그인 페이지로 다시 되돌린다.
// PrincipalDetailsService에서 username이 있으면 
// 리턴한 PrincipalDetails을 세션에 저장을 한다.
// PrincipalDetails은 UserDetails를 상속받았다.
// UserDetails 인터페이스 내의 함수들을 PrincipalDetails가 임플리먼트 해서 오버라이드로 UserDetails를 PrincipalDetails로 바꿔치기 함.
// 세션저장을 할때 세션영역에 저장이 되어 있는데 key,value로 저장이 된다.
// 세션을 찾기 위해서는 httpSession이라는 Request 객체를 만든다.
// 하지만 세션영역안에 SecurityContextHolder라는 곳에 들어간다.
// SecurityContextHolder 안에 Authentication 이라는 객체 안에 집어넣는다.
// PrincipalDetails가 Authentication 안에 들어가 있는 것이다.
// @AuthenticationPrincipal 어노테이션으로 접근하면 Authentication 객체에 바로 접근이 가능하다.


