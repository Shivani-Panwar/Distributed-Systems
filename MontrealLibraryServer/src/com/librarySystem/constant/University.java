package com.librarySystem.constant;

/**
 * The enum defines the prefixes of the universities that will be used in the userIDs and the itemIDs.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public enum University {
	

	CONCORDIA("CON", 9996,9911),
	MCGILL("MCG", 9997,9912),
	MONTREAL("MON", 9998,9913);
	
	private final String code;
	private final int udpPort;
	private final int soapPort;
	
	private University(final String code, final int udpPort, final int soapPort){
		this.code = code;
		this.udpPort = udpPort;
		this.soapPort=soapPort;
	}

	public String getCode() {
		return code;
	}
	
	public int getUdpPort(){
		return udpPort;
	}
	
	public int getSoapPort(){
		return soapPort;
	}
}
