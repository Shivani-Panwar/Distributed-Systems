package com.librarySystem.utility;

import com.librarySystem.constant.University;

/**
 * This class contains utilities that the client uses.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Utilities {

	/**
	 * This method is used to check the code that is prefixed before the item
	 * IDs and the user IDs
	 * 
	 * @param str
	 *            - The string that is to be checked
	 * @param clientCheck
	 *            - true if code is to be checked for a user, false if the code
	 *            is to be checked for item
	 * @param code
	 *            - the code with which the string will be compared
	 * @param isManager
	 *            - true when the user is a manager, false in all other cases
	 * @return
	 */
	public static boolean CodeCheck(String str, boolean clientCheck, String code, boolean isManager) {

		str = (clientCheck) ? str.substring(0, 4) : str.substring(0, 3);
		if (clientCheck) {
			if (str.equals(code)) {
				if (isManager) {
					if (str.endsWith("M")) {
						return true;
					}
				} else {
					if (str.endsWith("U")) {
						return true;
					}
				}
			}
		} else {
			if (str.equals(code)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * This method is used to find the university corresponding to the user ID.
	 * @param userId
	 * @return
	 */
	public static University getUniversity(String userId) {
		if (University.CONCORDIA.getCode().equals(userId.substring(0, 3))) {
			return University.CONCORDIA;
		} else if (University.MCGILL.getCode().equals(userId.substring(0, 3))) {
			return University.MCGILL;
		} else {
			return University.MONTREAL;
		}
	}

}
