package com.jachs.websocket.entity;

public enum Status {
	CONNTION(1),LOGINOUT(2),MOVER(3),SENDMESS(4);
	private int status;

	private Status(int status) {
		this.status = status;
	}

	
}
