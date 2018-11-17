package com.jachs.websocket.entity;

import java.io.Serializable;

public class Message implements Serializable{
	private String userName;//用户
	private Status status;//状态
	private String mess;//信息
	
	public Message() {
		super();
	}

	public Message(String userName, Status status, String mess) {
		super();
		this.userName = userName;
		this.status = status;
		this.mess = mess;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
