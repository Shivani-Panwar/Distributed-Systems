package com.librarySystem.service;

import java.util.ArrayList;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;

public interface LibraryService {
	
	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID 
	 * @param itemID
	 * @param itemName
	 * @param quantity	- Quantity of the books that are to be added
	 * @return
	 */
	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity);

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID
	 * @param itemID
	 * @param quantity - Quantity of the books that are to be removed
	 * @return
	 */
	public boolean removeItem(University university, String managerID, String itemID, int quantity);

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID
	 * @return
	 */
	public ArrayList<Item> listItemAvailability(University university, String managerID);

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param userID
	 * @param itemID
	 * @param numberOfDays
	 * @return
	 */
	public String borrowItem(University university, String userID, String itemID, int numberOfDays);

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param userID
	 * @param itemName
	 * @return
	 */
	public ArrayList<Item> findItem(University university, String userID, String itemName);

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param userID
	 * @param itemID
	 * @return
	 */
	public boolean returnItem(University university, String userID, String itemID);
	
	/**
	 * This method is used when the user wants to be added to the wait queue for an item.
	 * @param university
	 * @param itemID
	 * @param userID
	 * @return
	 */
	public boolean addToQueue(University university, String itemID, String userID);
	
	public boolean exchangeItem(University university, String userID, String oldItem, String newItem);


}
