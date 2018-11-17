package com.jachs.websocket.vo;

public class StatusVo {
	private int status;
	private String name;
	
	public StatusVo() {
		super();
	}
	public StatusVo(int status, String name) {
		super();
		this.status = status;
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
