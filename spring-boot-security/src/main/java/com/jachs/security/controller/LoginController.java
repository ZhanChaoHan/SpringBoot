package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jachs.security.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping("/failure")
	public String failure() {
		return "failure";
	}
	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}
	@RequestMapping("/golog")
	public String golog() {
		return "log";
	}
	@RequestMapping("/log")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println(username);
		System.out.println(password);
		return "hello";
	}
}
