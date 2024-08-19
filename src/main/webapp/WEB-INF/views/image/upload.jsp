<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ include file="../layout/header.jsp" %>

    <!--사진 업로드페이지 중앙배치-->
        <main class="uploadContainer">
           <!--사진업로드 박스-->
            <section class="upload">
               
               <!--사진업로드 로고-->
                <div class="upload-top">
                    <a href="home.html" class="">
                        <img src="/images/logo.jpg" alt="">
                    </a>
                    <p>사진 업로드</p>
                </div>
                <!--사진업로드 로고 end-->
                
                <!--사진업로드 Form-->
                
                
                <!-- <form class="upload-form" > -->
                <!-- <form class="upload-form" action ="/image" method = "post" enctype = "application/x-www-form-urlencoded"> -->
                <form class="upload-form" action ="/image" method = "post" enctype = "multipart/form-data">
                																											 <!-- 
                																											 	multipart/form-data란? 
                																											 	여러가지 종류의 타입을 묶어서 전송할때 사용하는 타입니다.
                																											    지금 상황은 파일과 key=value 형태를 같이 사용해야 하는 상황이다. 
                																											  -->
                																											 
                    <input  type="file" name="file"  onchange="imageChoose(this)"/>
                    <div class="upload-img">
                        <img src="/images/person.jpeg" alt="" id="imageUploadPreview" />
                    </div>
                    
                    <!--사진설명 + 업로드버튼-->
                    <div class="upload-form-detail">
                    
                    
                   		 <!-- <input type="text" placeholder="사진설명" name="caption"/> {"cation" : "테스트"} -->
                   		 <!-- <input type="text" placeholder="사진설명" name="caption" required="required"/>  -->
                   		 <input type="text" placeholder="사진설명" name="caption"/> 
                   		
                   		
                        <button class="cta blue">업로드</button>
                    </div>
                    <!--사진설명end-->
                    
                </form>
                <!--사진업로드 Form-->
            </section>
            <!--사진업로드 박스 end-->
        </main>
        <br/><br/>
	
	<script src="/js/upload.js" ></script>
    <%@ include file="../layout/footer.jsp" %>