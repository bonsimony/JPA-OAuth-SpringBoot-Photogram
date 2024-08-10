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
        <!--로그인섹션-->
            <section class="login">
               <!--로그인박스-->
                <article class="login__form__container">
                   <!--로그인 폼-->
                   <div class="login__form">
                        <h1><img src="/images/logo.jpg" alt=""></h1>
                        
                        <!--로그인 인풋-->
                        <form class="login__input"  action = "/auth/signin" method = "POST">
                        										   <!-- 
                        										   	action 속성의 값은 /auth/signin이라고 하고
                        										   	method 속성의 값은 POST라고 한다.
                        										   	method 속성의 값을 POST라고 하는 이유는 로그인하는 정보는 귀중한 정보이기 때문에
                        										   	method 속성의 값을 GET이라고 하면 주소창에 노출되기 때문이다. 
                        										   -->
                        										   
                        										   <!-- 
                        										   	로그인에 대한 Controller를 따로 만들지 않고 Security에 위임한다. 
                        										   	SercurityConfig 클래스에서 처리한다.
                        										   -->
                        										   
                            <input type="text" name="username" placeholder="유저네임" required="required" />
                            <input type="password" name="password" placeholder="비밀번호" required="required" />
                            <button>로그인</button>
                        </form>
                        <!--로그인 인풋end-->
                        
                        <!-- 또는 -->
                        <div class="login__horizon">
                            <div class="br"></div>
                            <div class="or">또는</div>
                            <div class="br"></div>
                        </div>
                        <!-- 또는end -->
                        
                        <!-- Oauth 소셜로그인 -->
                        <div class="login__facebook">
                            <button>
                                <i class="fab fa-facebook-square"></i>
                                <span>Facebook으로 로그인</span>
                            </button>
                        </div>
                        <!-- Oauth 소셜로그인end -->
                    </div>
                    
                    <!--계정이 없으신가요?-->
                    <div class="login__register">
                        <span>계정이 없으신가요?</span>
                        <a href="/auth/signup">가입하기</a>
                    </div>
                    <!--계정이 없으신가요?end-->
                </article>
            </section>
        </main>
        
    </div>
</body>

</html>