package com.jachs.security.provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jachs.security.config.PasswordEncoderConfig;
import com.jachs.security.service.LoginService;

/****
 * 验证用户
 * 
 * @author zhanchaohan
 *
 */
@Component
public class LoginProvider implements AuthenticationProvider {
    @Autowired
    private LoginService loginService;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource
            .getAccessor();
    
    @Override
    public Authentication authenticate ( Authentication authentication ) {
        // 表单输入的用户名
        String username = (String) authentication.getPrincipal ();
        // 表单输入的密码
        String password = (String) authentication.getCredentials ();
        UserDetails userDetails = loginService.loadUserByUsername ( username );
        if ( userDetails == null ) {
            throw new UsernameNotFoundException ( "用户不存在" );
        }
        if (userDetails.isAccountNonLocked()) {
            throw new LockedException(messages.getMessage(
                    "AccountStatusUserDetailsChecker.locked", "User account is locked"));
        }
        if (userDetails.isEnabled()) {
            throw new DisabledException(messages.getMessage(
                    "AccountStatusUserDetailsChecker.disabled", "User is disabled"));
        }
        if (userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(
                    messages.getMessage("AccountStatusUserDetailsChecker.expired",
                            "User account has expired"));
        }
        if (userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(messages.getMessage(
                    "AccountStatusUserDetailsChecker.credentialsExpired",
                    "User credentials have expired"));
        }
        // 对加密密码进行验证
        if ( passwordEncoderConfig.matches ( password, userDetails.getPassword () ) ) {
            return new UsernamePasswordAuthenticationToken ( username, password, userDetails.getAuthorities () );
        }
        else {
            throw new BadCredentialsException ( "密码错误" );
        }

    }
    /***
     * 是否支持赋予权限集合
     */
    @Override
    public boolean supports ( Class<?> authentication ) {
        return true;
    }

}
