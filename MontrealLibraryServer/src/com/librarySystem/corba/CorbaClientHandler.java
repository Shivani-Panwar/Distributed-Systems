package com.librarySystem.corba;

import org.omg.CORBA.ORB;

public class CorbaClientHandler extends Thread {
	
	final private ORB orb;
	
	public CorbaClientHandler(ORB orb) {
		this.orb = orb;
	}
	
	@Override
	public void run(){
		while(true){
			orb.run();
		}
	}

}
