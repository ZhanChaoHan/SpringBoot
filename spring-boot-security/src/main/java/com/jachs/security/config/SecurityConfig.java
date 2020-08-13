package com.jachs.security.config;

import javax.annotation.Resource;

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
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.jachs.security.handler.security.LoginFailureHandler;
import com.jachs.security.handler.security.LoginSuccessHandler;
import com.jachs.security.service.impl.LoginService;
import com.jachs.security.service.impl.RememberMeTokenService;

/****
 * 
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

        //放行所有login下接口地址
        registry.antMatchers ( "/login/*" ).permitAll ().anyRequest ().authenticated ();

        http.authorizeRequests ().and ().formLogin ().loginPage ( "/login/golog" )//登录页面url
                //         .loginProcessingUrl("/login/log")//登录验证url
                //如果不想表单使用默认用户名密码命名修改一下二个参数
                //	         .passwordParameter("username")
                //	         .usernameParameter("password")
                //指定登录页的路径,任何人都可访问,第二个参数，如果不写成true，则默认登录成功以后，访问之前被拦截的页面，而非去我们规定的页面
                //         .defaultSuccessUrl("/hello.html", true)
                .successHandler ( loginSuccessHandler )//成功登录处理器
                .failureHandler ( loginFailureHandler )//失败登录处理器
                .and ()
                // 关闭下 CSRF ，否则表单得不到提交，或者在表单里面添加一个 hidden 属性，提交csrf；
                .csrf ().disable ().httpBasic ();

        http.authorizeRequests ().and ().rememberMe ()
        .rememberMeServices ( persistentTokenBasedRememberMeServices () )
                //         .tokenRepository(persistentTokenRepository()) // 设置数据访问层
                .key ( "remember-me" );
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

}