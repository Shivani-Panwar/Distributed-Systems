package com.librarySystem.service;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.ws.WebServiceRef;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.rmi.Client;
import com.librarySystem.rmi.LibraryInterface;
import com.librarySystem.utility.Utilities;

/**
 * This class contains the methods that the clients wish to invoke on the server side.
 * @author Shivani
 * @version 1.0
 *
 */
public class RMIService implements LibraryService {

	private static Client client;

	static {
		client = new Client();
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID 
	 * @param itemID
	 * @param itemName
	 * @param quantity	- Quantity of the books that are to be added
	 * @return
	 */
	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity) {

		LibraryInterface library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.addItem(managerID, itemID, itemName, quantity);
			} catch (RemoteException e) {
		
				Utilities.errorLog(e.getMessage());
			}
		}

		return false;
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID
	 * @param itemID
	 * @param quantity - Quantity of the books that are to be removed
	 * @return
	 */
	public boolean removeItem(University university, String managerID, String itemID, int quantity) {

		LibraryInterface library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.removeItem(managerID, itemID, quantity);
			} catch (RemoteException e) {
				
				Utilities.errorLog(e.getMessage());
			}
		}

		return false;
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID
	 * @return
	 */
	public ArrayList<Item> listItemAvailability(University university, String managerID) {

		LibraryInterface library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				result = library.listItemAvailability(managerID);
			} catch (RemoteException e) {
				
				Utilities.errorLog(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param userID
	 * @param itemID
	 * @param numberOfDays
	 * @return
	 */
	public String borrowItem(University university, String userID, String itemID, int numberOfDays) {

		LibraryInterface library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.borrowItem(userID, itemID, numberOfDays).trim();
			} catch (RemoteException e) {
			
				Utilities.errorLog(e.getMessage());
			}
		}
		return "";
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param userID
	 * @param itemName
	 * @return
	 */
	public ArrayList<Item> findItem(University university, String userID, String itemName) {

		LibraryInterface library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				result = library.findItem(userID, itemName);
				System.out.println(result.size());
			} catch (RemoteException e) {
				
				Utilities.errorLog(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param userID
	 * @param itemID
	 * @return
	 */
	public boolean returnItem(University university, String userID, String itemID) {

		LibraryInterface library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.returnItem(userID, itemID);
			} catch (RemoteException e) {
			
				Utilities.errorLog(e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * This method is used when the user wants to be added to the wait queue for an item.
	 * @param university
	 * @param itemID
	 * @param userID
	 * @return
	 */
	public boolean addToQueue(University university, String itemID, String userID){
		
		LibraryInterface library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.addToQueue(itemID, userID);
			} catch (RemoteException e) {
			
				Utilities.errorLog(e.getMessage());
	
			}
		}
		return false;
	}
	
	public boolean exchangeItem(University university, String userID, String oldItem, String newItem){
		LibraryInterface library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.exchangeItem(userID, oldItem, newItem);
			} catch (RemoteException e) {
			
				Utilities.errorLog(e.getMessage());
			}
		}
		return false;
	}

}
