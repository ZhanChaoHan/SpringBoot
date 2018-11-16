package com.jachs.websocket.action;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Log logger = LogFactory.getLog(LoginController.class);

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		try {
			logger.info("初始化登陆页面...");
			Status[]StatusArr=Status.values();
			
			return "/html/chess/chess";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化登陆页失败...");
			return "/login/error";
		}
	}
}
