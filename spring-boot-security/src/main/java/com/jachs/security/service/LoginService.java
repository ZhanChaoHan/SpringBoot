package com.jachs.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jachs.security.dao.RoleUserDao;
import com.jachs.security.entity.RoleUser;

/****
 * 登录认证
 * @author zhanchaohan
 *
 */
@Service
public class LoginService implements  UserDetailsService {
	@Autowired
	private RoleUserDao roleUserDao;
    /**
     * 接口提供的默认查询方法
     */
    @Override
    public RoleUser loadUserByUsername ( String username ) throws UsernameNotFoundException{
//        Set<SecurityUser> gaSet = new HashSet<> ();
//        gaSet.add ( new  SecurityUser ( 1, "ROLE_Jachs" ));
//        gaSet.add ( new  SecurityUser ( 2, "ROLE_User" ));
//        RoleUser ud = new RoleUser ();
//        ud.setUsername ( username );
//        ud.setPassword ( "oooo" );
//        //指示用户的帐户是否已过期。过期帐户不能已验证 。 ture失效
//        ud.setAccountNonExpired ( true );
//        //指示用户是锁定还是解锁。锁定的用户不能 已验证。true锁定
//        ud.setAccountNonLocked ( true );
//        //指示用户是启用还是禁用。禁用的用户不能已验证。
//        ud.setEnabled ( true );
//        ud.setAuthorities ( gaSet );
    	
    	RoleUser ud=roleUserDao.queryRoleUser(username);
    	if(ud!=null) {
    		ud.setAuthorities(roleUserDao.queryAuthorities(username));
    	}
        return ud;
    }


}
