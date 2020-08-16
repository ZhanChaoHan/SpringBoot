package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jachs.security.service.PartService;

@Controller
@RequestMapping("/part")
public class PartController {
	@Autowired
	private PartService partService;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		
		return "helper.test";
	}
}
