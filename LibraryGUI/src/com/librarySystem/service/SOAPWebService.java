package com.librarySystem.service;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.ws.WebServiceRef;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.soap.Client;
import com.librarySystem.soap.SOAPService;
import com.librarySystem.rmi.LibraryInterface;
import com.librarySystem.utility.Utilities;

@WebServiceRef(wsdlLocation="com.librarySystem.soap")
public class SOAPWebService implements LibraryService {

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
	 * @throws MalformedURLException 
	 */
	@Override
	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity) {

		
			try {
				SOAPService library = client.getLibrary(university);
				if (library != null) {
					
				return library.addItem(managerID, itemID, itemName, quantity);
			} 
		}catch (MalformedURLException e) {
		
				Utilities.errorLog(e.getMessage());
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
	@Override
	public boolean removeItem(University university, String managerID, String itemID, int quantity) {

		SOAPService library;
		try {
			library = client.getLibrary(university);

		if (library != null){
				return library.removeItem(managerID, itemID, quantity);
			} 
		}catch (MalformedURLException e) {
				
				Utilities.errorLog(e.getMessage());
			}

		return false;
	}

	/**
	 * This method is used to pass all the relevant data to the Client class which uses the data to invoke the remote method.
	 * @param university - The university to which the user belongs
	 * @param managerID
	 * @return
	 */
	@Override
	public ArrayList<Item> listItemAvailability(University university, String managerID) {
		ArrayList<Item> result = null;
		
		try {
		SOAPService library = client.getLibrary(university);
		if (library != null) {
			
			result = library.listItemAvailability(managerID);
		}
		} catch (MalformedURLException e) {
				
				Utilities.errorLog(e.getMessage());
			
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
	@Override
	public String borrowItem(University university, String userID, String itemID, int numberOfDays) {
		try {
			
		SOAPService library = client.getLibrary(university);
		if (library != null) {
				return library.borrowItem(userID, itemID, numberOfDays).trim();
			} 
		}catch (Exception e) {
			
				Utilities.errorLog(e.getMessage());
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
	@Override
	public ArrayList<Item> findItem(University university, String userID, String itemName) {

		ArrayList<Item> result = null;
			try {
				SOAPService library = client.getLibrary(university);
				if (library != null) {
					System.out.println("ANDAR YAHA");
				result = library.findItem(userID, itemName);
			}
				} catch (MalformedURLException e) {
				
				Utilities.errorLog(e.getMessage());
			
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
	@Override
	public boolean returnItem(University university, String userID, String itemID) {
		try {
			
		SOAPService library = client.getLibrary(university);
		if (library != null) {
				return library.returnItem(userID, itemID);
			}
		}catch (Exception e) {
			
				Utilities.errorLog(e.getMessage());
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
	@Override
	public boolean addToQueue(University university, String itemID, String userID){
		try {
			
		SOAPService library = client.getLibrary(university);
		if (library != null) {
				return library.addToQueue(itemID, userID);
			}
		}catch (Exception e) {
			
				Utilities.errorLog(e.getMessage());
	
			
		}
		return false;
	}
	
	@Override
	public boolean exchangeItem(University university, String userID, String oldItem, String newItem){
		try {
			
		SOAPService library = client.getLibrary(university);
		if (library != null) {
				return library.exchangeItem(userID, oldItem, newItem);
			}
		}catch (Exception e) {
			
				Utilities.errorLog(e.getMessage());
			}
		
		return false;
	}
}
