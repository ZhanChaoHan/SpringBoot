package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jachs.security.entity.RoleUser;
import com.jachs.security.service.RoleUserService;

@Controller
@RequestMapping( "/roleuser" )
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;
    
    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(RoleUser roleUser) {
        return roleUserService.addUser(roleUser);
    }
    @RequestMapping("/findUser")
    @ResponseBody
    public RoleUser findUser(String Name) {
        return roleUserService.findUser(Name);
    }
}
