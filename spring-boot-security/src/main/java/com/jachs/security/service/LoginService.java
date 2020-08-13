package com.jachs.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.entity.SecurityUser;

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
        ud.setAccountNonExpired ( true );
        ud.setAccountNonLocked ( false );
        ud.setAuthorities ( gaSet );
        ud.setEnabled ( true );
        return ud;
    }


}
