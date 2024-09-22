package com.cos.photogramstart.config;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;


// 1. WebSecurityConfigurerAdapter 상속
// 2. Ioc등록 : @Configuration
// 3. 해당 파일로 시큐리티를 활성화 : @EnableWebSecurity

@RequiredArgsConstructor
@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration //Ioc 등록   
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
		private final OAuth2DetailsService oAuth2DetailsService;
	
		@Bean
		public BCryptPasswordEncoder encode() {
			return new BCryptPasswordEncoder();
		}
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable();
			http.authorizeRequests()
				.antMatchers(
									"/"
									, "/user/**"
									,"/image/**"
									, "/subscribe/**"
									, "/comment/**"
									, "/api/**"
									).authenticated()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/auth/signin") 
				.loginProcessingUrl("/auth/signin") 
				.defaultSuccessUrl("/")
				.and()
				.oauth2Login() 
				.userInfoEndpoint() 
				.userService(oAuth2DetailsService)
				;
				
		
		}

}

//@EnableWebSecurity //해당 파일로 시큐리티를 활성화
//@Configuration //Ioc 등록   
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//		
//	/*
//	 * SecurityConfig가 IoC에 등록될때
//	 * @Bean 어노이테이션을 읽어서 BCryptPasswordEncoder을 들고 있게 된다
//	 */
//		@Bean
//		public BCryptPasswordEncoder encode() {
//			return new BCryptPasswordEncoder();
//		}
//	
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			
//			http.csrf().disable();
//			
//			//super.configure(http);
//			// super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
//			
//			http.authorizeRequests()
//				.antMatchers(
//									"/"
//									, "/user/**"
//									,"/image/**"
//									, "/subscribe/**"
//									, "/comment/**"
//									, "/api/**"
//									).authenticated()
//				// 해당 주소들은 인증이 필요함
//				
//				.anyRequest().permitAll()
//				// 그 외 주소들은 인증이 필요하지 않음
//				
//				.and()
//				.formLogin()
//				// 인증이 된 페이지들은 로그인 페이지로 이동함
//				
//				.loginPage("/auth/signin") // GET
//				// 해당 로그인 페이지 경로는 /auth/signin
//				
//				
//				
//				
//				.loginProcessingUrl("/auth/signin") // POST -> 스프링 시큐리티가 로그인 프로세스 진행
//				
//				
//				
//				
//				.defaultSuccessUrl("/")
//				//로그인을 정상적으로 처리하게 되면 / 경로로 이동시킴
//				
//				.and()
//				
//				.oauth2Login() // form 로그인도 하는데 oauth 로그인도 할것이다.
//				.userInfoEndpoint() // oauth2 로그인을 하면 최종응답을 회원정보로 바로 받을 수 있따.
//				.userService(null)
//				;
//				
//		
//		}
//
//}

/*
 * IOC란? Inversion of Control 의 줄임말로, 제어의 역전 이라는 뜻이 된다. 제어의 역전이란 메소드나 객체의 호출작업을
 * 개발자가 아닌 외부에서 결정되는 것을 의미한다.  예를 들면 자바 프로그램은 main() 메소드에서 시작하여 개발자가 미리 정한 순서를
 * 따라 객체가 생성되고 실행된다. 그런데 서블릿을 생각해보면 개발해서 서버로 배포할 수 있지만, 배포하고 나서는 개발자가 직접 제어할 수
 * 없고 서블릿에 대한 제어 권한을 가진 컨테이너가 적절한 시점에 서블릿 클래스의 객체를 만들고 그 안에 메소드를 호출한다. 대부분의 프레임
 * 워크는 이와 같은 방식으로 사용 되며, 개발자는 필요한 부분을 개발해서 끼워넣기 형식으로 개발하고 실행한다. 이와 같이 조립된 코드의 최종
 * 호출은 개발자에 의해 제어되는 것이 아닌 프레임워크에 의해 제어 되게 되는데 이 때문에 제어의 역전이라 하는 것 이다.
*/

/*
 * DI란? Dependency Injection 의 줄임말로, 의존성 주입 이라는 뜻이 된다. 의존성 주입은 제어의 역전이 일어날때 스프링
 * 내부에 있는 객체들간의 관계를 관리할 때 사용하는 기법이다. 자바에서는 일반적으로 인터페이스를 이용해서 의존적인 객체의 관계를 최대한
 * 유연하게 처리할 수 있도록 한다.  의존성 주입은 의존적인 객체를 직접 생성하거나 제어하는것이 아닌, 특정 객체에 필요한 객체를 외부에서
 * 결정해서 연결 시키는 것을 의미한다. 우리는 클래스의 기능을 추상적으로 묶어둔 인터페이스를 갖다 쓰면 되는 것 이다. 이러한 의존성
 * 주입으로인해 모듈간의 결합도가 낮아지고 유연성이 높아진다.
 */


