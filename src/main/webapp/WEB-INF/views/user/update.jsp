<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!--프로필셋팅 메인-->
<main class="main">
	<!--프로필셋팅 섹션-->
	<section class="setting-container">
		<!--프로필셋팅 아티클-->
		<article class="setting__content">

			<!--프로필셋팅 아이디영역-->
			<div class="content-item__01">
				<div class="item__img">
					<img src="#" onerror="this.src='/images/person.jpeg'" />
				</div>
				<div class="item__username">
				
				
					<!-- <h2>TherePrograming</h2> -->
					<%-- <h2>${principal.username }</h2> --%>
					<h2>${principal.user.username}</h2>
					<!--
						 JSP에 EL(Expression Language)표현식이라고 하며
						UserController에서 
						/user/{id}/update 
						해당 주소로 넘어갔을 시
						Model 객체를 활용하여
						model.addAttribute("principal", principalDetails.getUser());
						User Object를 불러내는데 
						EL(Expression Language) 표현식에서는 
						${principal.username} 을 보면 User 객체를 명시하지 않고 
						User 객체 내에 있는 username을 명시하면 username의 Getter을 가져온다. 
					-->
					
					
				</div>
			</div>
			<!--프로필셋팅 아이디영역end-->

			<!--프로필 수정-->
			
			
			<!-- <form id="profileUpdate" > -->
			<!--<form id="profileUpdate" onsubmit="update(${principal.user.id})">-->
			<!--
				 form 태그 내 action 속성이 없으면 자기 자신으로 다시 되돌아온다. 
			-->
			
			
			<form id="profileUpdate" onsubmit="update(
																					${principal.user.id}
																					, event
																			   )"
																			   									>
			<!--
				 form 태그 onsubmit 속성의 값인 update 함수에 event 변수를 추가한다. 
			-->
			
			
				<div class="content-item__02">
					<div class="item__title">이름</div>
					<div class="item__input">
					
					
						<!-- <input type="text" name="name" placeholder="이름"
							value="겟인데어" /> -->
						<%-- <input type="text" name="name" placeholder="이름"
						value=${principal.name } /> --%>
						<%-- <input type="text" name="name" placeholder="이름" value="${principal.user.name}"/> --%>
						<input type="text" name="name" placeholder="이름" value="${principal.user.name}"  required="required"/>	
							
					</div>
				</div>
				<div class="content-item__03">
					<div class="item__title">유저네임</div>
					<div class="item__input">
					
					
						<!-- <input type="text" name="username" placeholder="유저네임"
							value="TherePrograming" readonly="readonly" /> -->
						<%-- <input type="text" name="username" placeholder="유저네임"
							value=${principal.username } readonly="readonly" /> --%>
						<input type="text" name="username" placeholder="유저네임" value="${principal.user.username}" readonly="readonly" />
							
							
					</div>
				</div>
				<div class="content-item__04">
					<div class="item__title">패스워드</div>
					<div class="item__input">
					
					
						<!-- <input type="password" name="password" placeholder="패스워드"  /> -->
						<input type="password" name="password" placeholder="패스워드" required="required"  />
						
						
					</div>
				</div>
				<div class="content-item__05">
					<div class="item__title">웹사이트</div>
					<div class="item__input">
					
					
						<!-- <input type="text" name="website" placeholder="웹 사이트"
							value="https://github.com/codingspecialist" /> -->
				<%-- 		<input type="text" name="website" placeholder="웹 사이트"
							value=${principal.website }/> --%>	
						<input type="text" name="website" placeholder="웹 사이트" value="${principal.user.website}"/>
						
							
							
					</div>
				</div>
				<div class="content-item__06">
					<div class="item__title">소개</div>
					<div class="item__input">
					
					
						<!-- <textarea name="bio" id="" rows="3">프로그래머</textarea> -->
						<%-- <textarea name="bio" id="" rows="3">${principal.bio }</textarea> --%>
						<textarea name="bio" id="" rows="3">${principal.user.bio}</textarea>
						
						
					</div>
				</div>
				<div class="content-item__07">
					<div class="item__title"></div>
					<div class="item__input">
						<span><b>개인정보</b></span> <span>비즈니스나 반려동물 등에 사용된 계정인 경우에도
							회원님의 개인 정보를 입력하세요. 공개 프로필에는 포함되지 않습니다.</span>
					</div>
				</div>
				<div class="content-item__08">
					<div class="item__title">이메일</div>
					<div class="item__input">
					
					
						<!-- <input type="text" name="email" placeholder="이메일"
							value="getinthere@naver.com" readonly="readonly" /> -->
						<%-- <input type="text" name="email" placeholder="이메일"
							value=${principal.email } readonly="readonly" /> --%>
						<input type="text" name="email" placeholder="이메일" value="${principal.user.email}" readonly="readonly" />			
							
							
					</div>
				</div>
				<div class="content-item__09">
					<div class="item__title">전회번호</div>
					<div class="item__input">
					
					
						<!-- <input type="text" name="tel" placeholder="전화번호"
							value="0102222" /> -->
						<%-- <input type="text" name="tel" placeholder="전화번호"
							value=${principal.phone } /> --%>
						<%-- <input type="text" name="tel" placeholder="전화번호" value="${principal.user.phone}"/> --%>
						<input type="text" name="phone" placeholder="전화번호" value="${principal.user.phone}"/>
							
							
					</div>
				</div>
				<div class="content-item__10">
					<div class="item__title">성별</div>
					<div class="item__input">
					
					
						<!-- <input type="text" name="gender" value="남" /> -->
						<%-- <input type="text" name="gender" value=${principal.gender } /> --%>
						<input type="text" name="gender" value="${principal.user.gender}" />
						
						
					</div>
				</div>

				<!--제출버튼-->
				<div class="content-item__11">
					<div class="item__title"></div>
					<div class="item__input">
					
					
						<!-- <button>제출</button> -->
						<!-- <button onclick = "update()">제출</button> -->
						<!-- <button onclick = "update(${principal.user.id}, event)">제출</button> -->
						<%-- <button type = "button" onclick = "update(${principal.user.id}, event)">제출</button> --%>
						
						<%-- <button type = "button" onclick = "update(${principal.user.id})">제출</button> --%>
						<!-- button type을 button으로 할 시 input 태그 내 required 속성이 적용되지 않는다. -->
						
						
						
						<button>제출</button>
						<!--  
								01. required 속성을 추가하여 프론트 단에서 유효성 검사 진행함.
								02. button 타입에 속성이 없는 경우에 form 태그가 실행이 되며 새로고침 되는 현상이 발생한다.
								03. form 태그 onsubmit 속성 값인 update 함수에 event 인자를 추가하여 
								      스크립트 상에서 preventDefault()를 통해 form 태그를 통한 새로고침 되는 현상을 막는다.
						-->
						
						
						
					</div>
				</div>
				<!--제출버튼end-->

			</form>
			<!--프로필수정 form end-->
		</article>
	</section>
</main>

<script src="/js/update.js"></script>

<%@ include file="../layout/footer.jsp"%>