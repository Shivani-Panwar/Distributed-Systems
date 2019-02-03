package com.librarySystem.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.service.RMIService;
import com.librarySystem.utility.Utilities;

/**
 * This class defines the userClient method which is used for capturing the inputs from the user.
 * Its is used to capture all the details that will be used to invoke the remote method.
 * .
 * @author shivani
 * @version 1.0
 *
 */
public class UserClientController {
	
	/**
	 *  This method is used to take all the inputs from the manager and perform the required
	 * action till the manager does not exit the program.
	 * @param userID - ID of the user that is logged into the system.
	 */
	public void userClient(String userID){
		Scanner userInp=new Scanner(System.in);
		String itemID=null;
		String itemName=null;
		RMIService service = new RMIService();
		University univ=null;
		
		if(Utilities.CodeCheck(userID, false, "CON", false)){
			univ=University.CONCORDIA;
		}else if(Utilities.CodeCheck(userID, false, "MCG", false)){
			univ=University.MCGILL;
		}else{
			univ=University.MONTREAL;
		}
		
		
		System.out.println("Select the action to be performed:\n1.Borrow an item.\n2.Find an item.\n3.Return an item.\n ");
		int selection= userInp.nextInt();
		switch(selection)
		{
		case 1:
			System.out.println("Enter the ID for the item to be borrowed: ");
			itemID= userInp.nextLine();
			System.out.println("Enter the number of days for which you want to borrow the item: ");
			int days=userInp.nextInt();
			if(service.borrowItem(univ, userID, itemID, days)){
				System.out.println("The book has been successfully borrowed!!");
			}
			break;
		
		case 2:
			System.out.println("Enter the name of the item to be searched: ");
			itemName= userInp.nextLine();
			ArrayList<Item> itemsfound = service.findItem(univ, userID, itemName);
			if(itemsfound.isEmpty()==false){
				System.out.println("Items found: ");
				for(int i=0;i<itemsfound.size();i++){
					System.out.println(itemsfound.get(i));
				}
			}
			break;
		
		case 3:
			System.out.println("Enter the ID for the item to be returned: ");
			itemID=userInp.nextLine();
			if(service.returnItem(univ, userID, itemID)){
				System.out.println("The item was returned successfully!!");
			}
			break;
		
		default:
			System.out.println("Enter a valid choice!!");
		}
		
		
		userInp.close();
	}
	
}