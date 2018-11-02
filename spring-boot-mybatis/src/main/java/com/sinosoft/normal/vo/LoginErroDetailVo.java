package com.sinosoft.normal.vo;

import java.sql.Timestamp;

/** 登陆用户异常明细信息 */
public class LoginErroDetailVo {
	/** 登陆区域 */
	private String loginRegion;
	/** 登陆地区 */
	private String loginArea;
	/** 登陆用户 */
	private String loginUser;
	/** 登陆门店 */
	private String loginCom;
	/** 登陆时间 */
	private Timestamp loginTime;
	/** 登陆人数 */
	private Long totalNum;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public LoginErroDetailVo() {
		
	}
	public LoginErroDetailVo(String loginRegion, String loginArea, String loginUser, String loginCom,
			Timestamp loginTime, Long totalNum, String reverse1, String reverse2, String reverse3) {
		super();
		this.loginRegion = loginRegion;
		this.loginArea = loginArea;
		this.loginUser = loginUser;
		this.loginCom = loginCom;
		this.loginTime = loginTime;
		this.totalNum = totalNum;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
	}
	public String getLoginRegion() {
		return loginRegion;
	}
	public void setLoginRegion(String loginRegion) {
		this.loginRegion = loginRegion;
	}
	public String getLoginArea() {
		return loginArea;
	}
	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getLoginCom() {
		return loginCom;
	}
	public void setLoginCom(String loginCom) {
		this.loginCom = loginCom;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public String getReverse1() {
		return reverse1;
	}
	public void setReverse1(String reverse1) {
		this.reverse1 = reverse1;
	}
	public String getReverse2() {
		return reverse2;
	}
	public void setReverse2(String reverse2) {
		this.reverse2 = reverse2;
	}
	public String getReverse3() {
		return reverse3;
	}
	public void setReverse3(String reverse3) {
		this.reverse3 = reverse3;
	}
	
}
