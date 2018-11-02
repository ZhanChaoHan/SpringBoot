package com.sinosoft.normal.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.sinosoft.normal.vo.UserSession;


/**
 * 生成日志相关的类
 * LvWeisheng 2011-12-29 v1.0.0 增加新版本
 */
public class LoggerUtil {

	/**
	 * 获取Info级别的信息，组织相应的log日志语句
	 * @param operate 操作类型（也是功能描述）
	 * @return 组织好的日志字符串
	 * @exception Exception 所有异常
	 */
	public static String getInfoMsg(String operate,HttpSession session) throws Exception {
		// 获得用户信息
		UserSession userSession = (UserSession) session.getAttribute(AppConst.USER_SESSION_ID);
		// 格式化时间对象
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获得登录用户信息
		String userCode = userSession.getUserCode();// 用户代码
		String currentTime = dateFormat.format(new Date());// 格式化当前时间成"yyyy-MM-dd HH:mm:ss"
		String comCode =userSession.getComCode();// 用户的机构代码
		// 组织log字符串
		StringBuffer logInfoString = new StringBuffer(100);
		logInfoString.append("【");
		logInfoString.append(comCode);// 用户的机构代码
		logInfoString.append("】机构的【 ");
		logInfoString.append(userCode);// 用户代码
		logInfoString.append("】用户在【");
		logInfoString.append(currentTime);// 当前时间
		logInfoString.append("】时间执行了操作->【");
		logInfoString.append(operate);// 操作类型（也是功能描述）
		logInfoString.append("】");
		// 返回
		return logInfoString.toString();
	}

	
	
	
//	
//	/**
//	 * 打印logdebug 日志
//	 * @param operate 操作类型（也是功能描述）
//	 * @param obj 主键
//	 * @param primaryString 操作明细列表
//	 * @return 组织好的日志字符串
//	 * @exception Exception 所有异常
//	 */
//	public static String getDebugMsg(String operate, Object obj, String[] primaryString) throws Exception {
//		// 获得用户信息
//		UserSession userSession = AuthorityUtil.getSysUserSession();
//		// 格式化时间对象
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		// 获得登录用户信息
//		String userCode = userSession.getUserCode();// 用户代码
//		String userName = userSession.getUserName();// 用户名
//		String currentTime = dateFormat.format(new java.util.Date());// 格式化当前时间成"yyyy-MM-dd HH:mm:ss"
//		String comCode =userSession.getComCode();// 用户的机构代码
//		// 组织log字符串
//		StringBuffer logDebugString = new StringBuffer(100);
//		logDebugString.append("【");
//		logDebugString.append(operate);// 操作类型（也是功能描述）
//		logDebugString.append("】 机构代码: ");
//		logDebugString.append(comCode);// 用户的机构代码
//		logDebugString.append(" 用户代码: ");
//		logDebugString.append(userCode);// 用户代码
//		logDebugString.append(" (");
//		logDebugString.append(userName);// 用户名
//		logDebugString.append(") 操作时间: ");
//		logDebugString.append(currentTime);// 当前时间
//		// 操作明细列表 有值的情况
//		if(null != primaryString && 0 < primaryString.length) {
//			// 
//			StringBuffer sb = new StringBuffer(200);
//			for(String primaryKey : primaryString){
//				sb.append(primaryKey).append(",");
//			}
//			logDebugString.append(" ");
//			logDebugString.append(operate);
//			logDebugString.append(": ");
//			logDebugString.append(sb.substring(0, sb.length()-1));
//		}
//		if(obj != null){
//			logDebugString.append(" ");
//			logDebugString.append(operate);
//			logDebugString.append(": ");
//			logDebugString.append(BeanUtils.describe(obj));
//		}
//		return logDebugString.toString();
//	}
//	
//	/**
//	 * 
//	 * @param operate
//	 * @param obj 联合主键
//	 * @param objId
//	 * @param primaryString
//	 * @return
//	 * @throws Exception
//	 */
//	public static String getLogDebugString(String operate,Object obj,Object objId,String[] primaryString) throws Exception{
//		UserSession userSession = AuthorityUtil.getSysUserSession();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String userCode = userSession.getUserCode();
//		String userName = userSession.getUserName();
//		String currentTime = dateFormat.format(new java.util.Date()); 
//		String comCode =userSession.getComCode();
//		String logDebugString ="【"+operate+"】 机构代码: "+comCode+" 用户代码: "+userCode+" ("+userName+") 操作时间: "+currentTime;
//		if(primaryString != null && primaryString.length>0){
//			StringBuffer sb = new StringBuffer(200);
//			for(String primaryKey : primaryString){
//				sb.append(primaryKey).append(",");
//			}
//			String s = sb.toString().substring(0,sb.toString().length()-1);
//			logDebugString += " "+operate+": "+s;
//		}
//		if(obj != null){
//			logDebugString += " "+operate+": " + BeanUtils.describe(obj);
//		}
//		if(objId != null){
//			logDebugString += " "+"ID"+": " + BeanUtils.describe(objId);
//		}
//		
//		return logDebugString;
//	}
//	
}

