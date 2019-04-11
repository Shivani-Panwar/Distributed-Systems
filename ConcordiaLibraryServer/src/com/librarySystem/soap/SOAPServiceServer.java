package com.librarySystem.soap;

import javax.xml.ws.Endpoint;

import com.librarySystem.constant.University;
import com.librarySystem.dao.LibraryInterface;

public class SOAPServiceServer {
	SOAPService server=new SOAPService();
	public SOAPService publishService(){
		try{
			Endpoint.publish("http://localhostt"+University.CONCORDIA.getSoapPort()+"/ws/LibraryService", server);	    
		}catch(Exception e){
			e.printStackTrace();
		}
		return server;
	}
}
