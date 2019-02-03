package com.librarySystem.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

/**
 * This class implements the remote interface LibraryInterface
 * 
 * @author Shivani
 *
 */
public class LibraryImpl extends UnicastRemoteObject implements LibraryInterface {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private CopyOnWriteArraySet<String> ClientList;

	public LibraryImpl() throws RemoteException {
		super();
		ClientList = new CopyOnWriteArraySet<String>();
	}

	private static HashMap<String, Item> map;
	private static HashMap<String, ArrayList<String>> BorrowList;
	private static HashMap<String, ArrayList<String>> WaitQueue;

	@Override
	public synchronized boolean addItem(String managerID, String itemID, String itemName, int quantity) {

		Item item = null;
		boolean result = false;

		// When the item is already present in the inventory, increase its
		// quantity
		if (map == null) {
			item = new Item();
			map = new HashMap<>();
			item.setID(itemID);
			item.setName(itemName);
			item.setQuantity(quantity);
			map.put(itemID, item);
			result = true;
		} else if (map.containsKey(itemID)) {
			item = map.get(itemID);
			item.setQuantity(item.getQuantity() + quantity);
			map.put(itemID, item);
			result = true;
		}
		// When the item is not present in the inventory, add an entry in the
		// HashMap
		else {
			item = new Item();
			item.setID(itemID);
			item.setName(itemName);
			item.setQuantity(quantity);
			map.put(itemID, item);
			result = true;
		}
		// Log file generation
		if (result == true) {
			Utilities.serverLog(University.CONCORDIA.getCode(), "Addition of Item", managerID,
					"The item has been added to the inventory successfully!!");
			Utilities.clientLog(managerID, "Addition of Item",
					"The item has been added to the inventory successfully!!");
		} else {
			Utilities.serverLog(University.CONCORDIA.getCode(), "Addition of Item", managerID,
					"The item cannot be added to the inventory!!");
			Utilities.clientLog(managerID, "Addition of Item", "The item cannot be added to the inventory!!");
		}

		return result;

	}

	@Override
	public synchronized boolean removeItem(String managerID, String itemID, int quantity) {
		// HashMap<String, Item> map = new HashMap<String, Item>();

		boolean result = false;

		// When the manager wants to reduce the quantity of the item
		if (map != null && map.containsKey(itemID) && quantity != 0 && map.get(itemID).getQuantity() >= quantity) {
			Item item = map.get(itemID);
			item.setQuantity(item.getQuantity() - quantity);
			map.put(itemID, item);

			result = true;
		}
		// When the manager wants to delete the item completely
		else if (map.containsKey(itemID) && quantity == 0) {
			map.remove(itemID);
			BorrowList.remove(itemID);
			WaitQueue.remove(itemID);
			result = true;
		}
		// When the item ID is not found in the inventory
		else {
			result = false;
		}
		// Log file generation
		if (result == true) {
			Utilities.clientLog(managerID, "Removal of Item",
					"The item has been successfully removed from the inventory!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Removal of Item", managerID,
					"The item has been successfully removed from the inventory!!");
		} else {
			Utilities.clientLog(managerID, "Removal of Item", "The item could not be removed from the inventory!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Removal of Item", managerID,
					"The item could not be removed from the inventory!!");
		}
		return result;
	}

	@Override
	public synchronized ArrayList<Item> listItemAvailability(String managerID) {
		// When the manager wants to list out all the items in his library
		ArrayList<Item> ResultList = new ArrayList<>();
		HashMap<String, Item> entry = new HashMap<>();

		for (String key : entry.keySet()) {
			ResultList.add(entry.get(key));
		}
		// Log file generation
		Utilities.clientLog(managerID, "Requested List of Items", "The Items have been listed successfully");
		Utilities.serverLog(University.CONCORDIA.getCode(), "Requested List of Items", managerID,
				"The Items have been listed successfully");

		return ResultList;
	}

	@Override
	public synchronized boolean borrowItem(String userID, String itemID, int numberOfDays) {

		boolean result = false;
		boolean alreadyborrowed=false;
		// Check if external client has already borrowed an item
		if (!Utilities.CodeCheck(userID, false, itemID.substring(0, 3), false)) {
			if (ClientList.contains(userID)) {
				alreadyborrowed= true;
			}else {
				alreadyborrowed = false;
			} 
		}
				if (map != null && map.containsKey(itemID) && alreadyborrowed==false) {
					Item item = map.get(itemID);
					if (item.getQuantity() != 0) {
						item.setQuantity(item.getQuantity() - 1);
						map.put(itemID, item);
						result = true;

						// Add item and user to the list of borrowed items
						ArrayList<String> borrowDetails = null;
						// Check if the list of borrowed items is empty or not.
						if (BorrowList == null) {

							borrowDetails = new ArrayList<>();
							BorrowList = new HashMap<>();
							borrowDetails.add(userID);

							BorrowList.put(itemID, borrowDetails);

						}
						// Check if the list of borrowed items has the item ID
						// already
						else if (BorrowList.containsKey(itemID)) {

							borrowDetails = BorrowList.get(itemID);
							borrowDetails.add(userID);
							BorrowList.put(itemID, borrowDetails);

						}
						// Add the item in the list if the list is not empty
						else {

							borrowDetails = new ArrayList<>();
							borrowDetails.add(userID);

							BorrowList.put(itemID, borrowDetails);
						}
					}

				}
			

				/*
				 * if (map != null && map.containsKey(itemID)) { Item item =
				 * map.get(itemID); if (item.getQuantity() != 0) {
				 * item.setQuantity(item.getQuantity() - 1); map.put(itemID,
				 * item); result = true;
				 * 
				 * //Add item and user to the list of borrowed items
				 * ArrayList<String> borrowDetails = null; // Check if the list
				 * of borrowed items is empty or not. if (BorrowList == null) {
				 * 
				 * borrowDetails = new ArrayList<>(); BorrowList = new
				 * HashMap<>(); borrowDetails.add(userID);
				 * 
				 * BorrowList.put(itemID, borrowDetails);
				 * 
				 * } //Check if the list of borrowed items has the item ID
				 * already else if (BorrowList.containsKey(itemID)) {
				 * 
				 * borrowDetails = BorrowList.get(itemID);
				 * borrowDetails.add(userID); BorrowList.put(itemID,
				 * borrowDetails);
				 * 
				 * } //Add the item in the list if the list is not empty else {
				 * 
				 * borrowDetails = new ArrayList<>(); borrowDetails.add(userID);
				 * 
				 * BorrowList.put(itemID, borrowDetails); }
				 */
			

		// Log file generation
		if (result == true) {
			Utilities.clientLog(userID, "Borrwing an Item", "Item borrowed Successfully!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Borrwing an Item", userID,
					"Item borrowed Successfully!!");
		} else {
			Utilities.clientLog(userID, "Borrwing an Item", "Item could not be borrowed!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Borrwing an Item", userID,
					"Item could not be borrowed!!");
		}
		return result;
	}

	@Override
	public synchronized ArrayList<Item> findItem(String userID, String itemName) {
		// If the item name matches the one entered by the user then it is added
		// to the array list
		ArrayList<Item> ResultList = new ArrayList<>();

		for (Entry<String, Item> value : map.entrySet()) {
			if (value.getValue().getName().equals(itemName)) {
				ResultList.add(value.getValue());
			}
		}

		// Log file generation
		if (ResultList.size() != 0) {
			
				if (ClientList.contains(userID)) {
					ClientList.remove(userID);
				}
			Utilities.clientLog(userID, "Finding an item", "Items listed successfully!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Finding an item", userID,
					"Items listed successfully!!");
		} else {
			Utilities.clientLog(userID, "Finding an item", "No items with the given name found!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Finding an item", userID,
					"No items with the given name found!!");
		}
		return ResultList;
	}

	@Override
	public boolean returnItem(String userID, String itemID) {
		// When the user wants to return a book the quantity is increased by 1.
		ArrayList<String> userList = BorrowList.get(itemID);
		int i = 0;
		boolean flag = false;
		while (i < userList.size()) {
			if (userList.get(i) == userID) {
				flag = true;
				break;
			}
		}

		// When the user wants to return a book the quantity is increased by 1.
		boolean result = false;
		if (flag == true) {
			if (map != null && map.containsKey(itemID)) {
				Item item = map.get(itemID);
				item.setQuantity(item.getQuantity() + 1);
				map.put(itemID, item);
				result = true;
			} else {
				result = false;
			}
		}
		// Log file generation
		if (result == true) 
		{			
			if(ClientList.contains(userID)){
			ClientList.remove(userID);
			}
			Utilities.clientLog(userID, "Returning an Item", "The item was successfully returned!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Returning an Item", userID,
					"The item was successfully returned!!");
		} else {
			Utilities.clientLog(userID, "Returning an Item", "The item cannot be returned!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Returning an Item", userID,
					"The item cannot be returned!!");
		}
		return result;
	}

	/**
	 * This method is used if the user is not able to borrow the item an then
	 * wants to be added to the wait list.
	 * 
	 * @param itemID
	 *            - ID of the item that the user wants to borrow.
	 * @param user
	 *            - ID of the user that wants to borrow the item.
	 * @return
	 * @throws IOException
	 */
	public boolean addToQueue(String itemID, String userID) {

		ArrayList<String> usersWaiting = null;
		boolean result = false;
		// Check if the waiting list is empty or not.
		if (WaitQueue == null) {
			usersWaiting = new ArrayList<>();
			WaitQueue = new HashMap<>();
			usersWaiting.add(userID);

			WaitQueue.put(itemID, usersWaiting);

			result = true;
		}
		// Check if the item ID already exists in the queue
		else if (WaitQueue.containsKey(itemID)) {
			usersWaiting = WaitQueue.get(itemID);
			usersWaiting.add(userID);
			WaitQueue.put(itemID, usersWaiting);
			result = true;
		}
		// Add the item in the list if the list is not empty
		else {

			usersWaiting = new ArrayList<>();
			usersWaiting.add(userID);

			WaitQueue.put(itemID, usersWaiting);

			result = true;
		}

		// Log file generation
		if (result == true) {
			Utilities.clientLog(userID, "Wait queue", "The user was successfully added to the wait queue!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Wait queue", userID,
					"The user was successfully added to the wait queue!!");
		} else {
			Utilities.clientLog(userID, "Wait queue", "The user cannot be added to the wait queue!!");
			Utilities.serverLog(University.CONCORDIA.getCode(), "Wait queue", userID,
					"The user cannot be added to the wait queue!!");
		}
		return result;
	}

}
