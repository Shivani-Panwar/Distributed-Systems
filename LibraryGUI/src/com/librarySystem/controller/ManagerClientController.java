package com.librarySystem.controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

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
		Registry getRegister = LocateRegistry.getRegistry(9999);
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
			break;
			
		case 2:
			break;
		
		case 3:
			System.out.println("Enter the ID for the item to be removed: ");
			itemID= managerInp.nextLine();
			System.out.println("Enter the name for the item to be removed: ");
			itemName=managerInp.nextLine();
			System.out.println("Enter the number items to be removed: ");
			quantity=managerInp.nextInt();
			break;
			
		}
		managerInp.close();
	}

}
