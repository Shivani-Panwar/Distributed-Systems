package com.librarySystem;

import com.librarySystem.rmi.Server;


/**
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class ConcordiaServer {

	public static void main(String[] args){
		//ArrayList<University> lib = Utilities.getRemoteLibraryNames();
		//for(University library : lib){
		//	System.out.println("Code :" + library.getCode() + " Port :" + library.getUdpPort());
		//}
		
		Server server=new Server();
		server.URLRegistry();		
	}
}
