package com.jachs.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.security.dao.RoleUserDao;
import com.jachs.security.entity.RoleUser;
import com.jachs.security.service.RoleUserService;

@Service
public class RoleUserServiceImpl implements RoleUserService{
    @Autowired
    private RoleUserDao roleUserDao;
    
    @Override
    public int addUser ( RoleUser roleUser ) {
        return roleUserDao.addUser(roleUser);
    }

}
