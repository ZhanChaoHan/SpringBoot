package com.sinosoft.normal.vo;

import java.sql.Timestamp;
/** 登陆用户时间信息 */
public class LoginTimeVo {
	/** 登录时间 */
	private Timestamp loginTime;
	/** 登录人数 */
	private Long totalNum;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public LoginTimeVo() {
		
	}
	public LoginTimeVo(Timestamp loginTime, Long totalNum, String reverse1, String reverse2, String reverse3) {
		super();
		this.loginTime = loginTime;
		this.totalNum = totalNum;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
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
