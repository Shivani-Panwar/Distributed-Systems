package com.librarySystem.corba;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.omg.CORBA.ORB;

import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

public class CorbaToLibraryInterface extends LibraryPOA {
	
	private static LibraryInterface library;
	
	private ORB orb;
	
	static{
		try {
			library = new LibraryImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void setORB(ORB orb_val) {
	   orb = orb_val; 
	}

	@Override
	public boolean addItem(String managerID, String itemID, String itemName, int quantity) {
		
		try {
			return library.addItem(managerID, itemID, itemName, quantity);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeItem(String managerID, String itemID, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String listItemAvailability(String managerID) {
		String returnString = null;
		try {
			ArrayList<Item> items = library.listItemAvailability(managerID);
			returnString = Utilities.getReplyStringFromList(items);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnString;
	}

	@Override
	public String borrowItem(String userID, String itemID, int numberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findItem(String userID, String itemName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean returnItem(String userID, String itemID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addToQueue(String itemID, String userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exchangeItem(String userID, String oldItem, String newItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkAvailability(String itemID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkBorrowPossible(String userID, String itemID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkReturnPossible(String userID, String itemID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
