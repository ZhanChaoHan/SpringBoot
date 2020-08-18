package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jachs.security.service.ModularService;

@Controller
@RequestMapping("/modular")
public class ModularController {
	@Autowired
	private ModularService modularService;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "modular.test"+SecurityContextHolder.getContext().getAuthentication();
	}
}
