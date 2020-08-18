package com.jachs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		return "part.test"+SecurityContextHolder.getContext().getAuthentication();
	}
	
	@RequestMapping(value ="/pa", method = RequestMethod.POST)
    @ResponseBody
    public String pa() {
        return "part.pa";
    }
    @RequestMapping(value ="/pb", method = RequestMethod.POST)
    @ResponseBody
    public String pb() {
        return "part.pb";
    }
    
    @RequestMapping(value ="/ga", method = RequestMethod.GET)
    @ResponseBody
    public String ga() {
        return "part.ga";
    }
    @RequestMapping(value ="/gb", method = RequestMethod.GET)
    @ResponseBody
    public String gb() {
        return "part.gb";
    }
}
