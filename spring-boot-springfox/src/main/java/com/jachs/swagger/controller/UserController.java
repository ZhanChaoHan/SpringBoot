package com.jachs.swagger.controller;

import com.jachs.swagger.annotation.SwaggerCustomIgnore;
import com.jachs.swagger.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

/**
 * @Description 用户控制层
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @SwaggerCustomIgnore
    @GetMapping("/list")
    public Object getUserList(){
        return Arrays.asList(User.builder().age(18).username("张三").build());
    }

    @PostMapping("create")
    public Object creteUser(){
        return User.builder().age(18).username("李四").build();
    }
}
