package com.jachs.security.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jachs.security.handler.security.LoginFailureHandler;
import com.jachs.security.handler.security.LoginSuccessHandler;

/****
 * 
 * @author zhanchaohan
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//登录成功handler
    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    //登录失败handler
    @Resource
    private LoginFailureHandler loginFailureHandler;
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Spring Security 提供的密码加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }
    
	/****
	 * formLogin() 普通表单登录 <br>
	 * oauth2Login() 基于 OAuth2.0 认证/授权协议 <br>
	 * openidLogin() 基于 OpenID 身份认证规范
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
         .cors()
         .and()
         	.authorizeRequests()
			//放行所有login下接口地址
			.antMatchers("/login/*").permitAll()
			.anyRequest().authenticated()
		 .and()
         .formLogin()
         .loginPage("/login/golog")//登录页面url
         .loginProcessingUrl("/login/log")//登录验证url
         	 //如果不想表单使用默认用户名密码命名修改一下二个参数
	         .passwordParameter("username")
	         .usernameParameter("password")
	     //指定登录页的路径,任何人都可访问,第二个参数，如果不写成true，则默认登录成功以后，访问之前被拦截的页面，而非去我们规定的页面
         .defaultSuccessUrl("/hello.html", true)
         .successHandler(loginSuccessHandler)//成功登录处理器
         .failureHandler(loginFailureHandler)//失败登录处理器
         .and()
         // 关闭下 CSRF ，否则表单得不到提交，或者在表单里面添加一个 hidden 属性，提交csrf；
         .csrf()
         .disable().httpBasic();
	}
	/**
     * 忽略拦截url或静态资源文件夹
     * @param web
     * @throws Exception
     */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception  {
		  auth.inMemoryAuthentication()
	        .withUser("admin").password("admin").roles("ADMIN")
	        .and()
	        .withUser("jachs").password("jachs").roles("USER")
	        .and()
	        .withUser("test").password("test").roles("USER");
	}

}