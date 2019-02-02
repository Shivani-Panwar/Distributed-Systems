package com.librarySystem.dao;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;

public class Client {

	public LibraryInterface getLibrary(University university) {
		Registry registry;
		LibraryInterface obj = null;
		String registryURL = university.getCode();
		try {
			registry = LocateRegistry.getRegistry(Constants.PORT);
			obj = (LibraryInterface) registry.lookup(registryURL);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
