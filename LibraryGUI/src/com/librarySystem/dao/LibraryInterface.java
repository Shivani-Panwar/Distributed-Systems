package com.librarySystem.dao;

import java.io.IOException;
import java.rmi.*;
import java.util.ArrayList;

import com.librarySystem.model.Item;

/**
 * This is a remote interface for the Library system.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public interface LibraryInterface extends Remote  {

	// Methods that the manager can invoke.

	/**
	 * This method is invoked by the appropriate library manager when he needs
	 * to add an item to the inventory.
	 * 
	 * @param managerID
	 *            -ID of the manager who is invoking the method.
	 * @param itemID
	 *            -ID of the item that is being added to the inventory.
	 * @param itemName
	 *            -Name of the item that is associated with the item ID.
	 * @param quantity
	 *            -The number of items that the manager wants to add to the
	 *            inventory.
	 * @return A message is returned to the manager stating the success or
	 *         failure of the action.
	 * @throws java.rmi.RemoteException
	 * @throws IOException
	 */
	public boolean addItem(String managerID, String itemID, String itemName, int quantity)
			throws java.rmi.RemoteException;

	/**
	 * This method is invoked by the library manager when he needs to remove the
	 * items from the inventory, the item can be completely deleted from the
	 * inventory or the number of items can be reduced.
	 * 
	 * @param managerID
	 *            -ID of the manager who is invoking the method.
	 * @param itemID
	 *            -ID of the item that is being removed from the inventory.
	 * @param quantity
	 *            -The number of items that the manager wants to add to the
	 *            inventory.
	 * @return A message is returned to the manager stating the success or
	 *         failure of the action.
	 * @throws java.rmi.RemoteException
	 * @throws IOException
	 */
	public boolean removeItem(String managerID, String itemID, int quantity)
			throws java.rmi.RemoteException;

	/**
	 * This method is invoked by the library manager when he needs the list of
	 * all the items available in his library.
	 * 
	 * @param managerID
	 *            -ID of the manager who is invoking the method.
	 * @return A list with the the Item ID, Item name and the quantity is
	 *         returned
	 * @throws java.rmi.RemoteException
	 * @throws IOException
	 */
	public ArrayList<Item> listItemAvailability(String managerID) throws java.rmi.RemoteException;

	// Methods that the user will invoke.

	/**
	 * This method is invoked by the user when he wants to borrow an item from
	 * the library. If the item is available in any of the libraries then the
	 * user can borrow it. If the item is not present in any of the library then
	 * the user is asked if he wants to be added to a waiting queue
	 * 
	 * @param userID
	 *            -ID of the user who is invoking the method.
	 * @param itemID
	 *            -ID of the item that is to be borrowed.
	 * @param numberOfDays
	 *            -The number of days for which the user wants to borrow the
	 *            book for.
	 * @return A message is returned to the manager stating the success or
	 *         failure of the action.
	 * @throws java.rmi.RemoteException
	 * @throws IOException
	 */
	public String borrowItem(String userID, String itemID, int numberOfDays)
			throws java.rmi.RemoteException;

	/**
	 * This method is invoked by the user on the appropriate server to find the
	 * number of items with the ID specified by the user. The server on which it
	 * is invoked communicates with the other 2 servers and gets the number of
	 * items for the item specified by the user.
	 * 
	 * @param userID
	 *            -ID of the user who is invoking the method.
	 * @param itemName
	 *            -Name of the item that the user wants to know the quantity of.
	 * @return A list of Item IDs along with the quantities.
	 * @throws java.rmi.RemoteException
	 * @throws IOException
	 */
	public ArrayList<Item> findItem(String userID, String itemName) throws java.rmi.RemoteException;

	/**
	 * This method is invoked by the user when he wants to return a book that he
	 * borrowed.
	 * 
	 * @param userID
	 *            -ID of the user who is invoking the method.
	 * @param itemID
	 *            -ID of the item that has to be returned.
	 * @return A message is returned to the manager stating the success or
	 *         failure of the action.
	 * @throws java.rmi.RemoteException
	 * @throws IOException
	 */
	public boolean returnItem(String userID, String itemID) throws java.rmi.RemoteException;
	
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
	public boolean addToQueue(String itemID, String userID) throws java.rmi.RemoteException;
}
