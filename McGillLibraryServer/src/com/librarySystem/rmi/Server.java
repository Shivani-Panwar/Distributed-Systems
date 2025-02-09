package com.librarySystem.rmi;

import java.rmi.Naming;

import com.librarySystem.constant.Constants;
import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
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
	public LibraryInterface URLRegistry() {
		try {
			// Call initRegistry to check whether the server is already
			// registered on the port or a new registry is to be made.
			Utilities.initRegistry(Constants.RMI_PORT);

			LibraryImpl LibImpl = new LibraryImpl();
			String registryURL = "rmi://"+Constants.HOSTANAME+":"+Constants.RMI_PORT+"/"+Constants.UNIVERSITY.getCode()+"";

			Naming.rebind(registryURL, LibImpl);
			System.out.println("Server is Running...");

			return LibImpl;
		} catch (Exception e) {
			System.out.println("The server did not start!! "+e.getMessage());
			Utilities.errorLog(e.getMessage());
		}

		return null;
	}

}
