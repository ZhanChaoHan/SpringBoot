package com.jachs.websocket.entity;

import java.io.Serializable;

public class Message implements Serializable{
	private Status status;//状态
	private String mess;//信息
	
	public Message() {
		super();
	}
	public Message(Status status, String mess) {
		super();
		this.status = status;
		this.mess = mess;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
}
