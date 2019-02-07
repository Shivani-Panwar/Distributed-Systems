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

		if (Utilities.CodeCheck(userID, false, University.CONCORDIA.getCode(), false)) {
			university = University.CONCORDIA;
		} else if (Utilities.CodeCheck(userID, false, University.MCGILL.getCode(), false)) {
			university = University.MCGILL;
		} else {
			university = University.MONTREAL;
		}

		do {
			
			System.out.println(
					"Select the action to be performed:\n1.Borrow an item.\n2.Find an item.\n3.Return an item.\n4.Logout\n ");
			int selection =0; 
		
			selection=Integer.valueOf(reader.readLine());
			int yesno=0;
			switch (selection) {
			case 1:
				System.out.println("Enter the ID for the item to be borrowed: ");
				//if(userInp.hasNext()){
					
				itemID = reader.readLine();
				
				//}
				System.out.println("Enter the number of days for which you want to borrow the item: ");
				int days=0;
				days = Integer.valueOf(reader.readLine());
				
				
				String reply = service.borrowItem(university, userID, itemID, days);
				if(reply != null){
					if (reply.equals(Constants.BORROWED_FROM_OWN) || reply.equals(Constants.BORROWED_FROM_OTHER)) {
						System.out.println("The book has been successfully borrowed!!");
					}else if(reply.equals(Constants.BORROW_FAIL_ITEM_NOT_FOUND)){
						System.out.println("The item cannot be borrowed!!");
					}else if(reply.equals(Constants.BORROW_FAIL_OWN)){
						System.out.println("Item could not be borrowed.\nSelect an option:\n1.Add to wait list.\2.Perform another search.");
						
						yesno=Integer.valueOf(reader.readLine());
						if(yesno==1){
							
						}else{
							break;
						}
					}
				}
				break;

			case 2:
				System.out.println("Enter the name of the item to be searched: ");
				itemName = reader.readLine();
				ArrayList<Item> itemsfound = service.findItem(university, userID, itemName);
				if (!itemsfound.isEmpty()) {
					System.out.println("Items found: ");
					for (int i = 0; i < itemsfound.size(); i++) {
						System.out.println(itemsfound.get(i).getID() +" | "+itemsfound.get(i).getName() +" | "+itemsfound.get(i).getQuantity());
					}
				} else {
					System.out.println("No Such Item Found");
				}
				break;

			case 3:
				System.out.println("Enter the ID for the item to be returned: ");
				itemID = reader.readLine();
				if (service.returnItem(university, userID, itemID)) {
					System.out.println("The item was returned successfully!!");
				}
				break;

			case 4:
				logout = true;

			default:
				logout = true;
				System.out.println("Enter a valid choice!!");
			}
		} while (!logout);
	}

}