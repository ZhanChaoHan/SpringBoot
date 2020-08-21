package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.service.RoleUserService;

@Controller
@RequestMapping( "/roleuser" )
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;
    
    @RequestMapping("/addUser")
    public void addUser(RoleUser roleUser) {
        roleUserService.addUser(roleUser);
    }
}
