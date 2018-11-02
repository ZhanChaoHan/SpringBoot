package com.sinosoft.normal.vo;
/** 接口调用异常信息 */
public class InterfaceCalledErroVo {
	/** 接口类型 */
	private String interfaceType;
	/** 调用次数 */
	private Long calledNum;
	/** 接口报错次数 */
	private Long errorCalledNum;
	/** 报错异常名称 */
	private String errorCallName;
	/** 备注1 */
	private String reverse1;
	/** 备注2 */
	private String reverse2;
	/** 备注3 */
	private String reverse3;
	public InterfaceCalledErroVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InterfaceCalledErroVo(String interfaceType, Long calledNum, Long errorCalledNum, String errorCallName,
			String reverse1, String reverse2, String reverse3) {
		super();
		this.interfaceType = interfaceType;
		this.calledNum = calledNum;
		this.errorCalledNum = errorCalledNum;
		this.errorCallName = errorCallName;
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
	public Long getErrorCalledNum() {
		return errorCalledNum;
	}
	public void setErrorCalledNum(Long errorCalledNum) {
		this.errorCalledNum = errorCalledNum;
	}
	public String getErrorCallName() {
		return errorCallName;
	}
	public void setErrorCallName(String errorCallName) {
		this.errorCallName = errorCallName;
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
