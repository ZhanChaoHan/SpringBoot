package com.jachs.swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户控制层
 **/
@RestController
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/toTest")
	public String getUserList() {
		return "ToTOtoTest";
	}

	@RequestMapping("/go")
	public String creteUser() {
		return "gogogogo";
	}
}
