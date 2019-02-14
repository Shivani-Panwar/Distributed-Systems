package com.librarySystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.service.RMIService;
import com.librarySystem.utility.InputReader;
import com.librarySystem.utility.Utilities;

/**
 * This class defines the userClient method which is used for capturing the
 * inputs from the user. Its is used to capture all the details that will be
 * used to invoke the remote method. .
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class UserClientController {

	/**
	 * This method is used to take all the inputs from the manager and perform
	 * the required action till the manager does not exit the program.
	 * 
	 * @param userID
	 *            - ID of the user that is logged into the system.
	 * @throws IOException
	 */
	public void userClient(String userID) throws IOException {
		BufferedReader reader = InputReader.getReader();
		String itemID = null;
		String itemName = null;
		boolean logout = false;
		RMIService service = new RMIService();
		University university = null;

		// Check the university of the user.
		if (Utilities.CodeCheck(userID, false, University.CONCORDIA.getCode(), false)) {
			university = University.CONCORDIA;
		} else if (Utilities.CodeCheck(userID, false, University.MCGILL.getCode(), false)) {
			university = University.MCGILL;
		} else {
			university = University.MONTREAL;
		}

		do {

			System.out.println(
					"\nSelect the action to be performed:\n1.Borrow an item.\n2.Find an item.\n3.Return an item.\n4.Logout\n ");
			int selection = 0;
			try{
			selection = Integer.valueOf(reader.readLine());
			int yesno = 0;
			// boolean waiting = false;
			int choice = 0;
			switch (selection) {
			case 1:
				System.out.println("\nEnter the ID for the item to be borrowed: ");
				itemID = reader.readLine();

				System.out.println("Enter the number of days for which you want to borrow the item: ");
				int days = 0;
				days = Integer.valueOf(reader.readLine());

				// Call method in RMIService class
				String reply = service.borrowItem(university, userID, itemID, days);
				if (reply != null) {
					if (reply.equals(Constants.BORROWED_FROM_OWN) || reply.equals(Constants.BORROWED_FROM_OTHER)) {
						System.out.println("The book has been successfully borrowed from " + Utilities.getUniversity(itemID) + "!!\n");
					} else if (reply.equals(Constants.BORROW_FAIL_ITEM_NOT_FOUND)) {
						System.out.println("The item does not exist in " + Utilities.getUniversity(itemID) + "!!\n");
					}else if(reply.equals(Constants.BORROW_FAIL_ALREADY_BORROWED)){
						if(Utilities.getUniversity(userID).equals(Utilities.getUniversity(itemID))){
						System.out.println("You have already borrowed this item from the library\n");
						}else{
							System.out.println("You have already borrowed an item from this library\n");
						}
					}else if (reply.equals(Constants.BORROW_FAIL_OWN)) {
						// If the book is not available then ask user if he
						// wishes to be added in a wait list
						do {
							System.out.println(
									"Item could not be borrowed.\nSelect an option:\n1.Add to wait list.\n2.Perform another search.\n");

							yesno = Integer.valueOf(reader.readLine());
							switch (yesno) {
							case 1:
								if (service.addToQueue(university, itemID, userID)) {
									System.out.println("You have been added to the wait queue!!\n");
								}else{
									System.out.println("The user could not be added to the wait queue!!\n");
								}
								choice = 1;
								break;
							case 2:
								choice = 1;
								break;
							default:
								System.out.println("Enter a valid choice!!\n");
								choice = 0;
								break;
							}
						} while (choice == 0);

					}
				}
				break;

			case 2:
				System.out.println("Enter the name of the item to be searched:\n");
				itemName = reader.readLine();
				// Call RMIService method
				ArrayList<Item> itemsfound = service.findItem(university, userID, itemName);
				if (!itemsfound.isEmpty()) {
					System.out.println("Items found: ");
					for (int i = 0; i < itemsfound.size(); i++) {
						//System.out.println(itemsfound.get(i).getID() + " | " + itemsfound.get(i).getName() + " | "
							//	+ itemsfound.get(i).getQuantity());
						System.out.println(itemsfound.get(i).getID() + "  "
								+ itemsfound.get(i).getQuantity());
					}
				} else {
					System.out.println("No Such Item Found!!\n");
				}
				break;

			case 3:
				System.out.println("Enter the ID for the item to be returned:\n");
				itemID = reader.readLine();
				// Call RMIService method
				if (service.returnItem(university, userID, itemID)) {
					System.out.println("The item was returned successfully!!\n");
				} else {
					System.out.println("The item could not be returned!!\n");
				}
				break;

			case 4:
				logout = true;
				break;
			default:
				System.out.println("Enter a valid choice!!\n");
				break;
			}
		}catch(NumberFormatException e){
			System.out.println("Enter a valid number!!");
		}
		}while (!logout);
	}

}