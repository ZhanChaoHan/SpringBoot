package com.jachs.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jachs.swagger.entity.Group;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/group")
public class GroupController {
	@ApiOperation(value="群组",notes = "跳转到群组")
	@GetMapping("/toGroup")
	public Group getUserList(@ApiParam("用户名")String name,@ApiParam("密码") String pwd) {
		return new Group(name,pwd);
		
	}

	@GetMapping("/go")
	public String creteUser() {
		return "gogogogo";
	}
}
