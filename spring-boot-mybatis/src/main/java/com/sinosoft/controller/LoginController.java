package com.sinosoft.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sinosoft.normal.po.SysCompany;
import com.sinosoft.normal.po.SysRole;
import com.sinosoft.normal.po.SysUser;
import com.sinosoft.normal.util.AppConst;
import com.sinosoft.normal.util.AppUtil;
import com.sinosoft.normal.util.LoggerUtil;
import com.sinosoft.normal.vo.UserSession;
import com.sinosoft.service.OperatorLogsService;
import com.sinosoft.service.SysCompanyService;
import com.sinosoft.service.SysRoleService;
import com.sinosoft.service.SysUserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Log logger = LogFactory.getLog(LoginController.class);

	@Autowired // 用户业务类
	private SysUserService sysUserService;

	@Autowired // 公司业务类
	private SysCompanyService sysCompanyService;

	@Autowired // 权限功能的业务类
	private SysRoleService sysRoleService;

	@Autowired // 日志的业务类
	private OperatorLogsService operatorLogsService;

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		try {
			logger.info("初始化登陆页面...");
			return "/login/login";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("初始化登陆页失败...");
			return "/login/error";
		}
	}

	// 校验用户名，密码，验证码是否正确
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String getJson(String usercode, String password, String randomCode, HttpSession session,
			RedirectAttributes attr) {
		try {
			logger.info("正在校验用户信息...");
			// 登录没有超时
			String rand = (String) session.getAttribute("rand");
			session.removeAttribute("rand");
			// 如果验证码校验通过
			if (randomCode.equalsIgnoreCase(rand)) {
				// 验证码校验通过就开始校验用户名和密码
				SysUser user = sysUserService.selectByPrimaryKey(usercode, AppConst.VALID_STATUS_01);

				if (user != null && user.getUserCode().equals(usercode)) {
					// 用户名校验无误，校验密码
					if (user.getPassWord().equals(AppUtil.md5s(password))) {
						// 登录正常，开始加载用户权限
						logger.info("登录成功，开始加载用户权限...");
						// 通过用户查出用户所在的机构
						SysCompany company = sysCompanyService.selectByPrimaryKey(user.getComCode(),
								AppConst.VALID_STATUS_01);
						// - 查找用户岗位集合

						// - 查找用户所有功能集合
						Map<String, Object> tempMap = new HashMap<String, Object>();
						tempMap.put("userCode", usercode);
						tempMap.put("validStatus1", AppConst.VALID_STATUS_01);
						tempMap.put("validStatus2", AppConst.VALID_STATUS_01);
						tempMap.put("validStatus3", AppConst.VALID_STATUS_01);
						// 去重后查出对应的权限集合
						List<SysRole> sysRoleList = sysRoleService.findSysRoleListByUserCode(tempMap);
						// 查找是否有用户登录的权限
						boolean flag = false;
						for (SysRole sysRole : sysRoleList) {
							if (AppConst.ROLE_CODE_0.equals(sysRole.getRoleCode())) {
								flag = true;
							}
						}
						// 如果没有登录权限,那么就不能登录!
						if (!flag) {
							logger.info("用户没有登录权限!");
							attr.addFlashAttribute("msg", "用户没有登录权限!");
							return "redirect:index.do";
						}

						// - 查找用户所有功能集合
						Map<String, SysRole> funcPowersMap = new TreeMap<String, SysRole>();
						for (SysRole role : sysRoleList) {
							if (role.getRoleType().equals(AppConst.ROLE_TYPE_01)) {
								funcPowersMap.put(role.getRoleCode(), role);
							}
						}

						// - 组织UserSession
						UserSession userSession = new UserSession();
						// 塞入用户编码
						userSession.setUserCode(user.getUserCode());
						// 用户名
						userSession.setUserName(user.getUserName());
						// 公司编码
						userSession.setComCode(user.getComCode());
						// 公司名称
						userSession.setComCname(company.getComCname());
						// 公司地区编码
						userSession.setAreaCode(company.getAreaCode());
						// 公司类型 '00 全国 01 省级 02 市级 03 县级 ',
						userSession.setComType(company.getComType());
						// 公司级别( ' - 超级机构 00 保监局 01 保险协会 02 经侦 03 中国保信 04 保险公司 ')
						userSession.setComLevel(company.getComLevel());
						// 放入用户的权限合集
						userSession.setFuncPowers(funcPowersMap);

						// 放入权限合集
						// 用户信息放入session
						session.setAttribute(AppConst.USER_SESSION_ID, userSession);
						// 打印日志
						logger.info(LoggerUtil.getInfoMsg("登录操作", session));
						return "redirect:frame.do";
					} else {
						logger.info("密码不正确!");
						attr.addFlashAttribute("msg", "密码不正确!");
						return "redirect:index.do";
					}
				} else {
					logger.info("用户名不正确!");
					attr.addFlashAttribute("msg", "用户名不正确!");
					return "redirect:index.do";
				}
			} else {
				logger.info("验证码不正确!");
				attr.addFlashAttribute("msg", "验证码不正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统开小差了,请稍后重试!");
			attr.addFlashAttribute("msg", "系统开小差了,请稍后重试!");
		}
		return "redirect:index.do";
	}

	/**
	 * 登录成功,进入系统
	 * 
	 * @return
	 */
	@RequestMapping(value = "/frame.do", method = RequestMethod.GET)
	public String loginSuccess() {
		try {
			logger.info("成功登入,跳转至系统主页...");
			return "/login/frame";
		} catch (Exception e) {
			e.printStackTrace();
			return "/login/error";
		}
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginOut.do", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		try {
			logger.info("用户退出登录...");
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:index.do";
	}

	/**
	 * 展示登录的主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showMainPage.do", method = RequestMethod.GET)
	public String showMainPage() {
		try {
			logger.info("展示后台首页...");
			return "/login/main";
		} catch (Exception e) {
			e.printStackTrace();
			return "/login/error";
		}

	}

	// 跳转到修改密码的界面
	@RequestMapping(value = "/goUpdatePassWord.do")
	public String goUpdateSysUserPassWord() {
		logger.info("正在跳转至密码修改页...");
		return "/login/changPassWord";
	}

	// 修改密码 校验当前的密码是否正确
	@RequestMapping(value = "/validatePassWord.do")
	@ResponseBody
	public Object validatePassWord(String password, HttpSession session) {
		String msg = null;
		try {
			logger.info("正在校验当前用户密码...");
			if (session == null) {
				msg = "用户退出登录,请重新登录!";
			}
			// 获取当当前用户
			SysUser user = sysUserService.selectByPrimaryKey(
					((UserSession) session.getAttribute(AppConst.USER_SESSION_ID)).getUserCode(),
					AppConst.VALID_STATUS_01);
			if (user != null) {
				if (AppUtil.md5s(password).equals(user.getPassWord())) {
					logger.info("用户密码校验成功...");
					msg = "success";
				} else {
					logger.info("用户输入密码和原密码不匹配...");
					msg = "not match";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("校验用户密码出现问题...");
			msg = "error";
		}
		return msg;
	}

	// 修改密码 修改当前用户的密码
	@RequestMapping(value = "/updateUserPassWord.do")
	@ResponseBody
	public Object updateUserPassWord(String password, HttpSession session) {
		String msg = null;
		try {
			UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
			SysUser user = sysUserService.selectByPrimaryKey(userSession.getUserCode(), AppConst.VALID_STATUS_01);
			user.setPassWord(AppUtil.md5s(password));
			int flag = sysUserService.updateByPrimaryKeySelective(user);
			if (flag > 0) {
				logger.info(LoggerUtil.getInfoMsg("修改密码成功", session));
				// 存入操作日志
				if (operatorLogsService.insert(AppUtil.setOneOperatorlogs(userSession.getUserCode(), new Date(),
						AppConst.ROLE_CODE_0, "修改密码", userSession.getAreaCode(), userSession.getComCode(),
						LoggerUtil.getInfoMsg("修改密码成功", session),
						userSession.getFuncPowers().get(AppConst.ROLE_CODE_0).getRoleType())) > 0) {
					logger.info("存入操作轨迹成功...");
					msg = "success";
				}
			} else {
				logger.info(LoggerUtil.getInfoMsg("修改密码失败", session));
				msg = "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				logger.info(LoggerUtil.getInfoMsg("修改密码失败", session));
				msg = "error";
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return msg;
	}

	@RequestMapping(value = "/getVerificationCode.do", method = RequestMethod.GET)
	@ResponseBody
	public void getVerificationCode(HttpServletResponse response, HttpSession session) {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 设置图片的长宽
		int width = 60, height = 20;
		// 产生图片数字数组
		// 数字去掉0和1，字母去掉O和I已经所有的小写字母
		String[] code = { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L",
				"M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		// 创建内存图像
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 创建随机类的实例
		Random random = new Random();
		// 设定图像背景色(因为是做背景，所以偏淡)
		g.setColor(getRandColor(random, 200, 250));
		g.fillRect(0, 0, width, height);

		// 随机产生100条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(random, 160, 200));
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4个汉字)
		// 保存生成的汉字字符串
		StringBuffer sRand = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String rand = code[random.nextInt(code.length)];
			sRand.append(rand);
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入session
		session.setAttribute("rand", sRand.toString());
		g.dispose();
		// 输出图象到页面
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			try {
				logger.info(LoggerUtil.getInfoMsg("验证码生成失败", session));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
