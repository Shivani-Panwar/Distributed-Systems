package com.librarySystem.constant;

/**
 * The enum defines the prefixes of the universities that will be used in the userIDs and the itemIDs.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public enum University {
	
	CONCORDIA("CON"),
	MCGILL("MAC"),
	MONTREAL("MON");
	
	private final String code;
	
	private University(final String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
