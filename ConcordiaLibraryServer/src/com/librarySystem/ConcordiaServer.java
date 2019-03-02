package com.librarySystem;

import java.io.IOException;

import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.rmi.Server;
import com.librarySystem.udp.ServerUDP;
import com.librarySystem.utility.Utilities;

/**
 * This is the class that starts both the RMI and the UDP servers.
 * 
 * @author Shivani
 * @version 1.0
 *
 */

public class ConcordiaServer {
	

	public static void main(String[] args) throws IOException {

		Server server = new Server();
		LibraryInterface library = server.URLRegistry();
		Utilities.loadLibrary(library);

		ServerUDP serverudp = new ServerUDP();
		serverudp.startServer();
	}
}
