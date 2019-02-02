package com.librarySystem;

import java.io.IOException;

import com.librarySystem.constant.University;
import com.librarySystem.dao.Client;
import com.librarySystem.dao.LibraryInterface;

/**
 * This class takes the input from the member and then determines which library
 * the member belongs to and whether the member is a user or a manager.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class LibrarySystem {

	public static void main(String[] args) {
		
		Client client = new Client();
		LibraryInterface concordiaLibrary = client.getLibrary(University.CONCORDIA);
		LibraryInterface mcgillLibrary = client.getLibrary(University.MCGILL);
		
		try {
			System.out.println(concordiaLibrary.addItem("CONM1235", "CON1234", "Examape", 1));
			System.out.println(mcgillLibrary.addItem("MACM1233", "MAC1234", "Examape", 1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Scanner sc = new Scanner(System.in);
		// Capture the member ID
		System.out.println("Enter your ID: ");
		String memberID = sc.nextLine();
		sc.close();
		// Check if the ID entered is valid
		if (memberID.length() == 8) {
			if (Utilities.CodeCheck(memberID, true, University.CONCORDIA.getCode(), false)
					|| Utilities.CodeCheck(memberID, true, University.MCGILL.getCode(), false)
					|| Utilities.CodeCheck(memberID, true, University.MONTREAL.getCode(), false)) {
				// When the member is a user, call the userClient method to
				// proceed
				UserClientController user = new UserClientController();
				user.userClient(memberID);
			} else if (Utilities.CodeCheck(memberID, true, University.CONCORDIA.getCode(), true)
					|| Utilities.CodeCheck(memberID, true, University.MCGILL.getCode(), true)
					|| Utilities.CodeCheck(memberID, true, University.MONTREAL.getCode(), true)) {
				// When the member is a manager, call the managerClient method
				// to proceed
				ManagerClientController manager = new ManagerClientController();
				manager.managerClient(memberID);
			} else {
				System.out.println("Enter a valid ID!! ");
			}
		} else {
			System.out.println("Enter a valid ID!!");
		}*/

	}

}
