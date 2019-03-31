package com.librarySystem.soap;

import javax.jws.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jws.WebMethod;

import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

@WebService
public class SOAPService {
	
	private static LibraryInterface library;
	
	static{
		try {
			library = new LibraryImpl();
		} catch (RemoteException e) {
			Utilities.errorLog(e.toString());
		}
	}
	

	
	@WebMethod
	public boolean addItem(String managerID, String itemID, String itemName, int quantity) {
		try {
			return library.addItem(managerID, itemID, itemName, quantity);
		} catch (RemoteException e) {
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public boolean removeItem(String managerID, String itemID, int quantity) {
		// TODO Auto-generated method stub
		try{
			return library.removeItem(managerID, itemID, quantity);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public String listItemAvailability(String managerID) {
		String returnString = null;
		try {
			ArrayList<Item> items = library.listItemAvailability(managerID);
			returnString = Utilities.getReplyStringFromList(items);
		} catch (RemoteException e) {
			Utilities.errorLog(e.toString());
		}
		return returnString;
	}

	//@Override
	@WebMethod
	public String borrowItem(String userID, String itemID, int numberOfDays) {
		try{
			return library.borrowItem(userID, itemID, numberOfDays);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return null;
	}

	//@Override
	@WebMethod
	public String findItem(String userID, String itemName) {
		String returnString = null;
		try{
			ArrayList<Item> items=library.findItem(userID, itemName);
			returnString = Utilities.getReplyStringFromList(items);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return returnString;
	}

	//@Override
	@WebMethod
	public boolean returnItem(String userID, String itemID) {
		try{
			return library.returnItem(userID, itemID);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public boolean addToQueue(String itemID, String userID) {
		try{
			return library.addToQueue(itemID, userID);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public boolean exchangeItem(String userID, String oldItem, String newItem) {
		try{
			return library.exchangeItem(userID, oldItem, newItem);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public boolean checkAvailability(String itemID) {
		try{
			return library.checkAvailability(itemID);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public boolean checkBorrowPossible(String userID, String itemID) {
		try{
			return library.checkBorrowPossible(userID, itemID);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

	//@Override
	@WebMethod
	public boolean checkReturnPossible(String userID, String itemID) {
		try{
			return library.checkReturnPossible(userID, itemID);
		}catch(RemoteException e){
			Utilities.errorLog(e.toString());
		}
		return false;
	}

}
