package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jachs.security.service.GaveFiveService;

@Controller
@RequestMapping("/gavefive")
public class GaveFiveController {
	@Autowired
	private GaveFiveService gaveFiveService;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "gavefive.test"+SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
