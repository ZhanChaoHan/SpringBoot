package com.jachs.security.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.jachs.security.handler.security.AccessDeniedServletHandler;
import com.jachs.security.handler.security.LoginFailureHandler;
import com.jachs.security.handler.security.LoginOutHandler;
import com.jachs.security.handler.security.LoginSuccessHandler;
import com.jachs.security.handler.security.UserAuthenticationEntryPointHandler;
import com.jachs.security.provider.LoginProvider;
import com.jachs.security.service.LoginService;
import com.jachs.security.service.RememberMeTokenService;

/****
 * SpringCecurity主要配置
 * 
 * @author zhanchaohan
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoginService loginService;
	// 登录成功handler
	@Resource
	private LoginSuccessHandler loginSuccessHandler;
	// 登录失败handler
	@Resource
	private LoginFailureHandler loginFailureHandler;
	@Resource
    private AccessDeniedServletHandler accessDeniedServletHandler;
	@Resource
	private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
	// 登出handler
	@Resource
	private LoginOutHandler loginOutHandler;
	@Autowired
	private RememberMeTokenService rememberMeTokenService;
	@Autowired
	private LoginProvider loginProvider;
	@Autowired
	private DataSource dataSource;

	/****
	 * formLogin() 普通表单登录 <br>
	 * oauth2Login() 基于 OAuth2.0 认证/授权协议 <br>
	 * openidLogin() 基于 OpenID 身份认证规范
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
				.antMatcher("/**").authorizeRequests();
		// 禁用CSRF 开启跨域
		http.csrf().disable().cors();

		// 标识只能在 服务器本地ip[127.0.0.1或localhost] 访问`/login/*`接口，其他ip地址无法访问
		registry.antMatchers("/login/*").hasIpAddress("127.0.0.1");

		HttpSecurity httpSecurity = http.authorizeRequests().and();

		registry
		.antMatchers("/login/*").permitAll()// 放行所有login下接口地址
		.antMatchers("/gavefive/*","/helper/*").hasRole("UserA")
		.antMatchers("/modular/*","/part/*").hasRole("UserB")
		.antMatchers(HttpMethod.POST,"/gavefive/*","/helper/*").hasRole("PostA")
		.antMatchers(HttpMethod.POST,"/modular/*","/part/*").hasRole("PostB")
		.antMatchers(HttpMethod.GET,"/gavefive/*","/helper/*").hasRole("GetA")
        .antMatchers(HttpMethod.GET,"/modular/*","/part/*").hasRole("GetB")
		.antMatchers("/helper/*","/modular/*","/gavefive/*","/part/*").hasRole("Jachs")
		.anyRequest().authenticated()
		.and().exceptionHandling().accessDeniedHandler(accessDeniedServletHandler);
		
		// 配置成功失败处理器
		httpSecurity
		.httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
		.and ().formLogin()
			.loginPage("/login/golog")// 登录页面url
			.loginProcessingUrl("/login/mylogin") // 指定验证凭据的URL，和表单路径一样
			.successHandler(loginSuccessHandler)// 成功登录处理器
			.failureHandler(loginFailureHandler)
			.and().logout().logoutUrl("/login/logout")// 失败登录处理器
			.logoutSuccessHandler(loginOutHandler).permitAll();// 注销成功处理器
		
		// 配置持久化
		httpSecurity.rememberMe().key("remember-me")
		.rememberMeServices(persistentTokenBasedRememberMeServices())//自定义的
//		.userDetailsService(loginService).tokenRepository(persistentTokenRepository());//官方自定义的
		.tokenValiditySeconds(3600);// 设置token过期时间
	}

	/**
	 * 忽略拦截url或静态资源文件夹
	 * 
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/favicon.ico", "/*.html", "/**/*.css", "/**/*.js");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(loginProvider);
		// auth.inMemoryAuthentication()
		// .withUser("admin").password("admin").roles("ADMIN")
		// .and()
		// .withUser("jachs").password("BCrypt").roles("jachs")
		// .and()
		// .withUser("test").password("test").roles("USER");
	}

	/**
	 * 持久化token
	 * 
	 * Security中，默认是使用PersistentTokenRepository的子类InMemoryTokenRepositoryImpl，将token放在内存中
	 * 如果使用JdbcTokenRepositoryImpl，会创建表persistent_logins，将token持久化到数据库
	 */
	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
//		rememberMeTokenService.setCreateTableOnStartup(true);//首次设置为true,自动创建表，如果这里不设置为true就需要自己手动创建表
		PersistentTokenBasedRememberMeServices services = new PersistentTokenBasedRememberMeServices("remember-me",
				loginService, rememberMeTokenService);
//		services.setTokenValiditySeconds(3600);
//		services.setParameter("rememberMe");
		return services;
	}
	/***
	 * 官方默认的数据库持久化
	 * InMemoryTokenRepositoryImpl ：仅用于测试保存内存中。
	 * JdbcTokenRepositoryImpl ：存储令牌到数据库中。
	 * @return
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);   //首次设置为true,自动创建表，如果这里不设置为true就需要自己手动创建表
		return tokenRepository;
	}
}