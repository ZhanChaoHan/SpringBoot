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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.po.SysCodeKey;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.SysCodeService;
/**
 * 系统代码管理页面
 * @author MoNi
 * @date: 2017年11月6日
 */
@Controller
@RequestMapping("/sysCode")
public class SysCodeController {
	//打印日志的类
	private  static final Log logger = LogFactory.getLog(LoginController.class);
	
	//系统代码管理类
	@Autowired
	public SysCodeService syscodeService;
	
	 /**
     * 展示系统代码管理的主页
     * @return
     */
    @RequestMapping(value="/showSysCode.do",method=RequestMethod.GET)
    public String showSysCode() {
    	try {
			logger.info("展示系统配置管理首页...");
			return "/setting/syscode/syscode";
		} catch (Exception e) {
			e.printStackTrace();
			return "/login/error";
		}
    	
    }
	
	/**
	 * 系统代码管理页面的添加方法
	 * @param record
	 * @param session
	 * @return Map<String,Object>    
	 * @throws  
	 */  
	@RequestMapping("/insertSelective.do")
	@ResponseBody
	public Map<String, Object> addSysCode(SysCode record,HttpSession session) {
		int count;
		Map<String,Object> map=null;
		try {
			logger.info("用户进行了添加操作...");
		    map=new HashMap<String, Object>();
			//获取用户信息
			UserSession usersession=(UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			//获取用户名
			String usercode=usersession.getUserCode();
			SysCode code=new SysCode();
			code.setCodeType(record.getCodeType());
			code.setCodeCode(record.getCodeCode());
			code.setCodeCname(record.getCodeCname());
		    code.setCreateTime(new Date());
		    code.setCreateUserCode(usercode);
		    code.setModifyTime(new Date());
		    code.setModifyUserCode(usercode);
		    code.setValidStatus(AppConst.VALID_STATUS_01);
		    //当用户点击添加，校检重复的数据
		    if(syscodeService.selectByPrimaryKey(record)==null) {
		    	count=0;
		    }else {
		    	count=1;
		    }
		    int row=syscodeService.insertSelective(code);
		    map.put("num", count);
		    map.put("msg", row);
		}catch(Exception e) {
		    	e.printStackTrace();
		    	logger.info("用户添加信息失败...");
		    }
			return map;
	}

	/**
	 * 系统代码管理页面的查询方法
	 * @param record
	 * @return Map<String,Object>    
	 * @throws  
	 */  
	@RequestMapping("/selectByPrimaryKeyList.do")
	@ResponseBody
	public Map<String, Object> selectByPrimaryKeyList(SysCode record) {
		Map<String,Object> map=null;
		try {
			logger.info("用户进行了查询操作...");
			map=new HashMap<String, Object>();
			List<SysCode> list=syscodeService.selectByPrimaryKeyList(record);
			map.put("list",list);
			}catch(Exception e) {
				e.printStackTrace();
				logger.info("用户查询信息失败...");
			}
		return map;
	}
	
	/**
	 * 系统代码管理页面的删除的方法
	 * @param key
	 * @return Map<String,Object>    
	 * @throws  
	 */  
	@RequestMapping("/deleteSysCodes.do")
	@ResponseBody
	public Map<String ,Object > deleteSysCodes(SysCodeKey key) {
		Map<String ,Object> map=null;
		try {
			logger.info("用户进行了删除的操作...");
			map=new HashMap<String,Object>();
			int count;
			 if(syscodeService.selectByPrimaryKey(key)==null) {
			    	count=0;
			    }else {
			    	count=1;
			    }
			int row=syscodeService.deleteByPrimaryKey(key);
			map.put("msg1", count);
			map.put("msg2", row);
			}catch(Exception e) {
				e.printStackTrace();
				logger.info("用户删除信息失败...");
			}
		return map;
	}
	
	/**
	 * 系统代码管理页面的修改的方法
	 * @param record
	 * @param session
	 * @return int    
	 * @throws  
	 */  
	@RequestMapping("/updateByPrimaryKeySelective.do")
	@ResponseBody
	public int updateByPrimaryKeySelective(SysCode record,HttpSession session) {
		int num = 0 ;
		try {
		logger.info("用户进行了修改的操作...");
		//获取用户信息
		UserSession usersession=(UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		//获取用户名
		String usercode=usersession.getUserCode();
		record.setModifyTime(new Date());
		record.setModifyUserCode(usercode);
		num=syscodeService.updateByPrimaryKeySelective(record);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("用户修改信息失败...");
		}
		return num;
	}
} 