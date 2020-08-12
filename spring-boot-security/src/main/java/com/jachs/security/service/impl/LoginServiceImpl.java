package com.jachs.security.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.entity.SecurityUser;
import com.jachs.security.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    /**
     * 接口提供的默认查询方法
     */
    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {

        return null;
    }

    @Override
    public UserDetails loadUserByUsername ( String username , String password ) {
        Set<SecurityUser> gaSet = new HashSet<> ();
        gaSet.add ( new  SecurityUser ( 1, "COCKcS" ));
        gaSet.add ( new  SecurityUser ( 2, "mjsh" ));
        RoleUser ud = new RoleUser ();
        ud.setUsername ( username );
        ud.setPassword ( password );
        ud.setAccountNonExpired ( true );
        ud.setAccountNonLocked ( false );
        ud.setAuthorities ( gaSet );
        ud.setEnabled ( true );
        return ud;
    }

}
