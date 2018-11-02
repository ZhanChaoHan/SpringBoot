package com.sinosoft.normal.vo;
/** 登陆用户状态信息 */
public class LoginStatusVo {
	/** 出单区域 */
	private String salesRegion;
	/** 登陆用户类型 00-活跃用户，01-正常用户，02-僵尸用户 */
	private String loginUserType;
	/** 登陆人数 */
	private Long totalNum;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public LoginStatusVo() {
		
	}
	public LoginStatusVo(String salesRegion, String loginUserType, Long totalNum, String reverse1, String reverse2,
			String reverse3) {
		super();
		this.salesRegion = salesRegion;
		this.loginUserType = loginUserType;
		this.totalNum = totalNum;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
	}
	public String getSalesRegion() {
		return salesRegion;
	}
	public void setSalesRegion(String salesRegion) {
		this.salesRegion = salesRegion;
	}
	public String getLoginUserType() {
		return loginUserType;
	}
	public void setLoginUserType(String loginUserType) {
		this.loginUserType = loginUserType;
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
