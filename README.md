# 포토그램 - 인스타그램 클론 코딩

### 인스타그램 개인 프로젝트 개발 [스프링시큐리티]

![20240920_134557](https://github.com/user-attachments/assets/7c556a9d-eec0-402a-ba1e-53fcdf2560a8)

![20240920_135014](https://github.com/user-attachments/assets/6547e5e2-91ba-4ab7-a494-5fc7e5fef478)

![20240920_135124](https://github.com/user-attachments/assets/c331cfcd-c56f-4d42-878e-8c95c2a8b560)

![20240920_135214](https://github.com/user-attachments/assets/057ba3af-762d-410c-b9ef-987bd2d82442)

![20240920_135301](https://github.com/user-attachments/assets/849aefcd-28c4-4e56-8947-378299fbb6e5)

![20240920_135330](https://github.com/user-attachments/assets/d09703f1-9676-4263-a297-1d2a1d278f30)

![20240920_135419](https://github.com/user-attachments/assets/0a2b1b6a-cb29-40c8-a1dc-6cc3db0ddb1a)

![20240920_135453](https://github.com/user-attachments/assets/7fb39ee6-c3f0-4c78-b6e4-83a4489f6d6e)

![20240920_135530](https://github.com/user-attachments/assets/a212bf72-002b-4c4a-9ae4-305fee08efcb)

### STS 툴 버그가 발견되면 다른 버전으로 다운 받는 법
- https://github.com/spring-projects/sts4/wiki/Previous-Versions

### STS 툴에 세팅하기 - 플러그인 설정 (JSP, Javascript)
- https://blog.naver.com/getinthere/222322821611

### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client

```xml
<!-- 시큐리티 태그 라이브러리 -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>9.0.43</version>
</dependency>

<!-- JSTL -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>
```

### 데이터베이스

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database photogram;
```

### yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/photogram?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false
    username: cos
    password: cos1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

#file:
#  path: C:/src/springbootwork-sts/upload/
```

### 태그라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
```
