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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.jachs.security.handler.security.LoginFailureHandler;
import com.jachs.security.handler.security.LoginSuccessHandler;
import com.jachs.security.service.LoginService;
import com.jachs.security.service.RememberMeTokenService;


/****
 * SpringCecurity主要配置
 * @author zhanchaohan
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginService loginService;
    //登录成功handler
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    //登录失败handler
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private RememberMeTokenService rememberMeTokenService;
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public PasswordEncoder passwordEncoder () {
        // Spring Security 提供的密码加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder ();
    }

    /****
     * formLogin() 普通表单登录 <br>
     * oauth2Login() 基于 OAuth2.0 认证/授权协议 <br>
     * openidLogin() 基于 OpenID 身份认证规范
     */
    @Override
    public void configure ( HttpSecurity http ) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .antMatcher ( "/**" ).authorizeRequests ();
        // 禁用CSRF 开启跨域
        http.csrf ().disable ().cors ();

        // 标识只能在 服务器本地ip[127.0.0.1或localhost] 访问`/login/*`接口，其他ip地址无法访问
        registry.antMatchers ( "/login/*" ).hasIpAddress ( "127.0.0.1" );

        HttpSecurity httpSecurity=http.authorizeRequests ().and ();
        
        //放行所有login下接口地址
        registry.antMatchers ( "/login/*" ).permitAll ().anyRequest ().authenticated ();
        //配置成功失败处理器
        httpSecurity.formLogin ()
            .loginPage ( "/login/golog" )//登录页面url
            .loginProcessingUrl("/login/mylogin")          //指定验证凭据的URL，和表单路径一样
                .successHandler ( loginSuccessHandler )//成功登录处理器
                .failureHandler ( loginFailureHandler );//失败登录处理器
            
        //配置持久化
        httpSecurity.rememberMe ()
        .rememberMeServices ( persistentTokenBasedRememberMeServices () )
                .key ( "remember-me" );
        
        httpSecurity.rememberMe ()
        .userDetailsService(loginService)
        .tokenRepository(persistentTokenRepository())
        .tokenValiditySeconds(3600);     //设置token过期时间
        
    }


    /**
     * 忽略拦截url或静态资源文件夹
     * 
     * @param web
     * @throws Exception
     */
    @Override
    public void configure ( WebSecurity web ) throws Exception {
        web.ignoring ().antMatchers ( HttpMethod.GET, "/favicon.ico", "/*.html", "/**/*.css", "/**/*.js" );
    }

    @Autowired
    public void configureGlobal ( AuthenticationManagerBuilder auth ) throws Exception {
        //		  auth.inMemoryAuthentication()
        //	        .withUser("admin").password("admin").roles("ADMIN")
        //	        .and()
        //	        .withUser("jachs").password("BCrypt").roles("USER")
        //	        .and()
        //	        .withUser("test").password("test").roles("USER");
    }
    /**
     * 持久化token
     * 
     * Security中，默认是使用PersistentTokenRepository的子类InMemoryTokenRepositoryImpl，将token放在内存中
     * 如果使用JdbcTokenRepositoryImpl，会创建表persistent_logins，将token持久化到数据库
     */
    @Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices () {
        PersistentTokenBasedRememberMeServices services = new PersistentTokenBasedRememberMeServices ( "remember-me",
                loginService, rememberMeTokenService );
        services.setTokenValiditySeconds ( 3600 );
        services.setParameter ( "rememberMe" );
        return services;
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);   //首次设置为true,自动创建表，如果这里不设置为true就需要自己手动创建表
        return tokenRepository;
    }
}