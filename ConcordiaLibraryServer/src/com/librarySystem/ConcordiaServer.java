package com.librarySystem;

import java.io.IOException;

import com.librarySystem.rmi.Server;
import com.librarySystem.udp.ServerUDP;


/**
 * This is the class that starts both the RMI and the UDP servers.
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class ConcordiaServer {

	public static void main(String[] args) throws IOException{

		Server server=new Server();
		server.URLRegistry();	
		
		ServerUDP serverudp=new ServerUDP();
		serverudp.startServer();
		
		
	}
}
