package com.librarySystem;

import com.librarySystem.rmi.Server;

public class McGillServer {

	
	public static void main(String[] args){
		Server server=new Server();
		server.URLRegistry();		
	}
}
