package com.jachs.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.entity.SecurityUser;
/****
 * 登录Action
 * @author zhanchaohan
 *
 */
@Service
public class LoginService implements  UserDetailsService {

    /**
     * 接口提供的默认查询方法
     */
    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException{
        Set<SecurityUser> gaSet = new HashSet<> ();
        gaSet.add ( new  SecurityUser ( 1, "ROLE_A" ));
        gaSet.add ( new  SecurityUser ( 2, "ROLE_B" ));
        RoleUser ud = new RoleUser ();
        ud.setUsername ( username );
        ud.setPassword ( "oooo" );
        //指示用户的帐户是否已过期。过期帐户不能已验证
        ud.setAccountNonExpired ( false );
        //指示用户是锁定还是解锁。锁定的用户不能 已验证。
        ud.setAccountNonLocked ( false );
        ud.setAuthorities ( gaSet );
        //指示用户是启用还是禁用。禁用的用户不能已验证。
        ud.setEnabled ( false );
        return ud;
    }


}
