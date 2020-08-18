package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value ="/pa", method = RequestMethod.POST)
    @ResponseBody
    public String pa() {
        return "modular.pa";
    }
    @RequestMapping(value ="/pb", method = RequestMethod.POST)
    @ResponseBody
    public String pb() {
        return "modular.pb";
    }
    
    @RequestMapping(value ="/ga", method = RequestMethod.GET)
    @ResponseBody
    public String ga() {
        return "modular.ga";
    }
    @RequestMapping(value ="/gb", method = RequestMethod.GET)
    @ResponseBody
    public String gb() {
        return "modular.gb";
    }
}
