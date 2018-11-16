package com.jachs.websocket.entity;

import java.io.Serializable;

public class Message implements Serializable{
	private Status Status;//状态
	private String Mess;//信息
	
	
	public Message() {
		super();
	}
	public Message(com.jachs.websocket.entity.Status status, String mess) {
		super();
		Status = status;
		Mess = mess;
	}
	public Status getStatus() {
		return Status;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	public String getMess() {
		return Mess;
	}
	public void setMess(String mess) {
		Mess = mess;
	}
	
}
