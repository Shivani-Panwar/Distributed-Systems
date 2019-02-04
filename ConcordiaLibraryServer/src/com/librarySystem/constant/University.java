package com.librarySystem.constant;

/**
 * The enum defines the prefixes of the universities that will be used in the userIDs and the itemIDs.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public enum University {
	
	CONCORDIA("CON", 9996),
	MCGILL("MAC", 9997),
	MONTREAL("MON", 9998);
	
	private final String code;
	private final int udpPort;
	
	private University(final String code, final int udpPort){
		this.code = code;
		this.udpPort = udpPort;
	}

	public String getCode() {
		return code;
	}
	
	public int getUdpPort(){
		return udpPort;
	}
}
