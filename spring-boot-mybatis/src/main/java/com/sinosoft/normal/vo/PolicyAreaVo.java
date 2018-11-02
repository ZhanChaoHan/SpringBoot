package com.sinosoft.normal.vo;
/** 保费签单量地区信息 */ 
public class PolicyAreaVo {
	/** 出单区域 */
	private String salesRegion;
	/** 出单地区 */
	private String salesArea;
	/** 总签单量 */
	private Long totalNum;
	/** 总保费 */
	private Double totalPremium;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public PolicyAreaVo() {
	
	}
	public PolicyAreaVo(String salesRegion, String salesArea, Long totalNum, Double totalPremium, String reverse1,
			String reverse2, String reverse3) {
		super();
		this.salesRegion = salesRegion;
		this.salesArea = salesArea;
		this.totalNum = totalNum;
		this.totalPremium = totalPremium;
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
	public String getSalesArea() {
		return salesArea;
	}
	public void setSalesArea(String salesArea) {
		this.salesArea = salesArea;
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
