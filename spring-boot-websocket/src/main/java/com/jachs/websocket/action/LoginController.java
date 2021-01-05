package com.jachs.websocket.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URLDecoder;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.jachs.websocket.entity.Message;
import com.jachs.websocket.entity.Status;


/***
 * 
 * @author zhanchaohan
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
	public String index() {
		try {
			logger.info("初始化登陆页面...");
			File path =new File(ResourceUtils.getURL("classpath:").getPath());
			
			
			BufferedWriter fileWriter=new BufferedWriter(new FileWriter(URLDecoder.decode(path.getAbsolutePath(), "UTF-8")+File.separator+"static"+File.separator+"js"+File.separator+"Status.json"));
			Gson gson=new Gson();
			String []json=new String []{gson.toJson(Status.values()),gson.toJson(new Message("",false,Status.SENDMESS,""))};
			fileWriter.write(gson.toJson(json));
			fileWriter.close();
			return "/html/chess/chess";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化登陆页失败...");
			return "/login/error";
		}
	}
	
//	@RequestMapping(value = "/status.do",produces="application/json;charset=UTF-8")
//	@ResponseBody
//	public Object status(String callback) {
//		try {
//			logger.info("获取状态码");
//			Gson gson=new Gson();
//			String []json=new String []{gson.toJson(Status.values()),gson.toJson(new Message("",false,Status.SENDMESS,""))};
//			return callback+"("+gson.toJson(json)+")";
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("获取状态码异常...");
//			return null;
//		}
//	}
}
