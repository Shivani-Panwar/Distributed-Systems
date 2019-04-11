package com.librarySystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.DSImplementation;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.service.CorbaService;
import com.librarySystem.service.LibraryService;
import com.librarySystem.service.RMIService;
import com.librarySystem.service.SOAPWebService;
import com.librarySystem.utility.InputReader;
import com.librarySystem.utility.Utilities;

/**
 * This class defines the managerClient method which is used for capturing the
 * inputs from the user. Its is used to capture all the details that will be
 * used to invoke the remote method.
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class ManagerClientController {
	
	private LibraryService service;
	
	public ManagerClientController(){
		if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.RMI)){
			service = new RMIService();
		} else if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.CORBA)){
			service = new CorbaService();
		}else{
			service = new SOAPWebService();
			}
	}
	
	/**
	 * This method is used to take all the inputs from the manager and perform
	 * the required action till the manager does not exit the program.
	 * 
	 * @param managerID
	 *            - ID of the manager that is logged into the system.
	 * @throws IOException 
	 */
	public void managerClient(String managerID) throws IOException {
		BufferedReader reader = InputReader.getReader();
		String itemID = null;
		String itemName = null;
		boolean logout = false;
		int quantity = 0;
		University university = null;
		if (Utilities.CodeCheck(managerID, false, University.CONCORDIA.getCode(), false)) {
			university = University.CONCORDIA;
		} else if (Utilities.CodeCheck(managerID, false, University.MCGILL.getCode(), false)) {
			university = University.MCGILL;
		} else {
			university = University.MONTREAL;
		}

		do {
			System.out.println(
					"\nSelect the action to be performed:\n1.Add an item.\n2.Find an item.\n3.Delete an item.\n4.Logout\n");
			int selection = Integer.valueOf(reader.readLine());
			try{
			switch (selection) {
			case 1:
				System.out.println("\nEnter the ID for the item to be Added: ");
				itemID = reader.readLine();
				System.out.println("Enter the name for the item to be Added: ");
				itemName = reader.readLine();
				System.out.println("Enter the number items to be added: ");
				quantity = Integer.valueOf(reader.readLine());
				if(Utilities.matchesOrNot("\\d+",itemID.substring(3,7))){
					if (service.addItem(university, managerID, itemID, itemName, quantity)) {
						System.out.println("The item was added successfully!!\n");
					} else {
						System.out.println("The item could not be added!!\n");
					}
				}else{
					System.out.println("Enter a valid item ID");
				}
				break;

			case 2:
				//ArrayList<Item> itemsfound = service.listItemAvailability(university, managerID);
				ArrayList<Item> itemsfound = service.listItemAvailability(university, managerID);
				if (itemsfound.isEmpty() == false) {
					System.out.println("Items found: ");
					for (int i = 0; i < itemsfound.size(); i++) {
						System.out.println(itemsfound.get(i).getID() +"   "+itemsfound.get(i).getName() +"   "+itemsfound.get(i).getQuantity());
					}
				}else{
					System.out.println("No items were found!!\n");
				}
				break;

			case 3:
				System.out.println("\nEnter the ID for the item to be removed: ");
				itemID = reader.readLine();
				System.out.println("Enter the number items to be removed (Enter zero to delete an item completely): ");
				quantity = Integer.valueOf(reader.readLine());
				if (service.removeItem(university, managerID, itemID, quantity)) {
					System.out.println("The item was successfully removed!!\n");
				} else {
					System.out.println("The item cannot be removed!!\n");
				}

				break;
			case 4:
				logout = true;
				break;
			default:
				System.out.println("Enter a valid choice!!\n");
				break;
			}
			}catch(NumberFormatException |StringIndexOutOfBoundsException e){
				System.out.println("Enter a valid number!!");
			}
		} while (!logout);
	}

}
