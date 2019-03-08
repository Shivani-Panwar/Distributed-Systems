package com.librarySystem.dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.librarySystem.constant.Constants;
import com.librarySystem.model.Item;
import com.librarySystem.udp.Client;
import com.librarySystem.utility.Utilities;

/**
 * This class implements the remote interface LibraryInterface
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class LibraryImpl extends UnicastRemoteObject implements LibraryInterface {

	private static final long serialVersionUID = 1L;

	private static CopyOnWriteArraySet<String> ClientList;

	public LibraryImpl() throws RemoteException {
		super();
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
		if (Utilities.getUniversity(itemID).getCode().equals(Constants.UNIVERSITY.getCode()) && itemID.length() == 7
				&& quantity >= 0) {
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
				item.setName(itemName);
				item.setQuantity(item.getQuantity() + quantity);
				map.put(itemID, item);
				result = true;
			}
			// When the item is not present in the inventory, add an entry in
			// the
			// HashMap
			else {
				item = new Item();
				item.setID(itemID);
				item.setName(itemName);
				item.setQuantity(quantity);
				map.put(itemID, item);
				result = true;
			}
		} else {
			result = false;
		}
		if (result == true) {

			// Automatically assign the item to the users in waiting queue
			if (WaitQueue != null && WaitQueue.containsKey(itemID)) {
						//Checking if the wait queue is empty and if it contains the itemID
				int count = 0;
				int itemsgiven = 0;
				ArrayList<String> borrowDetails = null;
				ArrayList<String> userList = WaitQueue.get(itemID);
				Item itemBorrow = map.get(itemID);
				if (BorrowList == null) {
					borrowDetails = new ArrayList<>();
					BorrowList = new HashMap<>();
				} else if (BorrowList.containsKey(itemID)) {
					borrowDetails = BorrowList.get(itemID);
				} else {
					borrowDetails = new ArrayList<>();
				}
				count = (userList.size() < itemBorrow.getQuantity() ? userList.size() : itemBorrow.getQuantity());
				// Add user to borrow list only when the user is not external or
				// when the user is external but has not borrowed
				// any item from the server otherwise skip user in wait list
				for (int i = 0; i < count; i++) {
					// If 10 items of an item ID are added and 5 users are in
					// the waiting list for it,
					// then the item will be given to 5 users and its quantity
					// in map will be reduced accordingly
					int j = 0;
					if (ClientList == null || ( ClientList != null && !ClientList.contains(userList.get(j)))) {
						borrowDetails.add(userList.get(j));
						// Update logs for servers and clients with the
						// successful borrow message.
						Utilities.clientLog(userList.get(j), "Borrwing an Item", "User removed from waitList and item borrowed successfully!!");
						Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Borrwing an Item", userList.get(j),
								"Item borrowed Successfully!!");
						if (!Utilities.getUniversity(userList.get(j)).equals(Utilities.getUniversity(itemID))) {
							if(ClientList == null)
								ClientList = new CopyOnWriteArraySet<>();
							ClientList.add(userList.get(j));
						}
						userList.remove(j);
						itemsgiven++;
					} else {
						j++;
					}
				}
				BorrowList.put(itemID, borrowDetails);
				WaitQueue.put(itemID, userList);
				// Update the Hashmap with the quantity after serving the
				// waitlist
				itemBorrow.setQuantity(itemBorrow.getQuantity() - itemsgiven);
				map.put(itemID, itemBorrow);
			}
			// Log file generation
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Addition of Item", managerID,
					"The item has been added to the inventory successfully!!");
			Utilities.clientLog(managerID, "Addition of Item",
					"The item has been added to the inventory successfully!!");
		} else {
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Addition of Item", managerID,
					"The item cannot be added to the inventory!!");
			Utilities.clientLog(managerID, "Addition of Item", "The item cannot be added to the inventory!!");
		}
		return result;
	}

	@Override
	public synchronized boolean removeItem(String managerID, String itemID, int quantity) {

		boolean result = false;

		// When the manager wants to reduce the quantity of the item
		if (map != null && map.containsKey(itemID) && quantity != 0 && map.get(itemID).getQuantity() >= quantity
				&& quantity >= 0) {
			Item item = map.get(itemID);
			item.setQuantity(item.getQuantity() - quantity);
			map.put(itemID, item);

			result = true;
		}
		// When the manager wants to delete the item completely
		else if (map.containsKey(itemID) && quantity == 0) {
			map.remove(itemID);
			if (BorrowList != null && BorrowList.containsKey(itemID)) {
				if(ClientList!=null){
					for(int i=0;i<BorrowList.get(itemID).size();i++){
						if(ClientList.contains(BorrowList.get(itemID).get(i))){
							ClientList.remove(BorrowList.get(itemID).get(i));
						}
					}
				}
				BorrowList.remove(itemID);
			}
			if (WaitQueue != null && WaitQueue.containsKey(itemID)) {
				WaitQueue.remove(itemID);
			}
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
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Removal of Item", managerID,
					"The item has been successfully removed from the inventory!!");
		} else {
			Utilities.clientLog(managerID, "Removal of Item", "The item could not be removed from the inventory!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Removal of Item", managerID,
					"The item could not be removed from the inventory!!");
		}
		return result;
	}

	@Override
	public synchronized ArrayList<Item> listItemAvailability(String managerID) {
		// When the manager wants to list out all the items in his library
		ArrayList<Item> ResultList = new ArrayList<>();
		Set<String> keys = map.keySet();

		for (String key : keys) {
			ResultList.add(map.get(key));
		}
		// Log file generation
		Utilities.clientLog(managerID, "Requested List of Items", "The Items have been listed successfully");
		Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Requested List of Items", managerID,
				"The Items have been listed successfully");

		return ResultList;
	}

	@Override
	public synchronized String borrowItem(String userID, String itemID, int numberOfDays) {

		String result = null;
		ArrayList<String> userList = null;
		if (BorrowList != null) {
			userList = BorrowList.get(itemID);
		}
		if (numberOfDays < 1) {
			return Constants.BORROW_FAIL_NO_OF_DAYS;
		}
		// Check if the request is for an item in the user's library
		if (Utilities.getUniversity(itemID).equals(Constants.UNIVERSITY)) {
			// Check if user has already borrowed the item
			if (userList != null && userList.contains(userID)) {
				Utilities.clientLog(userID, "Borrwing an Item", "Item could not be borrowed!!");
				Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Borrwing an Item", userID,
						"Item could not be borrowed!!");
				return Constants.BORROW_FAIL_ALREADY_BORROWED;
			}

			// Check if external client has already borrowed an item
			if (!Utilities.getUniversity(userID).equals(Constants.UNIVERSITY) && ClientList != null && ClientList.contains(userID)) {
					Utilities.clientLog(userID, "Borrwing an Item", "Item could not be borrowed!!");
					Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Borrwing an Item", userID,
							"Item could not be borrowed!!");
					return Constants.BORROW_FAIL_ALREADY_BORROWED;
			}

			if (map != null && map.containsKey(itemID)) {
				Item item = map.get(itemID);

				if (item.getQuantity() != 0) {

					// Check if user is from same library
					if (Utilities.getUniversity(userID).equals(Constants.UNIVERSITY.getCode())) {
						result = Constants.BORROWED_FROM_OWN;
					} else {
						result = Constants.BORROWED_FROM_OTHER;
					}

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
					item.setQuantity(item.getQuantity() - 1);
					map.put(itemID, item);
				}
				// When the quantity of the item is zero
				else {
					result = Constants.BORROW_FAIL_OWN;
				}
			} else {
				result = Constants.BORROW_FAIL_ITEM_NOT_FOUND;
			}
		}
		// When the user requests an item from another library
		else {
			Client borrowitem = new Client();
			result = borrowitem.borrowItemFromRemoteLibrary(userID, itemID, numberOfDays);
		}

		// Log file generation
		if (result != null
				&& (result.equals(Constants.BORROWED_FROM_OTHER) || result.equals(Constants.BORROWED_FROM_OWN))) {

			if (!Utilities.getUniversity(userID).equals(Constants.UNIVERSITY)) {
				Utilities.clientLog(userID, "Addition of Item",
						"THE USER IS ADDED TO THE CLIENT LIST!!");
				if(ClientList == null)
					ClientList = new CopyOnWriteArraySet<>();
				ClientList.add(userID);
			}
			Utilities.clientLog(userID, "Borrwing an Item", "Item borrowed Successfully!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Borrwing an Item", userID,
					"Item borrowed Successfully!!");
		} else {
			Utilities.clientLog(userID, "Borrwing an Item", "Item could not be borrowed!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Borrwing an Item", userID,
					"Item could not be borrowed!!");
		}
		return result;
	}

	@Override
	public synchronized ArrayList<Item> findItem(String userID, String itemName) {
		// If the item name matches the one entered by the user then it is added
		// to the array list
		ArrayList<Item> ResultList = new ArrayList<>();
		if (map != null) {
			for (Entry<String, Item> value : map.entrySet()) {
				if (value.getValue().getName().equals(itemName)) {
					ResultList.add(value.getValue());
				}
			}
		}

		if (Utilities.getUniversity(userID).getCode().equals(Constants.UNIVERSITY.getCode())) {
			ArrayList<Item> items = new ArrayList<>();
			Client findinothers = new Client();
			items = findinothers.findItemsOnRemoteLibraries(userID, itemName);
			if (items != null) {
				ResultList.addAll(items);
			}
		}
		// Log file generation
		if (ResultList.size() != 0) {
			System.out.println(ResultList.size());
			Utilities.clientLog(userID, "Finding an item", "Items listed successfully!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Finding an item", userID,
					"Items listed successfully!!");
		} else {
			Utilities.clientLog(userID, "Finding an item", "No items with the given name found!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Finding an item", userID,
					"No items with the given name found!!");
		}
		return ResultList;
	}

	@Override
	public boolean returnItem(String userID, String itemID) {
		ArrayList<String> userList = null;
		boolean result = false;
		if (Utilities.getUniversity(itemID).getCode().equals(Constants.UNIVERSITY.getCode())) {
			// When the user wants to return a book the quantity is increased by
			// 1.
			if (BorrowList != null) {
				userList = BorrowList.get(itemID);
			}
			if (userList != null && userList.contains(userID)) {
				if (map != null && map.containsKey(itemID)) {
					Item item = map.get(itemID);
					item.setQuantity(item.getQuantity() + 1);
					map.put(itemID, item);
					result = true;
					 // Checking if the wait queue is empty and if it contains the item ID
					if (WaitQueue != null && WaitQueue.containsKey(itemID)) {
						ArrayList<String> borrowDetails = null;
						ArrayList<String> waitList = WaitQueue.get(itemID);
						Item itemBorrow = map.get(itemID);
						if (BorrowList == null) {
							borrowDetails = new ArrayList<>();
							BorrowList = new HashMap<>();
						} else if (BorrowList.containsKey(itemID)) {
							borrowDetails = BorrowList.get(itemID);
						} else {
							borrowDetails = new ArrayList<>();
						}
						// Add user to borrow list only when the user is not external or
						// when the user is external but has not borrowed
						// any item from the server otherwise skip user in wait list
						for (int i = 0; i < waitList.size(); i++) {
							// If 10 items of an item ID are added and 5 users are in
							// the waiting list for it,
							// then the item will be given to 5 users and its quantity
							// in map will be reduced accordingly
							int j=0;
							if (ClientList == null || (ClientList != null && !ClientList.contains(waitList.get(j)))) {
								borrowDetails.add(waitList.get(j));
								// Update logs for servers and clients with the
								// successful borrow message.
								Utilities.clientLog(waitList.get(j), "Borrwing an Item", "User removed from waitList and item borrowed successfully!!");
								Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Borrwing an Item", waitList.get(j),
										"Item borrowed Successfully!!");
								if(!Utilities.getUniversity(waitList.get(j)).equals(Constants.UNIVERSITY)){
									if(ClientList == null)
										ClientList = new CopyOnWriteArraySet<>();
									ClientList.add(waitList.get(j));
								}
								waitList.remove(j);
								BorrowList.put(itemID, borrowDetails);
								WaitQueue.put(itemID, waitList);
								// Update the Hashmap with the quantity after serving the
								// waitlist
								itemBorrow.setQuantity(item.getQuantity() - 1);
								map.put(itemID, itemBorrow);
								break;
							} else {
								i++;
							}
						}
					}
				} else {
					result = false;
				}
			}
		} else {
			Client returnitem = new Client();
			result = returnitem.returnItemToRemoteLibrary(userID, itemID);
		}
		// Log file generation
		if (result == true) {
			if (ClientList != null && ClientList.contains(userID)) {
				ClientList.remove(userID);
			}
			// Update the borrow list if the item was of this library
			if (Utilities.getUniversity(itemID).getCode().equals(Constants.UNIVERSITY.getCode())) {
				userList.remove(userID);
				BorrowList.put(itemID, userList);
			}
			Utilities.clientLog(userID, "Returning an Item", "The item was successfully returned!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Returning an Item", userID,
					"The item was successfully returned!!");
		} else {
			Utilities.clientLog(userID, "Returning an Item", "The item cannot be returned!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Returning an Item", userID,
					"The item cannot be returned!!");
		}
		return result;
	}

	@Override
	public boolean addToQueue(String itemID, String userID) {

		ArrayList<String> usersWaiting = null;
		boolean result = false;
		// Check if the waiting list is empty or not.
		if(Constants.UNIVERSITY.equals(Utilities.getUniversity(itemID))){
		if (WaitQueue == null && (ClientList == null || (ClientList != null && !ClientList.contains(userID)))) {
			usersWaiting = new ArrayList<>();
			WaitQueue = new HashMap<>();
			usersWaiting.add(userID);

			WaitQueue.put(itemID, usersWaiting);

			result = true;
		}
		// Check if the item ID already exists in the queue
		else if (WaitQueue.containsKey(itemID) && (ClientList == null || (ClientList != null && !ClientList.contains(userID)))) {
			usersWaiting = WaitQueue.get(itemID);
			if(!usersWaiting.contains(userID)){
			usersWaiting.add(userID);
			WaitQueue.put(itemID, usersWaiting);
			result = true;
			}else{
			result = false;
			}
		}
		// Add the item in the list if the list is not empty
		else if (WaitQueue != null && !WaitQueue.containsKey(itemID) && (ClientList == null || (ClientList != null && !ClientList.contains(userID)))) {

			usersWaiting = new ArrayList<>();
			usersWaiting.add(userID);

			WaitQueue.put(itemID, usersWaiting);

			result = true;
		}
		}else{
			Client addToQueue = new Client();
			result = addToQueue.addItemtoQueueToRemoteLibrary(userID, itemID);
		}
		

		// Log file generation
		if (result == true) {
			Utilities.clientLog(userID, "Wait queue", "The user was successfully added to the wait queue!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Wait queue", userID,
					"The user was successfully added to the wait queue!!");
		} else {
			Utilities.clientLog(userID, "Wait queue", "The user cannot be added to the wait queue!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Wait queue", userID,
					"The user cannot be added to the wait queue!!");
		}
		return result;
	}
	
	@Override
	public boolean exchangeItem(String userID, String oldItem, String newItem){
		boolean result=false;
		if(checkAvailability(newItem) && checkBorrowPossible(userID, newItem) && checkReturnPossible(userID, oldItem)){
			int days=1;
			returnItem(userID,oldItem);
			borrowItem(userID,newItem,days);
			result=true;
		}
		
		if (result == true) {	
			Utilities.clientLog(userID, "Returning an Item", "The item was successfully exchanged!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Returning an Item", userID,
					"The item was successfully exchanged!!");
		} else {
			Utilities.clientLog(userID, "Returning an Item", "The item cannot be exchanged!!");
			Utilities.serverLog(Constants.UNIVERSITY.getCode(), "Returning an Item", userID,
					"The item cannot be exchanged!!");
		}
		
	return result;
	}
	
	@Override
	public boolean checkAvailability(String itemID){
		boolean result=false;
		if(Utilities.getUniversity(itemID).getCode().equals(Constants.UNIVERSITY.getCode())){
		if(map!=null && map.get(itemID).getQuantity()>0){
		result= true;
		}
		}else{
			Client checkAvailability = new Client();
			result= checkAvailability.checkIfAvailable(itemID);
		}
		return result;
	}
	
	@Override
	public boolean checkBorrowPossible(String userID,String itemID){
		boolean result = false;
		ArrayList<String> userList = null;
		if (BorrowList != null) {
			userList = BorrowList.get(itemID);
		}
		// Check if the request is for an item in the user's library
		if (Utilities.getUniversity(itemID).equals(Constants.UNIVERSITY)) {
			// Check if user has already borrowed the item
			if (userList != null && userList.contains(userID)) {
				result=true;
			}
			
			if (map != null && map.containsKey(itemID) && result!=true) {
				Item item = map.get(itemID);

				if (item.getQuantity() != 0) {
				    result = true;
				}
				// When the quantity of the item is zero
				else {
					result = false;
				}
			} else {
				result = false;
			}
		}
		// When the user requests an item from another library
		else {
			Client borrowitem = new Client();
			result = borrowitem.canBorrow(userID, itemID);
		}
		return result;
	}
	
	@Override
	public boolean checkReturnPossible(String userID,String itemID){
		boolean result=false;
		ArrayList<String> userList = null;
		if (Utilities.getUniversity(itemID).getCode().equals(Constants.UNIVERSITY.getCode())) {
			if (BorrowList != null) {
				userList = BorrowList.get(itemID);
			}
			if (userList != null && userList.contains(userID)) {
			result= true;
		}
		}else {
			Client returnitem = new Client();
			result = returnitem.canReturn(userID, itemID);
		}
		return result;
	}

	@Override
	protected void finalize() {
		Utilities.unLoadLibrary(this);
	}
}
