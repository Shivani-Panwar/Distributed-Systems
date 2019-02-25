package com.librarySystem;

import java.io.BufferedReader;
import java.io.IOException;

import com.librarySystem.constant.University;
import com.librarySystem.controller.ManagerClientController;
import com.librarySystem.controller.UserClientController;
import com.librarySystem.service.RMIService;
import com.librarySystem.utility.InputReader;
import com.librarySystem.utility.Utilities;

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
		
		// Capture the member ID
		BufferedReader reader = InputReader.getReader();
		boolean exit = false;
		do {
		
		System.out.println("Enter your ID:\n");
		String memberID;
		try {
			memberID = reader.readLine();

			// Check if the ID entered is valid
			if (memberID.length() == 8 && Utilities.matchesOrNot("\\d+",memberID.substring(4,8))) {
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
					// When the member is a manager, call the managerClient
					// method
					// to proceed
					ManagerClientController manager = new ManagerClientController();
					manager.managerClient(memberID);
				} else {
					System.out.println("Enter a valid ID!!\n");
				}
			} else {
				System.out.println("Enter a valid ID!!\n");
			}

			System.out.println("Want to exit the application? (Y/N)\n");
			String exitString = reader.readLine();
			exit = (exitString.equals("Y"))? true : false;
			
		} catch (IOException e) {
			
			Utilities.errorLog(e.getMessage());
			
		} 
			
		} while (!exit);
		System.out.println("Do you wish to run the threads? (Y/N)");
		try {
			String runthread= reader.readLine();
			if(runthread.equals("Y")){
			Runnable thread1 =
				    new Runnable(){
				        public void run(){
				        	RMIService service = new RMIService();
				    		System.out.println(service.addItem(University.CONCORDIA, "CONM1111", "CON1111", "JAVA", 10));
				        }
				    };
		    Runnable thread2 =
				    new Runnable(){
				        public void run(){
				        	RMIService service = new RMIService();
				    		System.out.println(service.addItem(University.CONCORDIA, "CONM1111", "CON1111", "JAVA", 11));
				        }
				    };
		    thread1.run();
		    thread2.run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Input");
		}
		InputReader.closeReader();
		System.out.println("Application terminated!!");
		
	}

}
