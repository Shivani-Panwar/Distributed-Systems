package com.librarySystem.utility;

/**
 * This class contains utilities that the client uses.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Utilities {
 
	public static boolean CodeCheck(String str, boolean clientCheck, String code, boolean isManager){
		
		str = (clientCheck)?str.substring(0,4):str.substring(0,3);
		if(clientCheck){
			if(str.equals(code)){
				if(isManager){
					if(str.endsWith("M")){
						return true;
					}
				} else {
					if(str.endsWith("U")){
						return true;
					}
				}
			}
		} else {
			if(str.equals(code)){
				return true;
			}
		}
		
		return false;
	}	
}
