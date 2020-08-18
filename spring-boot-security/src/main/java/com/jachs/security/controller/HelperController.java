package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jachs.security.service.HelperService;

@Controller
@RequestMapping("/helper")
public class HelperController {
	@Autowired
	private HelperService helperService;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "helper.test"+SecurityContextHolder.getContext().getAuthentication();
	}
}
