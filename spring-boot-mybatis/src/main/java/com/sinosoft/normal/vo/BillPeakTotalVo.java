package com.sinosoft.normal.vo;

import java.sql.Timestamp;

public class BillPeakTotalVo {
	private Timestamp time;
	private Integer thisYearIssue;
	private Integer	lastYearIssue;
	private Integer thisYearBill;
	private Integer lastYearBill;
	public BillPeakTotalVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BillPeakTotalVo(Timestamp time, Integer thisYearIssue, Integer lastYearIssue, Integer thisYearBill,
			Integer lastYearBill) {
		super();
		this.time = time;
		this.thisYearIssue = thisYearIssue;
		this.lastYearIssue = lastYearIssue;
		this.thisYearBill = thisYearBill;
		this.lastYearBill = lastYearBill;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getThisYearIssue() {
		return thisYearIssue;
	}
	public void setThisYearIssue(Integer thisYearIssue) {
		this.thisYearIssue = thisYearIssue;
	}
	public Integer getLastYearIssue() {
		return lastYearIssue;
	}
	public void setLastYearIssue(Integer lastYearIssue) {
		this.lastYearIssue = lastYearIssue;
	}
	public Integer getThisYearBill() {
		return thisYearBill;
	}
	public void setThisYearBill(Integer thisYearBill) {
		this.thisYearBill = thisYearBill;
	}
	public Integer getLastYearBill() {
		return lastYearBill;
	}
	public void setLastYearBill(Integer lastYearBill) {
		this.lastYearBill = lastYearBill;
	}
	
	
	
	
}
