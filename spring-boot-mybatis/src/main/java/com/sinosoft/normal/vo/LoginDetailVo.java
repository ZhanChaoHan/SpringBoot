package com.sinosoft.normal.vo;
/** 登陆用户明细信息 */
public class LoginDetailVo {
	/** 登陆区域 */
	private String loginRegion;
	/** 登陆地区 */
	private String loginArea;
	/** 登陆用户 */
	private String loginUser;
	/** 登陆门店 */
	private String loginCom;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public LoginDetailVo() {
		
	}
	public LoginDetailVo(String loginRegion, String loginArea, String loginUser, String loginCom, String reverse1,
			String reverse2, String reverse3) {
		super();
		this.loginRegion = loginRegion;
		this.loginArea = loginArea;
		this.loginUser = loginUser;
		this.loginCom = loginCom;
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
