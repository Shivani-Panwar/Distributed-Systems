package com.librarySystem.service;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.librarySystem.constant.University;
import com.librarySystem.dao.Client;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.model.Item;

public class RMIService {

	private static Client client;

	static {
		client = new Client();
	}

	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity) {

		LibraryInterface library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.addItem(managerID, itemID, itemName, quantity);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean removeItem(University university, String managerID, String itemID, int quantity) {

		LibraryInterface library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.removeItem(managerID, itemID, quantity);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public ArrayList<Item> listItemAvailability(University university, String managerID) {

		LibraryInterface library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				result = library.listItemAvailability(managerID);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean borrowItem(University university, String userID, String itemID, int numberOfDays) {

		LibraryInterface library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.borrowItem(userID, itemID, numberOfDays);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Item> findItem(University university, String userID, String itemName) {

		LibraryInterface library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				result = library.findItem(userID, itemName);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean returnItem(University university, String userID, String itemID) {

		LibraryInterface library = client.getLibrary(university);
		if (library != null) {
			try {
				return library.returnItem(userID, itemID);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
