package com.librarySystem.controller;

import java.util.Scanner;

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
		
		System.out.println("Select the action to be performed:\n1.Borrow an item.\n2.Find an item.\n3.Return an item.\n ");
		int selection= userInp.nextInt();
		switch(selection)
		{
		case 1:
			System.out.println("Enter the ID for the item to be borrowed: ");
			itemID= userInp.nextLine();
			System.out.println("Enter the number of days for which you want to borrow the item: ");
			int days=userInp.nextInt();
			break;
		
		case 2:
			System.out.println("Enter the name of the item to be searched: ");
			itemName= userInp.nextLine();
			break;
		
		case 3:
			System.out.println("Enter the ID for the item to be returned: ");
			itemID=userInp.nextLine();
			break;
		
		default:
			System.out.println("Enter a valid choice!!");
		}
		
		
		userInp.close();
	}
	
}
