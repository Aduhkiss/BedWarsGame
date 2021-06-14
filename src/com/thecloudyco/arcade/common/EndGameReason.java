package com.thecloudyco.arcade.common;

public enum EndGameReason {
	
	ADMIN_STOPPED("Staff member stopped game"),
	SERVER_RESTARTING("Server Restarting?"),
	ALL_PLAYERS_LEFT("All players left the arcade server");
	
	private String reason;
	
	EndGameReason(String reason) {
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}
}
