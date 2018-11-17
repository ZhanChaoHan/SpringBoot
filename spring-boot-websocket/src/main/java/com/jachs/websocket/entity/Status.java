package com.jachs.websocket.entity;

public enum Status {
	MOVER(2),SENDMESS(3),CHECKUSER(4);
	private int status;

	private Status(int status) {
		this.status = status;
	}

	
}
