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

import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.po.SysCodeKey;
import com.sinosoft.normal.po.SysGroup;
import com.sinosoft.normal.po.SysGroupRole;
import com.sinosoft.normal.po.SysGroupRoleKey;
import com.sinosoft.normal.po.SysRole;
import com.sinosoft.normal.po.SysUserGroup;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.vo.QuerySysGroupVo;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.SysCodeService;
import com.sinosoft.service.SysGroupRoleService;
import com.sinosoft.service.SysGroupService;
import com.sinosoft.service.SysRoleService;
import com.sinosoft.service.SysUserGroupService;


@Controller //类似Struts的Action
@RequestMapping(value="/sysGroup")
public class SysGroupController {
	private  static final Log logger = LogFactory.getLog(SysGroupController.class);
	
	@Autowired //岗位权限的业务类
	private SysGroupService sysGroupService;
	@Autowired //配置表
	private SysCodeService sysCodeService;
	@Autowired //岗位权限的业务类
	private SysRoleService sysRoleService;
	@Autowired //机构权限业务类
	private SysGroupRoleService sysGroupRoleService;
	@Autowired //用户权限类
	private SysUserGroupService sysUserGroupService;
	
	
	//跳转到岗位管理查询方法
	// 请求url地址映射，类似Struts的action-mapping
	@RequestMapping(value="/querySysGroup.do",method=RequestMethod.GET)  
    public String querySysGroup (HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//查询机构级别维度
			List<SysCode> comLevelList =  sysCodeService.getSysCodeList(AppConst.COM_LEVEL);
			//查询机构类型维度
			List<SysCode> comTypeList =  sysCodeService.getSysCodeList(AppConst.COM_TYPE);
			request.setAttribute("comLevelList", comLevelList);  
			request.setAttribute("comTypeList", comTypeList);  
			logger.info("初始化查询页面...");
			return "/authority/sysgroup/querySysGroup";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化查询页面失败...");
			return "/login/error";
		}
    }
	
	//岗位管理查询结果方法
	// 请求url地址映射，类似Struts的action-mapping
	@RequestMapping(value="/querySysGroupResult.do")
	@ResponseBody
    public Map<String,Object> querySysGroupResult(
			@RequestParam("groupCode") String groupCode,
			@RequestParam("groupName") String groupName,
			@RequestParam("comLevel") String comLevel,
			@RequestParam("comType") String comType){
		HashMap<String,Object> map = new HashMap<String, Object>();  
		try {
			//获取当前岗位查询信息
			List<SysGroup>  groupList= sysGroupService.querySysGroup(groupCode,groupName,comLevel,comType);
			List<QuerySysGroupVo> list = new ArrayList<QuerySysGroupVo>();
			for(SysGroup sysgroup:groupList) {
				//创建查询结果展示vo
				QuerySysGroupVo vo = new QuerySysGroupVo();
				//翻译机构级别
				vo.setComLevel(sysgroup.getComLevel());
				SysCodeKey keyLevel = new SysCodeKey(); 
				keyLevel.setCodeCode(sysgroup.getComLevel());
				keyLevel.setCodeType(AppConst.COM_LEVEL);
				SysCode comLevelch =  sysCodeService.selectByPrimaryKey(keyLevel);
				vo.setComLevelCh(comLevelch.getCodeCname());
				vo.setComType(sysgroup.getComType());
				//翻译机构类型
				SysCodeKey keyType = new SysCodeKey(); 
				keyType.setCodeCode(sysgroup.getComType());
				keyType.setCodeType(AppConst.COM_TYPE);
				SysCode comTypech =  sysCodeService.selectByPrimaryKey(keyType);
				vo.setComTypeCh(comTypech.getCodeCname());
				vo.setGroupCode(sysgroup.getGroupCode());
				vo.setGroupName(sysgroup.getGroupName());
				list.add(vo);
			}
			map.put("groupList", list);
			logger.info("查询岗位信息...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询岗位信息失败...");
			//msg ="error";
		}
		return map;
    }
	
	
	//跳转到岗位管理添加页面
	@RequestMapping(value="/editSysGroup.do",method=RequestMethod.GET)  
    public String editsysGroup (HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//查询机构级别维度
			List<SysCode> comLevelList =  sysCodeService.getSysCodeList(AppConst.COM_LEVEL);
			//查询机构类型维度
			List<SysCode> comTypeList =  sysCodeService.getSysCodeList(AppConst.COM_TYPE);
			request.setAttribute("comLevelList", comLevelList);  
			request.setAttribute("comTypeList", comTypeList);  
			logger.info("初始化添加页面...");
			return "/authority/sysgroup/editSysGroup";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化查询页面失败...");
			return "/login/error";
		}
    }
	
	//岗位管理校验主键
	@RequestMapping(value="/checkGroupCode.do")
	@ResponseBody
    public Map<String,Object> checkGroupCode(
			@RequestParam("groupCode") String groupCode
			){
		HashMap<String,Object> map = new HashMap<String, Object>(); 
		try {
			//打印添加内容
			//System.out.println(groupCode);
			SysGroup sysGroup = sysGroupService.selectByPrimaryKey(groupCode);
			if (sysGroup == null) {
				logger.info("数据库中不存在相同的岗位代码，可以保存到数据库");
				// Ajax 返回 页面的 字符串 （1：没有重复数据，0：有重复数据）
				map.put("msg", 1);  
			}
			// 有对应数据
			else {
				logger.info("数据库中已存在相同的岗位代码数据");
				// Ajax 返回 页面的 字符串 （1：没有重复数据，0：有重复数据）
				map.put("msg", 0);  
			}
			logger.info("查询岗位代码信息...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询岗位代码信息失败...");
		}
		return map;
    }
	
	//岗位管理保存方法
	@RequestMapping(value="/saveSysGroup.do")
	@ResponseBody
    public Map<String,Object> saveSysGroup(
			@RequestParam("groupCode") String groupCode,
			@RequestParam("groupName") String groupName,
			@RequestParam("comLevel") String comLevel,
			@RequestParam("comType") String comType,HttpSession session){
		HashMap<String,Object> map = new HashMap<String, Object>(); 
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String userCode = userSession.getUserCode();
		try {
			//获取当前岗位查询信息
			SysGroup sysgroup = new SysGroup();
			sysgroup.setComLevel(comLevel);
			sysgroup.setComType(comType);
			sysgroup.setCreateTime(new Date());
			sysgroup.setCreateUserCode(userCode);
			sysgroup.setGroupCode(groupCode);
			sysgroup.setGroupName(groupName);
			sysgroup.setModifyTime(new Date());
			sysgroup.setModifyUserCode(userCode);
			sysgroup.setValidStatus(AppConst.VALID_STATUS_01);
			sysGroupService.insert(sysgroup);
			map.put("msg", 1);
			map.put("sysgroup", sysgroup);
			logger.info("查询岗位信息...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询岗位信息失败...");
		}
		return map;
    }

	//岗位管理删除方法
	@RequestMapping(value="/deleteSysGroup.do")  
	@ResponseBody	
	public Map<String, Object> deleteSysGroup(@RequestParam("groupCode") String groupCode){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(groupCode!=null) {
				//条件查询
				Integer  number =  sysGroupService.deleteByPrimaryKey(groupCode);
				//查询所有权限
				List<SysGroupRole> sysGroupRolelist = sysGroupRoleService.selectByGroupCode(AppConst.VALID_STATUS_01,groupCode);
				if(sysGroupRolelist!=null && sysGroupRolelist.size() > 0) {
					for(SysGroupRole sysGroupRole:sysGroupRolelist) {
						SysGroupRole key = new SysGroupRole();
						key.setGroupCode(groupCode);
						key.setRoleCode(sysGroupRole.getRoleCode());
						Integer number1= sysGroupRoleService.deleteByPrimaryKey(key);
						map.put("number1", number1);
					}
				}
				//查询所有用户岗位
				List<SysUserGroup> sysUserGrouplist = sysUserGroupService.selectByGroupCode(groupCode);
				if(sysUserGrouplist!=null && sysUserGrouplist.size() > 0) {
					for(SysUserGroup syssUserGroup:sysUserGrouplist) {
						SysUserGroup keyUser = new SysUserGroup();
						keyUser.setGroupCode(groupCode);
						keyUser.setUserCode(syssUserGroup.getUserCode());
						Integer number2= sysUserGroupService.deleteByPrimaryKey(keyUser);
						map.put("number2", number2);
					}
				}
				map.put("number", number);
			}
			 logger.info("进行删除操作...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("删除操作异常...");
		}
		return map;
    }

	//岗位管理修改方法
	@RequestMapping(value="/modifySysGroup.do")  
	public String modifySysGroup(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		try {
			//获取页面输入的查询条件
			String groupCodeVal = request.getParameter("updateGroupCode"); 
			//查询机构级别维度
			List<SysCode> comLevelList =  sysCodeService.getSysCodeList(AppConst.COM_LEVEL);
			//查询机构类型维度
			List<SysCode> comTypeList =  sysCodeService.getSysCodeList(AppConst.COM_TYPE);
			request.setAttribute("comLevelList", comLevelList);  
			request.setAttribute("comTypeList", comTypeList);  
			//查询修改对象
			SysGroup sysGroup = sysGroupService.selectByPrimaryKey(groupCodeVal);
			if(sysGroup != null) {
				//修改
				QuerySysGroupVo queryUpdate = new QuerySysGroupVo();
				//翻译机构级别
				queryUpdate.setComLevel(sysGroup.getComLevel());
				SysCodeKey keyLevel = new SysCodeKey(); 
				keyLevel.setCodeCode(queryUpdate.getComLevel());
				keyLevel.setCodeType(AppConst.COM_LEVEL);
				SysCode comLevelch =  sysCodeService.selectByPrimaryKey(keyLevel);
				queryUpdate.setComLevelCh(comLevelch.getCodeCname());
				//翻译机构类型
				queryUpdate.setComType(sysGroup.getComType());
				SysCodeKey keyType = new SysCodeKey(); 
				keyType.setCodeCode(queryUpdate.getComType());
				keyType.setCodeType(AppConst.COM_TYPE);
				SysCode comTypech =  sysCodeService.selectByPrimaryKey(keyType);
				queryUpdate.setComTypeCh(comTypech.getCodeCname());
				queryUpdate.setGroupCode(sysGroup.getGroupCode());
				queryUpdate.setGroupName(sysGroup.getGroupName());
				//Integer  number=  sysGroupService.updateByPrimaryKey(queryUpdate);
				request.setAttribute("queryUpdate", queryUpdate);
			}
			 logger.info("进行修改操作...");
			 return "/authority/sysgroup/modifySysGroup";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("修改操作异常...");
			return "/login/error";
		}
    }


	//岗位管理保存方法
	@RequestMapping(value="/saveUpdateSysGroup.do")
	@ResponseBody
    public Map<String,Object> saveUpdateSysGroup(
			@RequestParam("groupCode") String groupCode,
			@RequestParam("groupName") String groupName,
			@RequestParam("comLevel") String comLevel,
			@RequestParam("comType") String comType,HttpSession session){
		HashMap<String,Object> map = new HashMap<String, Object>(); 
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String userCode = userSession.getUserCode();
		try {
			//查询修改对象
			SysGroup sysGroup = sysGroupService.selectByPrimaryKey(groupCode);
			//获取当前岗位查询信息
			SysGroup sysgroup = new SysGroup();
			sysgroup.setComLevel(comLevel);
			sysgroup.setComType(comType);
			sysgroup.setCreateTime(sysGroup.getCreateTime());
			sysgroup.setCreateUserCode(sysGroup.getCreateUserCode());
			sysgroup.setGroupCode(sysGroup.getGroupCode());
			sysgroup.setGroupName(groupName);
			sysgroup.setModifyTime(new Date());
			sysgroup.setModifyUserCode(userCode);
			sysgroup.setValidStatus(AppConst.VALID_STATUS_01);
			sysGroupService.updateByPrimaryKey(sysgroup);
			map.put("msg", 1);
			logger.info("查询岗位信息...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询岗位信息失败...");
		}
		return map;
    }

	//跳转到权限查询方法
	// 请求url地址映射，类似Struts的action-mapping
	@RequestMapping(value="/querySysRole.do",method=RequestMethod.GET)  
    public String querySysRole (HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取页面输入的查询条件
			String groupCode = request.getParameter("saveGroupCode"); 
			//查询所有权限
			List<SysRole> sysRolelist = sysRoleService.querySysRole("01");
			List<SysRole> list = new ArrayList<SysRole>();// 返回权限
			list.addAll(recursiveSysRole("0",sysRolelist));
			request.setAttribute("list", list);  
			request.setAttribute("groupCode", groupCode);  
			logger.info("初始化添加权限页面...");
			return "/authority/sysgroup/editSysGroupRole";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化查询页面失败...");
			return "/login/error";
		}
    }

	/**
	 * 递归查找登录用户能操作的系统机构列表
	 * @param roleCode 
	 * @param allRoles 
	 * @return 系统基础权限表集合
	 */
	private List<SysRole> recursiveSysRole(String roleCode, List<SysRole> sysRolelist) {
		List<SysRole> list = new ArrayList<SysRole>();
		String upperRoleCode_ = null;
		for(SysRole role : sysRolelist) {
			upperRoleCode_ = role.getUpperRoleCode();
			if(upperRoleCode_.equals(roleCode)){
				list.add(role);
				list.addAll(this.recursiveSysRole(role.getRoleCode(), sysRolelist));
				continue;
			}
		}
		return list;
	}

	//为新的机构添加岗位
	@RequestMapping(value="/saveSysGroupRole.do")
	@ResponseBody
    public Map<String,Object> saveSysGroupRole(
    		@RequestParam("groupCode") String groupCode,
			@RequestParam("treeCheckBox") String treeCheckBox,HttpSession session){
		HashMap<String,Object> map = new HashMap<String, Object>(); 
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String userCode = userSession.getUserCode();
		try {
			//同步获取页面输入传来的值
//				String groupCode = request.getParameter("groupCode"); 
//				String[] data = request.getParameterValues("treeCheckBox");
			String [] treeCheckBoxs = treeCheckBox.split(",");
			SysGroupRole sysGroupRole = null;
			SysGroupRoleKey sysGroupRoleKey = null;
			List<SysGroupRole> sysGroupRoleList = new ArrayList<SysGroupRole>();
			if(treeCheckBoxs != null){
				for(String box : treeCheckBoxs){
					sysGroupRoleKey = new SysGroupRoleKey();
					sysGroupRoleKey.setGroupCode(groupCode);
					sysGroupRoleKey.setRoleCode(box);
					sysGroupRole = new SysGroupRole();
					sysGroupRole.setCreateTime(new Date());
					sysGroupRole.setCreateUserCode(userCode);
					sysGroupRole.setGroupCode(groupCode);
					sysGroupRole.setRoleCode(box);
					sysGroupRole.setValidStatus(AppConst.VALID_STATUS_01);
					sysGroupRoleList.add(sysGroupRole);
				}
				//保存岗位集合
				this.sysGroupRoleService.saveSysGroupRoleAll(sysGroupRoleList);
				//添加登录权限
				sysGroupRoleKey = new SysGroupRoleKey();
				sysGroupRoleKey.setGroupCode(groupCode);
				sysGroupRoleKey.setRoleCode(AppConst.ROLE_CODE);
				sysGroupRole = new SysGroupRole();
				sysGroupRole.setCreateTime(new Date());
				sysGroupRole.setCreateUserCode(userCode);
				sysGroupRole.setGroupCode(groupCode);
				sysGroupRole.setRoleCode(AppConst.ROLE_CODE);
				sysGroupRole.setValidStatus(AppConst.VALID_STATUS_01);
				this.sysGroupRoleService.insert(sysGroupRole);
			}
			map.put("msg", 1);
			logger.info("保存岗位权限信息...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询岗位权限信息失败...");
		}
		return map;
    }

	//先拿groupcode查询sysgrouprole所有的数据，放进list
	//再查询所有的sysrole ：FROM SysRole ORDER BY roleCode  放进list
	//跳转到修改岗位权限方法
	@RequestMapping(value="/modifySysGroupRole.do")  
    public String modifySysGroupRole (HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//获取页面输入的查询条件
			String updateGroupCode = request.getParameter("updateGroupCode"); 
			//查询所有权限
			List<SysGroupRole> sysGroupRolelist = sysGroupRoleService.selectByGroupCode(AppConst.VALID_STATUS_01,updateGroupCode);
			List<SysRole> sysRolelist = sysRoleService.querySysRoleAll();
			request.setAttribute("sysGroupRolelist", sysGroupRolelist);  
			request.setAttribute("sysRolelist", sysRolelist); 
			QuerySysGroupVo queryUpdate = new QuerySysGroupVo();
			queryUpdate.setGroupCode(updateGroupCode);
			request.setAttribute("queryUpdate", queryUpdate);
			logger.info("修改权限页面...");
			return "/authority/sysgroup/modifySysGroupRole";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("修改权限页面失败...");
			return "/login/error";
		}
    }
		

	//岗位管理保存方法
	@RequestMapping(value="/saveUpdateSysGroupRole.do")
	@ResponseBody
    public Map<String,Object> saveUpdateSysGroupRole(
			@RequestParam("groupCode") String groupCode,
			@RequestParam("treeCheckBox") String treeCheckBox,HttpSession session){
		HashMap<String,Object> map = new HashMap<String, Object>(); 
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String userCode = userSession.getUserCode();
		try {
			String [] treeCheckBoxs = treeCheckBox.split(",");
			SysGroupRole sysGroupRole = null;
			SysGroupRoleKey sysGroupRoleKey = null;
			List<SysGroupRole> sysGroupRoleList = new ArrayList<SysGroupRole>();
			if(treeCheckBoxs != null){
				for(String box : treeCheckBoxs){
					sysGroupRoleKey = new SysGroupRoleKey();
					sysGroupRoleKey.setGroupCode(groupCode);
					sysGroupRoleKey.setRoleCode(box);
					sysGroupRole = new SysGroupRole();
					sysGroupRole.setCreateTime(new Date());
					sysGroupRole.setCreateUserCode(userCode);
					sysGroupRole.setGroupCode(groupCode);
					sysGroupRole.setRoleCode(box);
					sysGroupRole.setValidStatus(AppConst.VALID_STATUS_01);
					sysGroupRoleList.add(sysGroupRole);
				}
				//查询所有权限
				List<SysGroupRole> sysGroupRolelist = sysGroupRoleService.selectByGroupCode(AppConst.VALID_STATUS_01,groupCode);
				if(sysGroupRolelist!= null && sysGroupRolelist.size() > 0) {
					SysGroupRoleKey key = null;
					for(SysGroupRole list:sysGroupRolelist) {
						key = new SysGroupRoleKey();
						key.setGroupCode(groupCode);
						key.setRoleCode(list.getRoleCode());
						//删除岗位权限信息
						this.sysGroupRoleService.deleteByPrimaryKey(key);
					}
				}
				this.sysGroupRoleService.saveSysGroupRoleAll(sysGroupRoleList);
				//添加登录权限
				sysGroupRoleKey = new SysGroupRoleKey();
				sysGroupRoleKey.setGroupCode(groupCode);
				sysGroupRoleKey.setRoleCode(AppConst.ROLE_CODE);
				sysGroupRole = new SysGroupRole();
				sysGroupRole.setCreateTime(new Date());
				sysGroupRole.setCreateUserCode(userCode);
				sysGroupRole.setGroupCode(groupCode);
				sysGroupRole.setRoleCode(AppConst.ROLE_CODE);
				sysGroupRole.setValidStatus(AppConst.VALID_STATUS_01);
				this.sysGroupRoleService.insert(sysGroupRole);
			}
			map.put("msg", 1);
			logger.info("修改岗位权限信息成功...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("修改岗位权限信息失败...");
		}
		return map;
    }
	
	
}
