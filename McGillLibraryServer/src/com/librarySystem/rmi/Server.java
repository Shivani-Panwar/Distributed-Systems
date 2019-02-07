package com.librarySystem.rmi;

import java.rmi.Naming;

import com.librarySystem.constant.Constants;
import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.utility.Utilities;


/**
 * This class is used to initiate the server registry for RMI.
 * @author Shivani
 * @version 1.0
 *
 */
public class Server {

	/**
	 * This method initiates the server for the RMI.
	 */
	public void URLRegistry() {
		try {
			// Call initRegistry to check whether the server is already
			// registered on the port or a new registry is to be made.
			Utilities.initRegistry(Constants.PORT);

			LibraryImpl LibImpl = new LibraryImpl();
			String registryURL = "rmi://"+Constants.HOSTANAME+":"+Constants.PORT+"/"+Constants.UNIVERSITY.getCode()+"";

			Naming.rebind(registryURL, LibImpl);
			System.out.println("Server is Running...");

		} catch (Exception e) {
			System.out.println("The server did not start!! "+e.getMessage());
			Utilities.errorLog(e.getMessage());
		}

	}

}
