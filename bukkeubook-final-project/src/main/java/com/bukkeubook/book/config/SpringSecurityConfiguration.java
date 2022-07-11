package com.bukkeubook.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity	//권한 설정 -> 설정파일이면서 시큐리티 설정(권한 및 경로 포함)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
//	WebSecurityConfigurerAdapter< Adapter는 인터페이스의 강제성 추상메소드를 오버라이드만 해놓은 아이.
//	내가 원하는 메소드만 재정의하여 사용가능
	
//	private MemberService memberService;
//	
//	@Autowired
//	public SpringSecurityConfiguration(MemberService memberService) {
//		this.memberService = memberService;
//	}
//	
//	/* 1. 암호화 처리를 위한 PasswordEncoder를 Bean으로 설정 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	//빈등록만 해주면 알아서 비번 확인을 할 수 있다.
	}
	
	/* 2. 시큐리티 설정을 무시할 정적 리소스 등록 
	 *    resources안의 static 폴더 내부의 정적 리소스 요청 무시*/
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**","/js/**","/images/**","/lib/**");
	}
	
	/* 3. HTTP요청에 대한 권한 설정 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		/* csrf: 토큰 위조공격을 막기 위한 것(default가 'on'인 상태) 크로스사이트리퀘스트포저리(cross cite request forgery)
		 *       해커가 클라이언트의 정보를 빼돌려서 위조하는 행위 방어 
		 *       구현의 편의를 위해서 disable로 하자.*/
		http.csrf().disable()		// 구현의 편리를 위해 disable로 함
		    .authorizeRequests()	// 요청에 대한 권한체크를 어떻게 할 것인지
//		        .antMatchers("/menu/**").authenticated()							// /menu/**에 대해서는 하나하나 권한을 등록하겠다.(인증된 사용자만 접근 가능(로그인 후))
//		        .antMatchers(HttpMethod.GET,"/menu/**").hasRole("MEMBER")			//이메소드를 통해서 각 회원의 권한을 찾기 때문에 튜플을 저장할 때 ROL_을 꼭 반드시 작성해야 한다.(ROLE_MEMBER와 일치하면 허용하겠다)
//		        .antMatchers(HttpMethod.POST,"/menu/**").hasRole("ADMIN")
//		        .antMatchers("/order/**").hasRole("MEMBER")
//		        .antMatchers("/admin/**").hasRole("ADMIN")
		        .anyRequest().permitAll()											// 등록되지 않은 경로는 누구나 접근 가능
//		    .and()	//로그인 요청
//		    	.formLogin()														// 로그인 form 을 따로 이용해 로그인 처리를 할 것이다.
//		    	.loginPage("/member/login")											// 기본적으로 스프링 시큐리티에서 제공하는 로그인 화면 외에 로그인 화면을 따로 구성하겠다.
//		    																		//(권한이 획득되지않아 로그인이 필요한 상황에서도 사용가능한 페이지)
//		    	.successForwardUrl("/")										
//		    .and()	//로그아웃 페이지
//		    	.logout()															// 로그아웃 설정
//		    	.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))	// 로그아웃 시 요청 경로
//		    	.deleteCookies("JSESSIONID")										// 쿠키 제거
//		    	.invalidateHttpSession(true)										//Session정보 무효화
//		    	.logoutSuccessUrl("/")
		    .and()	//에러 페이지
		    	.exceptionHandling()												//exception 핸들링 설정
		    	.accessDeniedPage("/common/denied");								//접근 거부시 경로 설정
//	}
//	
//	/* 4. 권한을 등록할 때 인증할 비즈니스 로직이 어떤것인지 등록(MemberService 계층에서 할 예정) */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
//		
//		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//		
	}
	
}
