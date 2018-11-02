package com.sinosoft.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.normal.po.SysConfig;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.RelationShipService;


/**
 * 代码参数配置管理页面
 * @author MoNi
 * 2017年10月17日
 */
@Controller
@RequestMapping("/relationShip")
public class RelationShipController {
	
	//打印日志文档
	private  static final Log logger = LogFactory.getLog(LoginController.class);
	
	//系统代码管理类
	@Autowired
	public RelationShipService  shipService;
	
	
	/**  
	* 跳转到参数配置管理页面
	* @return String    
	* @throws  
	*/  
	@RequestMapping("/showRelationShip.do")
	public String showSyscodeConfig() {
		try {
			logger.info("展示系统代码管理页面");
			return "/setting/sysconfig/sysconfig";
		}catch(Exception e) {
			e.printStackTrace();
			return "/login/error";
		}
		
	}
	
	/**
	* 参数配置管理页面查找的方法
	* @param config
	* @return Map<String,Object>    
	* @throws  
	*/  
	@RequestMapping("/selectrelationShip.do")
	@ResponseBody
	public Map<String,Object> selectMaryList(SysConfig config){
		Map<String,Object> map=null;
		try {
			logger.info("用户点了全局查找方法...");
			map=new HashMap<String,Object>();
			List<SysConfig> list=shipService.selectByPrimaryKey(config);
			System.out.println(list);
			map.put("list", list);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("用户查找信息失败...");
		}
		return map;
	}
	
	
	/**
	 * 参数配置管理页面修改方法
	 * @param config
	 * @param session
	 * @return int    
	 * @throws  
	 */  
	@RequestMapping("/updaterelationShip.do")
	@ResponseBody
	public int UpdateMarySelect(SysConfig config,HttpSession session) {
		int num=0;
		try {
			logger.info("用户点击了修改方法...");
			//获取用户信息
			UserSession usersession=(UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			//获取用户名
			String usercode=usersession.getUserCode();
			config.setModifyTime(new Date());
			config.setModifyUserCode(usercode);
			num =shipService.updateByPrimaryKeySelective(config);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("用户信息失败...");
		}
		return num;
	}
}
