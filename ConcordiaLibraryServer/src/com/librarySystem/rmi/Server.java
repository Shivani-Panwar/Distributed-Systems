package com.librarySystem.rmi;

import java.rmi.Naming;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.utility.Utilities;

public class Server {

	public void URLRegistry() {
		try {
			// Call initRegistry to check whether the server is already
			// registered on the port or a new registry is to be made.
			Utilities.initRegistry(Constants.PORT);

			LibraryImpl LibImpl = new LibraryImpl();
			String registryURL = "rmi://"+Constants.HOSTANAME+":"+Constants.PORT+"/"+University.CONCORDIA.getCode()+"";

			Naming.rebind(registryURL, LibImpl);
			System.out.println("Server is Running...");

		} catch (Exception e) {
			System.out.println("The server did not start!! "+e.getMessage());
		}

	}

}
