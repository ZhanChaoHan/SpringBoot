package com.jachs.websocket.entity;

import java.io.Serializable;


/***
 * 
 * @author zhanchaohan
 *
 */
public class Message implements Serializable{
	private String userName;//用户
	private boolean playUser;//游戏玩家
	private Status status;//状态
	private String mess;//信息
	
	public Message() {
		super();
	}
	public Message(String userName, boolean playUser, Status status, String mess) {
		super();
		this.userName = userName;
		this.playUser = playUser;
		this.status = status;
		this.mess = mess;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isPlayUser() {
		return playUser;
	}
	public void setPlayUser(boolean playUser) {
		this.playUser = playUser;
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
