package com.sinosoft.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.normal.po.SysCompany;
import com.sinosoft.normal.po.SysGroup;
import com.sinosoft.normal.po.SysUser;
import com.sinosoft.normal.po.SysUserGroup;
import com.sinosoft.normal.po.SysUserGroupKey;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.util.AppUtil;
import com.sinosoft.normal.vo.SysUserVo;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.SysCompanyService;
import com.sinosoft.service.SysGroupService;
import com.sinosoft.service.SysUserGroupService;
import com.sinosoft.service.SysUserService;


/**
 * @author caoLong
 * 用户管理
 * 2017年10月24日
 */
@Controller
@RequestMapping(value="/sysUser")
public class SysUserController {
	 private  static final Log logger = LogFactory.getLog(SysUserController.class);

		@Autowired//用户业务类
		private SysUserService sysUserService;
		
		@Autowired //配置表
		private SysCompanyService sysCompanyService;
		
		@Autowired //岗位权限的业务类
		private SysGroupService sysGroupService;
		
		@Autowired //用户岗位权限的业务类
		private SysUserGroupService sysUserGroupService;
		
		
		//跳转到用户查询的界面
		@RequestMapping(value="/querySysUser.do")
		public String goquerySysUser(HttpServletRequest request, HttpServletResponse response) {
			//查询下拉选机构代码
			List<SysCompany> sysCompanyList=sysCompanyService.selectSysCompany(AppConst.SUPER_COM_CODE);
			request.setAttribute("sysCompanyList", sysCompanyList);  
			logger.info("正在跳转至用户页...");
			return "/authority/sysuser/querySysUser";
		}
		
		//跳转到用户查询结果页
		@RequestMapping(value="/querySysUserReturn.do")
		@ResponseBody	
		public Map<String, Object> querySysUserReturn(
				@RequestParam("userCode") String userCode,
				@RequestParam("userName") String userName,
				@RequestParam("comCode") String comCode,
				@RequestParam("comCname") String comCname,
				@RequestParam("telePhone") String telePhone,
				@RequestParam("email") String email){
			Map<String,Object> map=new HashMap<String, Object>();
			try {	
				 //条件查询
				 List<SysUserVo>  sysUserList = sysUserService.querySysUser(userCode,userName,comCode,comCname,telePhone,email,AppConst.ADMIN,AppConst.VALID_STATUS_01);
				 map.put("sysUserList", sysUserList);
				 logger.info("跳转用户条件查询结果页面...");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("用户条件查询页面异常...");
			}
			return map;
		}
		
		//跳转到用户管理添加页面
		@RequestMapping(value="/editSysUser.do",method=RequestMethod.GET)  
	    public String editSysUser (HttpServletRequest request, HttpServletResponse response) throws Exception{
			try {
				//查询下拉选机构代码
				List<SysCompany> sysCompanyList=sysCompanyService.selectSysCompany(AppConst.SUPER_COM_CODE);
				request.setAttribute("sysCompanyList", sysCompanyList);
				logger.info("初始化添加页面...");
				return "/authority/sysuser/editSysUser";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("初始化查询页面失败...");
				return "/login/error";
			}
	    }
		//岗位管理修改回显方法
		@RequestMapping(value="/modifySysUser.do")  
		public String modifySysUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
//			Map<String,Object> map=new HashMap<String, Object>();
			try {
				//获取页面的主键
				String userCodeVal = request.getParameter("userCode"); 
				//查询下拉选机构代码
				List<SysCompany> sysCompanyList=sysCompanyService.selectSysCompany(AppConst.SUPER_COM_CODE);
				request.setAttribute("sysCompanyList", sysCompanyList);
				//查询修改对象
				SysUser sysUser = sysUserService.selectByPrimaryKey(userCodeVal,AppConst.VALID_STATUS_01);
				if(sysUser != null) {
					//修改数据回显
					SysUserVo userVo = new SysUserVo();
					userVo.setUserCode(sysUser.getUserCode());
					userVo.setUserName(sysUser.getUserName());
					userVo.setComCode(sysUser.getComCode());
					userVo.setComCname(sysUser.getComCode());
					userVo.setEmail(sysUser.getEmail());
					userVo.setTelePhone(sysUser.getTelePhone());
					request.setAttribute("userVo", userVo);
				}
				 logger.info("进行修改操作...");
				 return "/authority/sysuser/modifySysUser";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("修改操作异常...");
				return "/login/error";
			}
	    }
		//岗位管理修改保存方法
		@RequestMapping(value="/updateSysUser.do")
		@ResponseBody	
		public Map<String, Object> updateSysUser(
				@RequestParam("userCode") String userCode,
				@RequestParam("userName") String userName,
				@RequestParam("telePhone") String telePhone,
				@RequestParam("email") String email,
				HttpSession session){
			Map<String,Object> map=new HashMap<String, Object>();
			UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			// 用户代码
			String modifyUserCode = userSession.getUserCode();
			try {	
				//获取用户的信息
				SysUser  sysUser=  sysUserService.selectByPrimaryKey(userCode, AppConst.VALID_STATUS_01);
				//修改保存部分信息
				if(sysUser != null) {
					SysUser User=new SysUser();
					User.setUserCode(userCode);
					User.setUserName(userName);
					User.setComCode(sysUser.getComCode());
					User.setPassWord(sysUser.getPassWord());
					User.setEmail(email);
					User.setTelePhone(telePhone);
					User.setValidStatus(AppConst.VALID_STATUS_01);
					User.setCreateTime(sysUser.getCreateTime());
					User.setCreateUserCode(sysUser.getCreateUserCode());
					User.setModifyUserCode(modifyUserCode);;
					User.setModifyTime(new Date());
					sysUserService.updateByPrimaryKey(User);
				}
				logger.info("跳转用户条件查询结果页面...");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("用户条件查询页面异常...");
			}
			return map;
		}
		
		
		//跳转用户添加页
		@RequestMapping(value="/addSysUser.do")
		@ResponseBody	
		public Map<String, Object> addSysUserReturn(
				@RequestParam("userCode") String userCode,
				@RequestParam("userName") String userName,
				@RequestParam("comCode") String comCode,
				@RequestParam("passWord") String passWord,
				@RequestParam("telePhone") String telePhone,
				@RequestParam("email") String email,
				HttpSession session){
			Map<String,Object> map=new HashMap<String, Object>();
			// 获得用户信息
			UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			// 用户代码
			String createUserCode = userSession.getUserCode();
			try {	
				//保存用户的信息
				SysUser sysUser=new SysUser();
				sysUser.setUserCode(userCode);
				sysUser.setUserName(userName);
				sysUser.setComCode(comCode);
				sysUser.setPassWord(AppUtil.md5s(passWord));
				sysUser.setTelePhone(telePhone);
				sysUser.setEmail(email);
				sysUser.setCreateTime(new Date());
				sysUser.setCreateUserCode(createUserCode);
				sysUser.setModifyTime(new Date());
				sysUser.setModifyUserCode(createUserCode);
				sysUser.setValidStatus(AppConst.VALID_STATUS_01);
				sysUserService.insert(sysUser);
				logger.info("跳转用户添加成功...");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("用户添加异常...");
			}
			return map;
		}
		
		
		//用户的删除方法
		@RequestMapping(value="/deleteSysUser.do")
		@ResponseBody	
		public Map<String, Object> deleteSysUser(@RequestParam("userCode") String userCode,
				@RequestParam("comCode") String comCode){
			Map<String,Object> map=new HashMap<String, Object>();
			try {	
				//获取机构信息
				List<SysUserGroup>	userGroup=sysUserGroupService.getSysUserGroupListByUserCode(userCode, AppConst.VALID_STATUS_01);
					//判断岗位是否存在
					if(userGroup != null && userGroup.size() > 0) {
						for(SysUserGroup group:userGroup) {
							SysUserGroupKey key=new SysUserGroupKey();
							key.setGroupCode(group.getGroupCode());
							key.setUserCode(group.getUserCode());
							//删除岗位信息
							sysUserGroupService.deleteByPrimaryKey(key);
						}
					}
					//删除用户信息
				Integer  number = sysUserService.deleteByPrimaryKey(userCode);
				map.put("number", number);
				logger.info("跳转用户条件查询结果页面...");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("用户条件查询页面异常...");
			}
			return map;
		}
		//用户校验方法
		@RequestMapping(value="/checkUserCode.do")
		@ResponseBody	
		public Map<String, Object> checkUserCode(@RequestParam("userCode") String userCode){
			Map<String,Object> map=new HashMap<String, Object>();
			try {	
				//校验用户的主键是否存在
				 SysUser  sysUser=  sysUserService.selectByPrimaryKey(userCode,AppConst.VALID_STATUS_01);
				 if (sysUser == null) {
						logger.info("数据库中不存在相同的用户代码，可以保存到数据库");
						// Ajax 返回 页面的 字符串 （1：没有重复数据，0：有重复数据）
						map.put("msg", 1);  
					}
				// 有对应数据
					else {
						logger.info("数据库中已存在相同的用户代码数据");
						// Ajax 返回 页面的 字符串 （1：没有重复数据，0：有重复数据）
						map.put("msg", 0);  
					}
					logger.info("查询用户代码信息...");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("用户代码校验异常...");
			}
			return map;
		}
		//用户岗位管理修改回显方法
		@RequestMapping(value="/modifySysUserGroup.do")  
		public String modifySysUserGroup(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
			try {
				//获得页面的主键
				String userCodeVal = request.getParameter("userCode"); 
				//获取用户的对象
				SysUser sysUser = this.sysUserService.selectByPrimaryKey(userCodeVal,AppConst.VALID_STATUS_01);
				//获取用户的机构信息
				SysCompany company=this.sysCompanyService.selectByPrimaryKey(sysUser.getComCode(), AppConst.VALID_STATUS_01);
				//获得用户能分配的岗位列表集合
				List<SysGroup> sysGroupList = sysGroupService.queryUserSysGroup(company.getComType(), AppConst.VALID_STATUS_00);
				//获得用户已拥有的岗位列表集合
				List<SysUserGroup> userGroupsList=sysUserGroupService.getSysUserGroupListByUserCode(userCodeVal, AppConst.VALID_STATUS_01);
				request.setAttribute("sysGroupList", sysGroupList);
				request.setAttribute("userGroupsList", 	userGroupsList);
				request.setAttribute("sysUser",sysUser);
				logger.info("进行分配岗位操作...");
				return "/authority/sysuser/modifyUserSysGroup";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("分配岗位操作异常...");
				return "/login/error";
			}
	    }	
		//用户岗位添加方法
		@RequestMapping(value="/updateSysUserGroup.do")  
		public String updateSysUserGroup(HttpServletRequest request, HttpServletResponse response
				,HttpSession session)
			throws Exception{
			// 获得用户信息
			UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			// 用户代码
			String userCode = userSession.getUserCode();
			try {
				//获得页面的主键
				String userCodeVal = request.getParameter("userCode"); 
				//循环页面中选中的岗位代码，获得用户岗位对象，添加到用户岗位集合中
				String[] data = request.getParameterValues("selected");
				List<SysUserGroup> group = sysUserGroupService.getSysUserGroupListByUserCode(userCodeVal, AppConst.VALID_STATUS_01);
				if(group!=null && group.size() > 0) {
					for(SysUserGroup ss:group) {
						sysUserGroupService.deleteByPrimaryKey(ss);
					}
				}
				SysUserGroup sysUserGroup = null;
				SysUserGroupKey SysUserGroupKey = null;
				List<SysUserGroup> sysUserGrouplist = new ArrayList<SysUserGroup>();
				if(data != null && data.length > 0){
					for(String box : data){
						SysUserGroupKey = new SysUserGroupKey();
						SysUserGroupKey.setGroupCode(box);
						SysUserGroupKey.setUserCode(userCodeVal);
						sysUserGroup = new SysUserGroup();
						sysUserGroup.setGroupCode(box);
						sysUserGroup.setUserCode(userCodeVal);
						sysUserGroup.setCreateTime(new Date());
						sysUserGroup.setCreateUserCode(userCode);
						sysUserGroup.setValidStatus(AppConst.VALID_STATUS_01);
						sysUserGrouplist.add(sysUserGroup);
					}
					//保存岗位
					this.sysUserGroupService.saveSysUserGroupAll(sysUserGrouplist);
				}
				logger.info("进行分配岗位保存...");
				return "/authority/sysuser/querySysUser";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("分配岗位保存异常...");
				return "/login/error";
			}
	    }	
}
