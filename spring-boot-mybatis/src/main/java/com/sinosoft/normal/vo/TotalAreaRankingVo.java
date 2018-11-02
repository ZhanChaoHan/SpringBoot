package com.sinosoft.normal.vo;
/** 今日签单区域排行榜 */
public class TotalAreaRankingVo {
	/** 区域名称 */
	private String salesRegion;
	/** 签单数量 */
	private Integer totalBill;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public TotalAreaRankingVo() {
		
	}
	public TotalAreaRankingVo(String salesRegion, Integer totalBill, String reverse1, String reverse2,
			String reverse3) {
		super();
		this.salesRegion = salesRegion;
		this.totalBill = totalBill;
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
	public Integer getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(Integer totalBill) {
		this.totalBill = totalBill;
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
