package com.sinosoft.normal.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.normal.po.SysRole;


public class UserSession {

	/** 登录用户代码 */
	private String userCode = "";
	/** 登录用户姓名 */
	private String userName = "";
	/** 登录用户机构代码 */
	private String comCode = "";
	/** 登录用户机构名称 */
	private String comCname = "";
	/** 登录用户公司地区代码 */
	private String areaCode = "";
	/** 登录用户机构类型 */
	private String comType = "";
	/** 登录用户机构级别   */
	private String comLevel = null;
	
	/** 登录用户的页面功能集合 */
	private Map<String, SysRole> funcPowers = new HashMap<String, SysRole>();
	/** 登录用户的数据功能集合 */
	private List<SysRole> dataPowers = new ArrayList<SysRole>();
	
	/** getter and setter方法  */
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public String getComLevel() {
		return comLevel;
	}
	public void setComLevel(String comLevel) {
		this.comLevel = comLevel;
	}
	public List<SysRole> getDataPowers() {
		return dataPowers;
	}
	public void setDataPowers(List<SysRole> dataPowers) {
		this.dataPowers = dataPowers;
	}
	public Map<String, SysRole> getFuncPowers() {
		return funcPowers;
	}
	public void setFuncPowers(Map<String, SysRole> funcPowers) {
		this.funcPowers = funcPowers;
	}
	public void setComCname(String comCname) {
		this.comCname = comCname;
	}
	public String getComCname() {
		return comCname;
	}
	
}
