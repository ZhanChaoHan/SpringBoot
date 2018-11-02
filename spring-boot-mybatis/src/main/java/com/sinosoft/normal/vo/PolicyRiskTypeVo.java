package com.sinosoft.normal.vo;

import java.sql.Timestamp;
/** 保费签单量险种信息 */
public class PolicyRiskTypeVo {
	/** 出单险种 */
	private String riskType;
	/** 总签单量 */
	private Long totalNum;
	/** 总保费 */
	private Double totalPremium;
	/** 出单时间 */
	private Timestamp signTime;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public PolicyRiskTypeVo() {
		
	}
	public PolicyRiskTypeVo(String riskType, Long totalNum, Double totalPremium, Timestamp signTime, String reverse1,
			String reverse2, String reverse3) {
		super();
		this.riskType = riskType;
		this.totalNum = totalNum;
		this.totalPremium = totalPremium;
		this.signTime = signTime;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public Double getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(Double totalPremium) {
		this.totalPremium = totalPremium;
	}
	public Timestamp getSignTime() {
		return signTime;
	}
	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
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
