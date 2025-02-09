package com.librarySystem;

import java.io.IOException;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.DSImplementation;
import com.librarySystem.corba.ServerCorba;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.rmi.Server;
import com.librarySystem.soap.SOAPService;
import com.librarySystem.soap.SOAPServiceServer;
import com.librarySystem.udp.ServerUDP;
import com.librarySystem.utility.Utilities;

/**
 * This is the class that starts both the RMI and the UDP servers.
 * 
 * @author Shivani
 * @version 1.0
 *
 */

public class McGillServer {
	

	public static void main(String[] args) throws IOException {

		if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.RMI)){
			Server server = new Server();
			LibraryInterface library = server.URLRegistry();
			Utilities.loadLibrary(library);
		} else if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.SOAPSERVICE)){
			SOAPServiceServer server=new SOAPServiceServer();
		Utilities.loadLibrary(server.publishService());	
		}else {
			ServerCorba server = new ServerCorba();
			Utilities.loadLibrary(server.startServer(new String[]{Constants.ORBINITIALPORT_KEY, String.valueOf(Constants.CORBA_PORT)
					, Constants.ORBINITIALHOST_KEY, Constants.HOSTANAME}));
		}

		ServerUDP serverudp = new ServerUDP();
		serverudp.startServer();
	}
}
