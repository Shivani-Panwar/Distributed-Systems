package com.librarySystem.controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;
import com.librarySystem.service.RMIService;

/**
 * This class defines the managerClient method which is used for capturing the inputs from the user.
 * Its is used to capture all the details that will be used to invoke the remote method.
 * @author Shivani
 *
 */
public class ManagerClientController {
	/**
	 * This method is used to take all the inputs from the manager and perform the required
	 * action till the manager does not exit the program.
	 * @param managerID - ID of the manager that is logged into the system.
	 * @throws RemoteException 
	 */
	public void managerClient(String managerID) throws RemoteException{
		Scanner managerInp=new Scanner(System.in);
		String itemID=null;
		String itemName=null;
		int quantity=0;
		University univ=null;
		RMIService service = new RMIService();
		
		if(Utilities.CodeCheck(managerID, false, "CON", false)){
			univ=University.CONCORDIA;
		}else if(Utilities.CodeCheck(managerID, false, "MCG", false)){
			univ=University.MCGILL;
		}else{
			univ=University.MONTREAL;
		}
		
		System.out.println("Select the action to be performed:\n1.Add an item.\n2.Find an item.\n3.Delete an item.\n  ");
		int selection= managerInp.nextInt();
		switch(selection)
		{
		case 1:
			System.out.println("Enter the ID for the item to be Added: ");
			itemID= managerInp.nextLine();
			System.out.println("Enter the name for the item to be Added: ");
			itemName=managerInp.nextLine();
			System.out.println("Enter the number items to be added: ");
			quantity=managerInp.nextInt();
			if(service.addItem(univ, managerID, itemID, itemName, quantity)){
				System.out.println("The item was added successfully!!");
			}else{
				System.out.println("The item could not be added!!");
			}
			break;
			
		case 2:
			ArrayList<Item> itemsfound = service.listItemAvailability(univ, managerID);
			if(itemsfound.isEmpty()==false){
				System.out.println("Items found: ");
				for(int i=0;i<itemsfound.size();i++){
					System.out.println(itemsfound.get(i));
				}
			}
			break;
		
		case 3:
			System.out.println("Enter the ID for the item to be removed: ");
			itemID= managerInp.nextLine();
			System.out.println("Enter the name for the item to be removed: ");
			itemName=managerInp.nextLine();
			System.out.println("Enter the number items to be removed: ");
			quantity=managerInp.nextInt();
			if(service.removeItem(univ, managerID, itemID, quantity)){
				System.out.println("The item was successfully returned!!");
			}
			else{
				System.out.println("The item cannot be returned!!");
			}
			
			break;
			
		}
		managerInp.close();
	}

}
