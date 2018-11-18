package com.jachs.websocket.entity;

public enum Status {
	MOVER(1),SENDMESS(2),CHECKUSER(3),EATCHESS(4);
	private int status;

	private Status(int status) {
		this.status = status;
	}

	
}
