package com.sinosoft.normal.vo;

public class SysUserVo {
	/** 用户代码 */
	private String userCode = "";
	/** 用户姓名 */
	private String userName = "";
	/** 用户机构代码 */
	private String comCode = "";
	/** 用户机构名称 */
	private String comCname = "";
	/** 用户电话 */
	private String telePhone = "";
	/** 用户电话 */
	private String email = "";
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
	public String getComCname() {
		return comCname;
	}
	public void setComCname(String comCname) {
		this.comCname = comCname;
	}
	public String getEmail() {
		return email;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
