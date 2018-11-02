package com.sinosoft.controller;

import java.util.HashMap;
//日志的相关业务类
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.vo.SysAreaVo;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.SysAreaService;
import com.sinosoft.service.SysCodeService;

@Controller
@RequestMapping(value = "/sysArea")
public class SysAreaController {

	private static final Log logger = LogFactory.getLog(LoginController.class);

	@Autowired // 自动注入日志业务类
	private SysAreaService sysAreaService;
	@Autowired // 自动注入代码业务类
	private SysCodeService sysCodeService;

	/**
	 * 跳转到地区管理页面
	 * 
	 * @return url
	 */
	@RequestMapping(value = "goSysAreaManager.do", method = RequestMethod.GET)
	public String goSysAreaManager(HttpSession session, HttpServletRequest request) {
		try {
			logger.info("跳转至地区管理页面...");
			UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			if (userSession.getFuncPowers().get(AppConst.ROLE_CODE_2_3) == null) {
				logger.info("当前用户没有权限跳转至日志查询页...");
				request.setAttribute("error", "当前用户没有权限跳转至地区管理页面!");
				return "/login/error";
			}
			return "/setting/sysArea/sysAreaManager";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转至日志页面发送异常...");
			return "/login/error";
		}
	}

	/*
	 * 过滤查询地区列表
	 */
	@RequestMapping(value = "/querySysAreaListByFilter.do")
	public ModelAndView querySysAreaListByFilter(Integer pageNum, String areaCode, String areaCname,
			String supAreaCName, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			logger.info("正在使用查询条件查询地区列表...");
			// 校验权限
			UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			if (userSession.getFuncPowers().get(AppConst.ROLE_CODE_2_3_2) == null) {
				logger.info("当前用户没有权限查询地区列表...");
				mv.addObject("error", "当前用户没有权限查询地区列表...");
				mv.setViewName("/login/error");
			}
			// 铺垫分页
			if (pageNum == null || pageNum.equals("")) {
				pageNum = 1;
			}
			// 前台是刷新式分页，所以不再设置页面数，不支持，pageSize读取枚举
			// 分页
			PageHelper.startPage(pageNum, AppConst.PAGE_SIZE);
			// 加入查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode.trim());
			map.put("areaCname", areaCname.trim());
			map.put("superName", supAreaCName.trim());
			map.put("validStatus", AppConst.VALID_STATUS_01);

			List<SysAreaVo> sysAreaList = sysAreaService.querySysAreaListByFilter(map);

			// 查询机构类型维度
			List<SysCode> roleTypeList = sysCodeService.getSysCodeList(AppConst.HASSUBAREA);
			// 加入map中
			Map<String, String> tempMap = new HashMap<String, String>();
			for (SysCode s : roleTypeList) {
				tempMap.put(s.getCodeCode(), s.getCodeCname());
			}

			for (int i = 0; i < sysAreaList.size(); i++) {
				sysAreaList.get(i).setHasSubArea(tempMap.get(sysAreaList.get(i).getHasSubArea()));
			}

			PageInfo<SysAreaVo> page = new PageInfo<SysAreaVo>(sysAreaList);
			mv.addObject("page", page);
			mv.addObject("areaCode", areaCode);
			mv.addObject("areaCname", areaCname);
			mv.addObject("supAreaCName", supAreaCName);
			mv.setViewName("/setting/sysArea/sysAreaManager");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询地区列表出现异常...");
			mv.addObject("error", e.getMessage());
			mv.setViewName("/login/error");
		}
		return mv;
	}

}
