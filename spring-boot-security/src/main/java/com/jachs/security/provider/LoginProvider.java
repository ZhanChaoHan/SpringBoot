package com.jachs.security.provider;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jachs.security.config.PasswordEncoderConfig;
import com.jachs.security.entity.RoleUser;
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

    @Override
    public Authentication authenticate ( Authentication authentication ) {
        // 表单输入的用户名
        String username = (String) authentication.getPrincipal ();
        // 表单输入的密码
        String password = (String) authentication.getCredentials ();
        RoleUser userDetails = loginService.loadUserByUsername ( username );
        if ( userDetails == null ) {
            throw new UsernameNotFoundException ( "用户不存在" );
        }
        if ( userDetails.isAccountNonExpired () ) {
            
        }
        if ( userDetails.isAccountNonLocked () ) {
            throw new LockedException("该用户已被冻结");
        }
        if ( userDetails.isCredentialsNonExpired () ) {
            
        }
        if ( userDetails.isEnabled ()) {
            throw new DisabledException("用户被禁用");
        }
        // 对加密密码进行验证
        if ( passwordEncoderConfig.matches ( password, userDetails.getPassword () ) ) {
            return new UsernamePasswordAuthenticationToken ( username, password, userDetails.getAuthorities () );
        }
        else {
            throw new BadCredentialsException ( "密码错误" );
        }

    }

    @Override
    public boolean supports ( Class<?> authentication ) {
        return true;
    }

}
