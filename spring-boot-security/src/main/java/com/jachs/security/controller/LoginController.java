package com.jachs.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/login")
public class LoginController {

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
	@ResponseBody
	public Object login() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication.getDetails ();
	}
}
