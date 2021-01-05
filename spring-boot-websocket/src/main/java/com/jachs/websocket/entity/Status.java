package com.jachs.websocket.entity;


/***
 * 
 * @author zhanchaohan
 *
 */
public enum Status {
	LEAVE(0),
	MOVER(1),
	SENDMESS(2),
	CHECKUSER(3),
	EATCHESS(4),
	STARTGAME(5);
	private int status;

	private Status(int status) {
		this.setStatus(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
