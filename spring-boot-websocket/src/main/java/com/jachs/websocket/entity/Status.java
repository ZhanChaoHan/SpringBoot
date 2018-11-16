package com.jachs.websocket.entity;

public enum Status {
	CONNTION(1),LOGINOUT(2);
	private int status;

	private Status(int status) {
		this.status = status;
	}

	
}
