package com.librarySystem.service;

import java.util.ArrayList;

import com.librarySystem.constant.University;
import com.librarySystem.corbaDAO.Client;
import com.librarySystem.corbaDAO.Library;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

/**
 * This class contains the methods that the clients wish to invoke on the server side.
 * @author Shivani
 * @version 1.0
 *
 */
public class CrobaService {

	private static Client client;

	static {
		client = new Client(new String[2]);
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

		Library library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.addItem(managerID, itemID, itemName, quantity);
			} catch (Exception e) {
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

		Library library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.removeItem(managerID, itemID, quantity);
			} catch (Exception e) {
				
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

		Library library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				String reply = library.listItemAvailability(managerID);
				result = Utilities.getItemsFromReply(reply);
			} catch (Exception e) {
				
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

		Library library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.borrowItem(userID, itemID, numberOfDays).trim();
			} catch (Exception e) {
			
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

		Library library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				String reply = library.findItem(userID, itemName);
				result = Utilities.getItemsFromReply(reply);
				System.out.println(result.size());
			} catch (Exception e) {
				
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

		Library library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.returnItem(userID, itemID);
			} catch (Exception e) {
			
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
		
		Library library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.addToQueue(itemID, userID);
			} catch (Exception e) {
				Utilities.errorLog(e.getMessage());
	
			}
		}
		return false;
	}
	
	
	public boolean exchangeItem(University university, String userID, String oldItem, String newItem){
		Library library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.exchangeItem(userID, oldItem, newItem);
			} catch (Exception e) {
			
				Utilities.errorLog(e.getMessage());
			}
		}
		return false;
	}

}
