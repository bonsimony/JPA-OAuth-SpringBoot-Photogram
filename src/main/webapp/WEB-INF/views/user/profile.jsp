<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!--프로필 섹션-->
<section class="profile">
	<!--유저정보 컨테이너-->
	<div class="profileContainer">

		<!--유저이미지-->
		<div class="profile-left">
			<div class="profile-img-wrap story-border"
				onclick="popup('.modal-image')">
				
				
				
				<form id="userProfileImageForm">
					<input type="file" name="profileImageFile" style="display: none;"
						id="userProfileImageInput" />
				</form>
				
				
				

				<!-- <img class="profile-image" src="#" -->
				<img class="profile-image" src="/upload/${dto.user.profileImageUrl}"
					onerror="this.src='/images/person.jpeg'" id="userProfileImage" />
			</div>
		</div>
		<!--유저이미지end-->

		<!--유저정보 및 사진등록 구독하기-->
		<div class="profile-right">
			<div class="name-group">


				<!-- <h2>TherePrograming</h2> -->
				
				<h2>${dto.user.name}</h2>
				
				<c:choose>
					<%-- <c:when test="${principal.user.id == user.id}"> --%>
					<c:when test="${dto.pageOwnerState}">
					
						<button class="cta" onclick="location.href='/image/upload'">사진등록</button>
					
					</c:when>
					<c:otherwise>
						
						<c:choose>
							<c:when test="${dto.subscribeState}">
							
								<!-- <button class="cta blue" onclick="toggleSubscribe(this)">구독취소</button> -->
								<button class="cta blue" onclick="toggleSubscribe(${dto.user.id},this)">구독취소</button>
								
							</c:when>
							<c:otherwise>
							
								<!-- <button class="cta" onclick="toggleSubscribe(this)">구독하기</button> -->
								<button class="cta" onclick="toggleSubscribe(${dto.user.id},this)">구독하기</button>
								
							</c:otherwise>
						</c:choose>
						
						 <!-- <button class="cta" onclick="toggleSubscribe(this)">구독하기</button>  -->
						
					</c:otherwise>
									
				</c:choose>


				<!-- <button class="cta" onclick="location.href='/image/upload'">사진등록</button> -->
				<!-- <button class="cta" onclick="toggleSubscribe(this)">구독하기</button> -->
				
				<button class="modi" onclick="popup('.modal-info')">
					<i class="fas fa-cog"></i>
				</button>
			</div>

			<div class="subscribe">
				<ul>
				
				
				
					<!-- <li><a href=""> 게시물<span>3</span> -->
					<%-- <li><a href=""> 게시물<span>${user.images.size()}</span> --%> 
					<%-- <li><a href=""> 게시물<span>${dto.user.images.size()}</span>  --%>
					<li><a href=""> 게시물<span>${dto.imageCount}</span> 
					
					
					</a></li>
					
					
					<!-- <li><a href="javascript:subscribeInfoModalOpen();"> 구독정보<span>2</span> -->
					<%-- <li><a href="javascript:subscribeInfoModalOpen();"> 구독정보<span>${dto.subscribeCount}</span> --%>
					<li><a href="javascript:subscribeInfoModalOpen(${dto.user.id});"> 구독정보<span>${dto.subscribeCount}</span>
					
					
					</a></li>
				</ul>
			</div>
			<div class="state">


				<!-- <h4>자기 소개입니다.</h4> -->
				<%-- <h4>${user.bio}</h4> --%>
				<h4>${dto.user.bio}</h4>


				<!-- <h4>https://github.com/codingspecialist</h4> -->
				<%-- <h4>${user.website}</h4> --%>
				<h4>${dto.user.bio}</h4>


			</div>
		</div>
		<!--유저정보 및 사진등록 구독하기-->

	</div>
</section>

<!--게시물컨섹션-->
<section id="tab-content">
	<!--게시물컨컨테이너-->
	<div class="profileContainer">
		<!--그냥 감싸는 div (지우면이미지커짐)-->
		<div id="tab-1-content" class="tab-content-item show">
			<!--게시물컨 그리드배열-->
			<div class="tab-1-content-inner">

				<!--아이템들-->
				
				<!-- JSTL 문법 -->						
				<%-- <c:forEach var = "image" items = "${user.images}">  --%>
				<c:forEach var = "image" items = "${dto.user.images}"> 
															    <!-- User 객체의 images를 말하는 것이다. --> 
															    <!--   EL 표현식에서 변수명을 적으면 get 함수가 자동 호출된다.  -->
																<!--   itmes의 값을 통해 사진이 여러장 있으면 for문을 돌면서 var = "image" 에 담기게 된다  -->
																<!-- EL 표현식은 주석을 해도 인식을 한다!!! -->
					<div class="img-box">
						
						<!-- <a href=""> <img src="/images/home.jpg" /> -->
						
						<a href=""> <img src="/upload/${image.postImageUrl}" /> 
															<!-- 
															  /upload/** 패턴이 발동하면 
															  WebMvcConfig 통해서 
															  C:/workspace/springbootwork/upload/ 
															  주소로 변경해준다. 
															 -->
						
						
						</a>
						<div class="comment">
						
						
							<!-- <a href="#" class=""> <i class="fas fa-heart"></i><span>0</span> -->
							<%-- <a href="#" class=""> <i class="fas fa-heart"></i><span>${image.likes.size()}</span> --%>
							<a href="#" class=""> <i class="fas fa-heart"></i><span>${image.likeCount}</span>
							
							
							</a>
						</div>
					</div>

				</c:forEach>


				<!-- <div class="img-box">
					<a href=""> <img src="/images/home.jpg" />
					</a>
					<div class="comment">
						<a href="#" class=""> <i class="fas fa-heart"></i><span>0</span>
						</a>
					</div>
				</div>

				<div class="img-box">
					<a href=""> <img src="/images/home.jpg" />
					</a>
					<div class="comment">
						<a href="#" class=""> <i class="fas fa-heart"></i><span>0</span>
						</a>
					</div>
				</div> -->

				<!--아이템들end-->
			</div>
		</div>
	</div>
</section>

<!--로그아웃, 회원정보변경 모달-->
<div class="modal-info" onclick="modalInfo()">
	<div class="modal">


		<!-- <button onclick="location.href='/user/update'">회원정보 변경</button> -->
		<button onclick="location.href='/user/1/update'">회원정보 변경</button>


		<button onclick="location.href='/logout'">로그아웃</button>
		<button onclick="closePopup('.modal-info')">취소</button>
	</div>
</div>
<!--로그아웃, 회원정보변경 모달 end-->

<!--프로필사진 바꾸기 모달-->
<div class="modal-image" onclick="modalImage()">
	<div class="modal">
		<p>프로필 사진 바꾸기</p>
		<button onclick="profileImageUpload(${dto.user.id}, ${principal.user.id })">사진 업로드</button>
		<button onclick="closePopup('.modal-image')">취소</button>
	</div>
</div>

<!--프로필사진 바꾸기 모달end-->

<div class="modal-subscribe">

	<div class="subscribe">
	
		<div class="subscribe-header">
			<span>구독정보</span>
			<button onclick="modalClose()">
				<i class="fas fa-times"></i>
			</button>
		</div>

		<div class="subscribe-list" id="subscribeModalList">






			<!-- <div class="subscribe__item" id="subscribeModalItem-2">
				<div class="subscribe__img">
					<img src="#" onerror="this.src='/images/person.jpeg'" />
				</div>
				<div class="subscribe__text">
					<h2>ssar</h2>
				</div>
				<div class="subscribe__btn">
					<button class="cta blue" onclick="toggleSubscribeModal(this)">구독취소</button>
				</div>
			</div> -->
			
			
		</div>
	</div>

</div>


<script src="/js/profile.js"></script>

<%@ include file="../layout/footer.jsp"%>