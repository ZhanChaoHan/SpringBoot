package com.jachs.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jachs.security.dao.RoleUserDao;
import com.jachs.security.entity.RoleUser;
import com.jachs.security.service.RoleUserService;

/****
 * RoleUser增删改查
 * 
 * @author zhanchaohan
 * @PreAuthorize支持Spring EL表达式
 * @see org.springframework.security.access.expression
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    @PreAuthorize( "hasRole('ROLE_Jachs')" )
    public boolean addUser ( RoleUser roleUser ) {
        return roleUserDao.addUser ( roleUser );
    }

    @Override
    @PreAuthorize( "principal.username.equals(#username)" )
    public RoleUser findUser ( String Name ) {
        return roleUserDao.queryRoleUser ( Name );
    }

}
