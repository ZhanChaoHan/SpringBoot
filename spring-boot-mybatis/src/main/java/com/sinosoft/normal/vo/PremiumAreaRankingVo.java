package com.sinosoft.normal.vo;
/** 今日保费区域排行榜 */
public class PremiumAreaRankingVo {
	/** 区域名称 */
	private String salesRegion;
	/** 保费金额 */
	private Integer premiums;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public PremiumAreaRankingVo() {
		
	}
	public PremiumAreaRankingVo(String salesRegion, Integer premiums, String reverse1, String reverse2,
			String reverse3) {
		super();
		this.salesRegion = salesRegion;
		this.premiums = premiums;
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
	public Integer getPremiums() {
		return premiums;
	}
	public void setPremiums(Integer premiums) {
		this.premiums = premiums;
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
