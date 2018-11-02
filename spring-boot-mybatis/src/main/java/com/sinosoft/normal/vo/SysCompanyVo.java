package com.sinosoft.normal.vo;

import org.springframework.web.bind.annotation.RequestParam;

public class SysCompanyVo {

	/** 机构代码 */
	private String comCode = "";
	/** 机构名称 */
	private String comCname = "";
	/** 地区代码 */
	private String areaCode = "";
	/** 机构地址 */
	private String comAddress = "";
	/** 地区名称 */
	private String areaCname = "";
	/**机构级别*/
	private String comLevel = "";
	/**机构类型*/
	private String comType = "";
	/***机构电话**/
	private String comphone = "";
	/**上级机构**/
	private String supComCode="";
	
	public String getAreaCname() {
		return areaCname;
	}
	public void setAreaCname(String areaCname) {
		this.areaCname = areaCname;
	}
	public String getComCode() {
		return comCode;
	}
	public String getSupComCode() {
		return supComCode;
	}
	public void setSupComCode(String supComCode) {
		this.supComCode = supComCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getComCname() {
		return comCname;
	}
	public void setComCname(String comCname) {
		this.comCname = comCname;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getComLevel() {
		return comLevel;
	}
	public void setComLevel(String comLevel) {
		this.comLevel = comLevel;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public String getComphone() {
		return comphone;
	}
	public void setComphone(String comphone) {
		this.comphone = comphone;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	
}
