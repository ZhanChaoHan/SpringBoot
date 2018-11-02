package com.sinosoft.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.normal.po.SysArea;
import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.po.SysCodeKey;
import com.sinosoft.normal.po.SysCompany;
import com.sinosoft.normal.po.SysUser;
import com.sinosoft.normal.po.SysUserGroup;
import com.sinosoft.normal.po.SysUserGroupKey;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.vo.SysCompanyVo;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.SysAreaService;
import com.sinosoft.service.SysCodeService;
import com.sinosoft.service.SysCompanyService;
import com.sinosoft.service.SysUserGroupService;
import com.sinosoft.service.SysUserService;

/**
 * @author caoLong 机构管理 2017年10月24日
 */
@Controller 
@RequestMapping(value = "/sysCompany")
public class SysCompanyController {
	private static final Log logger = LogFactory.getLog(SysCompanyController.class);

	@Autowired // 用户业务类
	private SysCompanyService sysCompanyService;
	@Autowired // 配置表
	private SysCodeService sysCodeService;
	@Autowired // 配置表
	private SysAreaService sysAreaService;
	@Autowired // 用户业务类
	private SysUserService sysUserService;
	@Autowired // 用户岗位权限的业务类
	private SysUserGroupService sysUserGroupService;

	// 跳转到机构查询的界面
	@RequestMapping(value = "/querySysCompany.do")
	public String goUpdateSysUserPassWord(HttpServletRequest request, HttpServletResponse response) {
		// 查询下拉选地区
		List<SysArea> sysAreaList = sysAreaService.getSysAreaList();
		// 查询下拉选机构级别
		List<SysCode> comLevelList = sysCodeService.getSysCodeList(AppConst.COM_LEVEL);
		// 查询下拉选机构类型
		List<SysCode> comTypeList = sysCodeService.getSysCodeList(AppConst.COM_TYPE);
		// 查询上级机构
		List<SysCompany> supComCodelist = sysCompanyService.selectSupSysCompany(AppConst.COM_TYPE_00);
		request.setAttribute("sysAreaList", sysAreaList);
		request.setAttribute("comLevelList", comLevelList);
		request.setAttribute("comTypeList", comTypeList);
		request.setAttribute("supComCodelist", supComCodelist);
		logger.info("正在跳转至机构查询页...");
		return "/authority/syscompany/querySysCompany";
	}

	// 跳转到查询结果页面
	@RequestMapping(value = "/querySysCompanyResult.do")
	@ResponseBody
	public Map<String, Object> querySysCompanyResult(@RequestParam("comCode") String comCode,
			@RequestParam("comCname") String comCname, @RequestParam("areaCode") String areaCode,
			@RequestParam("comAddress") String comAddress) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 条件查询
			List<SysCompanyVo> sysCompanyVoList = sysCompanyService.querySysCompany(comCode, comCname, areaCode,
					comAddress, AppConst.SUPER_COM_CODE, AppConst.VALID_STATUS_01);
			for (SysCompanyVo com : sysCompanyVoList) {
				// 翻译级别
				SysCodeKey keyType = new SysCodeKey();
				keyType.setCodeCode(com.getComLevel());
				keyType.setCodeType(AppConst.COM_LEVEL);
				SysCode comTypech = sysCodeService.selectByPrimaryKey(keyType);
				com.setComLevel(comTypech.getCodeCname());
				// 翻译上级机构
				SysCompany supComCode = sysCompanyService.sysCompanyId(com.getSupComCode());
				if (supComCode != null) {
					com.setSupComCode(supComCode.getComCname());
				}

			}
			map.put("sysCompanyVoList", sysCompanyVoList);
			logger.info("跳转机构条件查询结果页面...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("机构条件查询页面异常...");
		}
		return map;
	}

	// 跳转到机构添加的界面
	@RequestMapping(value = "/editSysCompany.do")
	public String editSysCompany(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String currentUser = userSession.getUserCode();
		if (currentUser.equals(AppConst.ADMIN)) {
			// 查询下拉选机构类型
			List<SysCode> comTypeList = sysCodeService.getSysCodeList(AppConst.COM_TYPE);
			request.setAttribute("comTypeList", comTypeList);
			// 查询上级机构
			List<SysCompany> supComCodelist = sysCompanyService.selectSupSysCompany(null);
			request.setAttribute("supComCodelist", supComCodelist);
		} else {
			// 获取当前用的机构类型
			SysUser user = sysUserService.selectByPrimaryKey(currentUser, AppConst.VALID_STATUS_01);
			SysCompany com = sysCompanyService.selectByPrimaryKey(user.getComCode(), AppConst.VALID_STATUS_01);
			// 查询下拉选机构类型
			List<SysCode> comTypeList = sysCodeService.getCodeTypeList(AppConst.COM_TYPE, com.getComType());
			List<SysCompany> supComCodelist = sysCompanyService.selectSupSysCompany(comTypeList.get(0).getCodeCode());
			request.setAttribute("comTypeList", comTypeList);
			request.setAttribute("supComCodelist", supComCodelist);
		}
		// 查询下拉选地区
		List<SysArea> sysAreaList = sysAreaService.getSysAreaList();
		// 查询下拉选机构级别
		List<SysCode> comLevelList = sysCodeService.getSysCodeList(AppConst.COM_LEVEL);
		request.setAttribute("sysAreaList", sysAreaList);
		request.setAttribute("comLevelList", comLevelList);
		logger.info("正在跳转至机构查询页...");
		return "/authority/syscompany/editSysCompany";
	}

	// 跳转用户添加页
	@RequestMapping(value = "/addSysCompany.do")
	@ResponseBody
	public Map<String, Object> addSysUserReturn(@RequestParam("comCode") String comCode,
			@RequestParam("comCname") String comCname, @RequestParam("areaCode") String areaCode,
			@RequestParam("comAddress") String comAddress, @RequestParam("comLevel") String comLevel,
			@RequestParam("comPhone") String comPhone, @RequestParam("supComCode") String supComCode,
			@RequestParam("comType") String comType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String createUserCode = userSession.getUserCode();
		try {
			// 实体类
			SysCompany sysCompany = new SysCompany();
			sysCompany.setComCode(comCode);
			sysCompany.setComCname(comCname);
			sysCompany.setAreaCode(areaCode);
			sysCompany.setComLevel(comLevel);
			sysCompany.setComAddress(comAddress);
			sysCompany.setComphone(comPhone);
			sysCompany.setComType(comType);
			sysCompany.setSupComCode(supComCode);
			sysCompany.setCreateTime(new Date());
			sysCompany.setCreateUserCode(createUserCode);
			sysCompany.setModifyTime(new Date());
			sysCompany.setModifyUserCode(createUserCode);
			sysCompany.setValidStatus(AppConst.VALID_STATUS_01);
			sysCompany.setHasSubCom(AppConst.HASSUBCOMCODE_00);
			sysCompanyService.insert(sysCompany);
			// 查询是否有上级机构，如果有上级机构 HasSubCom字段值存01
			SysCompany supCom = sysCompanyService.getSysCompanyById(supComCode);
			if (supCom != null) {
				SysCompany upsysCompany = new SysCompany();
				upsysCompany.setComCode(supCom.getComCode());
				upsysCompany.setComCname(supCom.getComCname());
				upsysCompany.setAreaCode(supCom.getAreaCode());
				upsysCompany.setComLevel(supCom.getComLevel());
				upsysCompany.setComAddress(supCom.getComAddress());
				upsysCompany.setComphone(supCom.getComphone());
				upsysCompany.setComType(supCom.getComType());
				upsysCompany.setSupComCode(supCom.getSupComCode());
				upsysCompany.setCreateTime(new Date());
				upsysCompany.setCreateUserCode(createUserCode);
				upsysCompany.setModifyTime(new Date());
				upsysCompany.setModifyUserCode(createUserCode);
				upsysCompany.setValidStatus(AppConst.VALID_STATUS_01);
				upsysCompany.setHasSubCom(AppConst.HASSUBCOMCODE_01);
				sysCompanyService.updateByPrimaryKey(upsysCompany);
			}

			logger.info("跳转机构添加成功...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("用户添加异常...");
		}
		return map;
	}

	// 机构代码的唯一校验
	@RequestMapping(value = "/checkSysCompany.do")
	@ResponseBody
	public Map<String, Object> checkSysCompany(@RequestParam("comCode") String comCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 校验机构代码是否在数据库中存在
			SysCompany sysCompany = sysCompanyService.selectByPrimaryKey(comCode, AppConst.VALID_STATUS_01);
			if (sysCompany == null) {
				logger.info("数据库中不存在相同的机构代码，可以保存到数据库");
				// Ajax 返回 页面的 字符串 （1：没有重复数据，0：有重复数据）
				map.put("msg", 1);
			}
			// 有对应数据
			else {
				logger.info("数据库中已存在相同的机构代码数据");
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

	// 机构管理的删除方法
	@RequestMapping(value = "/deleteSysCompany.do")
	@ResponseBody
	public Map<String, Object> deleteSysCompany(@RequestParam("comCode") String comCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取该机构信息
			SysCompany sysCom = sysCompanyService.selectByPrimaryKey(comCode, AppConst.VALID_STATUS_01);
			// 获取机构下的用户
			List<SysUser> user = sysUserService.querySysUserDelete(sysCom.getComCode(), AppConst.VALID_STATUS_01);
			// 判读用户是否存在
			if (user != null && user.size() > 0) {
				for (SysUser uu : user) {
					// 获取用户的岗位信息
					List<SysUserGroup> userGroup = sysUserGroupService.getSysUserGroupListByUserCode(uu.getUserCode(),
							AppConst.VALID_STATUS_01);
					// 判读是否有岗位信息
					if (userGroup != null && userGroup.size() > 0) {
						for (SysUserGroup group : userGroup) {
							SysUserGroupKey key = new SysUserGroupKey();
							key.setGroupCode(group.getGroupCode());
							key.setUserCode(group.getUserCode());
							// 删除岗位信息
							sysUserGroupService.deleteByPrimaryKey(key);
						}
					}
					// 删除用户信息
					sysUserService.deleteByPrimaryKey(uu.getUserCode());
				}
			}
			// 删除机构信息
			Integer number = sysCompanyService.deleteByPrimaryKey(comCode);
			map.put("number", number);
			logger.info("跳转机构条件查询结果页面...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("机构条件查询页面异常...");
		}
		return map;
	}

	// 机构管理删除时校验是否有下级机构
	@RequestMapping(value = "/deleteCheckSysCompany.do")
	@ResponseBody
	public Map<String, Object> deleteCheckSysCompany(@RequestParam("comCode") String comCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String reFlag = null;
			// 判断是否有下级机构
			List<SysCompany> sysCompany = sysCompanyService.checkSysCompany(comCode);
			if (sysCompany.size() > 0) {
				reFlag = "01";
			}
			map.put("reFlag", reFlag);
			logger.info("查询用户代码信息...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("用户代码校验异常...");
		}
		return map;
	}

	// 机构管理修改回显方法
	@RequestMapping(value = "/modifySysCompany.do")
	public String modifySysCompany(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		try {
			// 获取页面的主键
			String comCodeVal = request.getParameter("comCode");
			SysCompany com = sysCompanyService.selectByPrimaryKey(comCodeVal, AppConst.VALID_STATUS_01);
			// 查询下拉选地区
			List<SysArea> sysAreaList = sysAreaService.getSysAreaList();
			// 查询下拉选机构级别
			List<SysCode> comLevelList = sysCodeService.getSysCodeList(AppConst.COM_LEVEL);
			// 查询下拉选机构类型
			List<SysCode> comTypeList = sysCodeService.getSysCodeList(AppConst.COM_TYPE);
			// 查询上级机构
			List<SysCompany> supComCodelist = sysCompanyService.selectSupSysCompany(AppConst.COM_TYPE_00);
			request.setAttribute("sysAreaList", sysAreaList);
			request.setAttribute("comTypeList", comTypeList);
			request.setAttribute("supComCodelist", supComCodelist);
			// 翻译级别、上级机构、地区
			List<SysCode> fanyiComLevel = sysCodeService.getCodeTypeList(AppConst.COM_LEVEL, com.getComLevel());
			request.setAttribute("fanyiComLevel", fanyiComLevel);
			// 查询下拉选地区
			List<SysArea> fanyiArea = sysAreaService.selectList(com.getAreaCode());
			request.setAttribute("fanyiArea", fanyiArea);
			List<SysCompany> fanyiSup = sysCompanyService.findSysCompanyList(com.getSupComCode(), com.getSupComCode());
			request.setAttribute("fanyiSup", fanyiSup);
			// 查询修改对象
			if (com.getSupComCode() == null) {
				Exception e = new Exception();
				e.printStackTrace();
				logger.info("修改操作异常...");
				return "/login/error";
			} else {
				UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
				// 用户代码
				String ComCode = userSession.getComCode();
				// 用areaCode_isModify字段来判断是否可以修改“地区代码”字段,00-可修改；01-不可修改
				boolean mark = recurAthwartSysArea(com.getAreaCode(), userSession.getAreaCode(), ComCode, false);
				if (mark == true) {
					// 修改“地区代码”字段,00-可修改；01-不可修改
					String areaCode_isModify = AppConst.ISMODIFY_00;
					request.setAttribute("areaCode_isModify", areaCode_isModify);
				} else {
					// 修改“地区代码”字段,00-可修改；01-不可修改
					String areaCode_isModify = AppConst.ISMODIFY_01;
					request.setAttribute("areaCode_isModify", areaCode_isModify);
				}
				// 用comLevel_isModify字段来判断是否可以修改“机构级别”字段,00-可修改；01-不可修改
				List<SysCode> comLevel_list = this.getUserComLevels(false, userSession.getComCode(),
						userSession.getComLevel());
				if (comLevel_list == null) {
					// 机构级别是否修改
					String comLevel_isModify = AppConst.ISMODIFY_01;
					request.setAttribute("comLevel_isModify", comLevel_isModify);
				} else {
					String comLevel_isModify = AppConst.ISMODIFY_01;
					request.setAttribute("comLevel_isModify", comLevel_isModify);
					for (SysCode comLivel : comLevel_list) {
						if (comLivel.getCodeCode().equals(String.valueOf(com.getComLevel()))) {
							String comLevel_isModify1 = AppConst.ISMODIFY_00;
							request.setAttribute("comLevel_isModify", comLevel_isModify1);
							// 显示的机构级别列表
							comLevelList = this.getUserComLevels(false, userSession.getComCode(),
									userSession.getComLevel());
							request.setAttribute("comLevelList", comLevelList);
						}
					}

				}
				// 用supComCode_isModify字段来判断是否可以修改“上级机构代码”字段,00-可修改；01-不可修改
				boolean flag = recurAthwartSysCompany(com.getSupComCode(), userSession.getComCode(), true);
				if (flag == true) {
					// 上级机构是否修改
					String supComCode_isModify = AppConst.ISMODIFY_00;
					request.setAttribute("supComCode_isModify", supComCode_isModify);
				} else {
					// 上级机构是否修改
					String supComCode_isModify = AppConst.ISMODIFY_01;
					request.setAttribute("supComCode_isModify", supComCode_isModify);
				}
				if (com != null) {
					// 修改
					SysCompanyVo comVo = new SysCompanyVo();
					comVo.setComCode(com.getComCode());
					comVo.setComCname(com.getComCname());
					comVo.setAreaCode(com.getAreaCode());
					comVo.setAreaCname(com.getAreaCode());
					comVo.setComAddress(com.getComAddress());
					comVo.setComLevel(com.getComLevel());
					comVo.setComphone(com.getComphone());
					comVo.setSupComCode(com.getSupComCode());
					// Integer number= sysGroupService.updateByPrimaryKey(queryUpdate);
					request.setAttribute("comVo", comVo);
				}
				logger.info("进行修改操作...");
				return "/authority/syscompany/modifySysCompany";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("修改操作异常...");
			return "/login/error";
		}
	}

	// 机构管理修改保存方法
	@RequestMapping(value = "/updateSysCompany.do")
	@ResponseBody
	public Map<String, Object> updateSysUser(@RequestParam("comCode") String comCode,
			@RequestParam("comCname") String comCname, @RequestParam("areaCode") String areaCode,
			@RequestParam("comAddress") String comAddress, @RequestParam("comLevel") String comLevel,
			@RequestParam("comPhone") String comPhone, @RequestParam("supComCode") String supComCode,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 用户代码
		String modifyUserCode = userSession.getUserCode();
		try {
			// 获取机构信息
			SysCompany sysCompany = sysCompanyService.selectByPrimaryKey(comCode, AppConst.VALID_STATUS_01);
			if (sysCompany != null) {
				SysCompany com = new SysCompany();
				com.setComCode(comCode);
				com.setComCname(comCname);
				com.setAreaCode(areaCode);
				com.setComAddress(comAddress);
				com.setComLevel(comLevel);
				com.setSupComCode(supComCode);
				com.setComType(AppConst.COM_TYPE_00);
				com.setComphone(comPhone);
				com.setHasSubCom(sysCompany.getHasSubCom());
				com.setValidStatus(AppConst.VALID_STATUS_01);
				com.setCreateTime(sysCompany.getCreateTime());
				com.setCreateUserCode(sysCompany.getCreateUserCode());
				com.setModifyUserCode(modifyUserCode);
				;
				com.setModifyTime(new Date());
				sysCompanyService.updateByPrimaryKey(com);
			}
			logger.info("跳转用户条件查询结果页面...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("用户条件查询页面异常...");
		}
		return map;
	}

	/**
	 * 查询地区代码为areaCodeDown的地区是否在地区代码为areaCodeUp的地区的控制中
	 * 
	 * @param areaCodeDown
	 *            - 找人的下级地区代码（查这个代码的上级代码、上上级代码。。。中是否包含下面的参数areaCodeUp）
	 * @param areaCodeUp
	 *            - 被找的上级地区代码（登陆用户的地区代码）
	 * @param include
	 *            - 是否包含本地区 （true：包含；false：不包含）
	 * @return 地区代码为areaCodeUp的地区的可控地区中 包含 地区代码为areaCodeDown的地区返回“true”否则返回“false”
	 */
	public boolean recurAthwartSysArea(String areaCodeDown, String areaCodeUp, String ComCode, boolean include) {
		// 地区代码存在的情况
		if (StringUtils.isNotEmpty(areaCodeDown) && StringUtils.isNotEmpty(areaCodeUp)) {
			// 超级用户被找（登陆的是超级用户）
			if (StringUtils.isNotEmpty(ComCode) && AppConst.SUPER_COM_CODE.equals(ComCode)) {
				return true;
			}
			// 地区代码相同
			if (areaCodeDown.equals(areaCodeUp)) {
				if (include) {
					return true;
				} else {
					return false;
				}
			}
			// 取得地区对象，目的是要他的上级地区
			SysArea sysAreaDown = sysAreaService.selectByPrimaryKey(areaCodeDown);
			// 有对象 有上级的情况
			if (null != sysAreaDown && StringUtils.isNotEmpty(sysAreaDown.getSupAreaCode())) {
				// 和上级相同
				if (sysAreaDown.getSupAreaCode().equals(areaCodeUp)) {
					// 释放无用资源
					sysAreaDown = null;
					return true;
				}
				// 用递归取得comCodeDown 对应的地区的上级地区集合
				List<SysArea> listSysArea = new ArrayList<SysArea>();
				// 递归找上级的集合
				this.recursiveAthwartSysAreas(listSysArea, sysAreaDown.getSupAreaCode());
				// 本找的地区对象，目的是对比
				SysArea sysAreaUp = sysAreaService.selectByPrimaryKey(areaCodeUp);
				// areaCodeDown 对应的地区的上级地区集合中包括 areaCodeUp对应的地区对象
				if (null != listSysArea && listSysArea.contains(sysAreaUp)) {
					// 释放无用资源
					listSysArea = null;
					sysAreaDown = null;
					sysAreaUp = null;
					return true;
				}
				// 释放无用资源
				sysAreaUp = null;
				listSysArea = null;
			}
			// 释放无用资源
			sysAreaDown = null;
		}
		return false;
	}

	/**
	 * 递归查上级地区方法
	 * 
	 * @param listSysArea
	 *            - 地区对象集合（传址操作，所有要查出来的地区）
	 * @param areaCode-
	 *            要查询上级地区的地区代码
	 */
	private void recursiveAthwartSysAreas(List<SysArea> listSysArea, String areaCode) {
		// 查询出 areaCode的地区
		SysArea sysArea = sysAreaService.selectByPrimaryKey(areaCode);
		// areaCode的地区存在的情况
		if (null != sysArea) {
			// 添加到集合中
			listSysArea.add(sysArea);
			// 不是最顶层的地区的情况（有上级地区）
			if (!AppConst.NOT_SUP.equals(sysArea.getSupAreaCode())) {
				// 继续递归查询上级
				this.recursiveAthwartSysAreas(listSysArea, sysArea.getSupAreaCode());
			}
		}

		// 释放无用资源
		sysArea = null;
	}

	/**
	 * 功能：获取当前用户能管控的机构级别
	 * 
	 * @param include
	 *            - 查询到的机构级别列表是否包含自己本身的级别
	 * @author 郝彬彬
	 */
	public List<SysCode> getUserComLevels(boolean include, String comCode, String SessionComLevel) {
		/*
		 * 组织查询的SQL： 1、超级管理员可查询到SysCode表中所有的ComLevel
		 * 2、普通用户需根据include参数，查询到自己本身和下级的ComLevel或自己本身的下级ComLevel
		 */
		if (!comCode.equals(AppConst.SUPER_COM_CODE)) {
			if (include) {
				List<SysCode> sysCodes = sysCodeService.muchComLevelList(AppConst.COM_LEVEL, SessionComLevel);
				return sysCodes;
			} else {
				List<SysCode> sysCodes = sysCodeService.thanComLevelList(AppConst.COM_LEVEL, SessionComLevel);
				return sysCodes;
			}
		} else {
			// 查询下拉选机构类型
			List<SysCode> sysCodes = sysCodeService.getSysCodeList(AppConst.COM_LEVEL);

			return sysCodes;
		}
	}

	/**
	 * 查询机构代码为comCodeDown的机构是否在机构代码为comCodeUp的机构的控制中
	 * 
	 * @param comCodeDown
	 *            - 找人的下级机构代码（查这个代码的上级代码、上上级代码。。。中是否包含下面的参数comCodeUp）
	 * @param comCodeUp
	 *            - 被找的上级机构代码（登陆用户）
	 * @param include
	 *            - 是否包含本机构 （true：包含；false：不包含）
	 * @return 机构代码为comCodeUp的机构的可控机构中 包含 机构代码为comCodeDown的机构返回“true”否则返回“false”
	 */
	public boolean recurAthwartSysCompany(String comCodeDown, String comCodeUp, boolean include) {
		// 超级用户被找（登陆的是超级用户）
		if (AppConst.SUPER_COM_CODE.equals(comCodeUp)) {
			return true;
		}
		// 找人机构是超级用户
		else if (AppConst.SUPER_COM_CODE.equals(comCodeDown)) {
			return false;
		}
		// 机构代码存在的情况
		else if (StringUtils.isNotEmpty(comCodeDown) && StringUtils.isNotEmpty(comCodeUp)) {
			// 机构代码相同
			if (comCodeDown.equals(comCodeUp)) {
				// 包含本机构
				if (include) {
					return true;
				}
				// 不包含
				else {
					return false;
				}
			}
			// 临时的list(查一次库取得comCodeDown和comCodeUp相关的两个对象)
			List<SysCompany> list = this.sysCompanyService.findSysCompanyList(comCodeUp, comCodeDown);
			// 查出两条数据时，才是合理的(comCodeDown和comCodeUp相关的两个对象)
			if (null != list && 2 == list.size()) {
				SysCompany sysCompanyUp = null;// 被找的 上级机构对象
				SysCompany sysCompanyDown = null;// 找人的下级机构对象
				// 取出这两个对象
				for (SysCompany sysCompany : list) {
					// 取出 被找的 上级机构对象
					if (StringUtils.isNotEmpty(comCodeUp) && comCodeUp.equals(sysCompany.getComCode())) {
						sysCompanyUp = sysCompany;
					}
					// 取出 找人的下级机构对象
					else if (StringUtils.isNotEmpty(comCodeDown) && comCodeDown.equals(sysCompany.getComCode())) {
						sysCompanyDown = sysCompany;
					}
				}
				// 两个 对象都被查到的情况
				if (null != sysCompanyUp && null != sysCompanyDown) {
					// 机构类型相同的情况 按机构上下级往上查（如：保监查保监）
					if (sysCompanyDown.getComType().equals(sysCompanyUp.getComType())) {
						// 用递归取得comCodeDown 的上级机构集合
						List<SysCompany> listSysCompany = new ArrayList<SysCompany>();
						this.recursiveAthwartSysCompanys(listSysCompany, sysCompanyDown.getSupComCode());
						// comCodeDown 的上级机构集合中包括 comCodeUp的对象
						if (null != listSysCompany && listSysCompany.contains(sysCompanyUp)) {
							// 释放无用资源
							sysCompanyUp = null;
							sysCompanyDown = null;
							listSysCompany = null;
							return true;
						}
						// 释放无用资源
						listSysCompany = null;
					}
					// 机构类型不相同的情况 按所在地区上下级往上查（如：保监查保险公司）
					else {
						// 地区代码相同
						if (sysCompanyDown.getAreaCode().equals(sysCompanyUp.getAreaCode())) {
							// 释放无用资源
							sysCompanyUp = null;
							sysCompanyDown = null;
							return true;
						}
						// 用递归取得comCodeDown 对应的地区的上级地区集合
						List<SysArea> listSysArea = new ArrayList<SysArea>();
						this.recursiveAthwartSysAreas(listSysArea, sysCompanyDown.getAreaCode());
						// comCodeDown 对应的地区的上级地区集合中包括 comCodeUp对应的地区对象

						List<SysArea> area = sysAreaService.selectList(sysCompanyUp.getAreaCode());

						if (null != listSysArea && listSysArea.contains(area)) {
							// 释放无用资源
							listSysArea = null;
							sysCompanyUp = null;
							sysCompanyDown = null;
							return true;
						}
						// 释放无用资源
						listSysArea = null;
					}
				}
				// 释放无用资源
				sysCompanyUp = null;
				sysCompanyDown = null;
			}
		}

		return false;
	}

	/**
	 * 递归查上级机构方法
	 * 
	 * @param listSysCompany
	 *            - 机构对象集合（传址操作，所有要查出来的机构）
	 * @param comCode
	 *            - 要查询上级机构的机构代码
	 */
	private void recursiveAthwartSysCompanys(List<SysCompany> listSysCompany, String comCode) {
		// 查询出 comCode的下级机构
		SysCompany sysCompany = this.sysCompanyService.getSysCompanyById(comCode);
		// comCode机构存在的情况
		if (null != sysCompany) {
			// 添加到集合中
			listSysCompany.add(sysCompany);
			// // 不是最顶层的机构的情况（有上级机构）
			// if (!AppConst.NOT_SUP.equals(sysCompany.getSupComCode())) {
			// 上级机构不是超级机构
			if (!AppConst.SUPER_COM_CODE.equals(sysCompany.getSupComCode())) {
				// 继续递归查询上级
				this.recursiveAthwartSysCompanys(listSysCompany, sysCompany.getSupComCode());
			}
		}

		// 释放无用资源
		sysCompany = null;
	}

}
