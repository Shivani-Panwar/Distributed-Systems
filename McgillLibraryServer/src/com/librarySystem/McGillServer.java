package com.librarySystem;

import com.librarySystem.rmi.Server;

public class ConcordiaServer {

	public static void main(String[] args){
		Server server=new Server();
		server.URLRegistry();		
	}
}
