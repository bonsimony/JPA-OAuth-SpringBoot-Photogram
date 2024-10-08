<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
</head>

<body>
    <div class="container">
        <main class="loginMain">
           <!--회원가입섹션-->
            <section class="login">
                <article class="login__form__container">
                  
                   <!--회원가입 폼-->
                    <div class="login__form">
                        <!--로고-->
                        <h1><img src="/images/logo.jpg" alt=""></h1>
                         <!--로고end-->
                         
                         <!--회원가입 인풋-->
                        <form class="login__input" action = "/auth/signup" method = "post">
                        																			<!-- 
                        																				method를 post로 하는 이유는?
                        																				서버에게 아래 데이터들을 달라고 하는 것이 아닌 주기 위해서 post를 사용하고 
                        																				데이터에베이스에 INSERT 하기 위함 
                        																			-->
                        																			
                        																			
                        																			
                        	<!------------------------------------------------------------------------------------------------------------  																		
                        		AOP 처리를 하기 위해 ValidationAdvice 파일 생성 후 
                        		BindingResult 잘 작동하는지 확인시에 required = "required" 제거했었음
                        	-------------------------------------------------------------------------------------------------------------->			
                        																
                        	<!-- <input type="text" name="username" placeholder="유저네임" maxlength = "20"/> -->
                        																			
                        	<!------------------------------------------------------------------------------------------------------------->
                        				
                        				
                        				
                        																			
                             <input type="text" name="username" placeholder="유저네임" required="required" maxlength = "20"/> 
                            																						  <!-- required 속성에 required 값이 들어가 있으면 필수입력 체크가 된다.
                            																						  하지만 postman 프로그램으로 x-www-form-urlencode 로 값을 넘기면 값을 넘길 수 있기 때문에
                            																						  프론트뿐만 아니라 백엔드에도 막아줘야한다 -->
                            																						  
                            																						  <!-- maxlength 속성을 활용하여 최대 입력할 수 있는 길이로 제한할 수 있다. -->
                            																						  
                            <input type="password" name="password" placeholder="패스워드" required="required" />
                            <input type="email" name="email" placeholder="이메일" required="required" />
                            <input type="text" name="name" placeholder="이름" required="required" />
                            <button>가입</button>
                        </form>
                        <!--회원가입 인풋end-->
                    </div>
                    <!--회원가입 폼end-->
                    
                    <!--계정이 있으신가요?-->
                    <div class="login__register">
                        <span>계정이 있으신가요?</span>
                        <a href="/auth/signin">로그인</a>
                    </div>
                    <!--계정이 있으신가요?end-->
                    
                </article>
            </section>
        </main>
    </div>
</body>

</html>