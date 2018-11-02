package com.sinosoft.normal.vo;
/** 接口调用总量信息 */
public class InterfaceCalledTotalVo {
	/** 接口类型 */
	private String interfaceType;
	/** 调用次数 */
	private Long calledNum;
	/** 平均耗时 （毫秒数） */
	private Double calledAvgTime;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public InterfaceCalledTotalVo() {
		
	}
	public InterfaceCalledTotalVo(String interfaceType, Long calledNum, Double calledAvgTime, String reverse1,
			String reverse2, String reverse3) {
		super();
		this.interfaceType = interfaceType;
		this.calledNum = calledNum;
		this.calledAvgTime = calledAvgTime;
		this.reverse1 = reverse1;
		this.reverse2 = reverse2;
		this.reverse3 = reverse3;
	}
	public String getInterfaceType() {
		return interfaceType;
	}
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	public Long getCalledNum() {
		return calledNum;
	}
	public void setCalledNum(Long calledNum) {
		this.calledNum = calledNum;
	}
	public Double getCalledAvgTime() {
		return calledAvgTime;
	}
	public void setCalledAvgTime(Double calledAvgTime) {
		this.calledAvgTime = calledAvgTime;
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
