package com.jachs.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jachs.security.dao.RoleUserDao;
import com.jachs.security.entity.RoleUser;
import com.jachs.security.service.RoleUserService;

/****
 * RoleUser增删改查
 * @author zhanchaohan
 *
 */
@Service
public class RoleUserServiceImpl implements RoleUserService{
    @Autowired
    private RoleUserDao roleUserDao;
    
    @Override
    public boolean addUser ( RoleUser roleUser ) {
        return roleUserDao.addUser(roleUser);
    }

}
