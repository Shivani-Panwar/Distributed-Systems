package com.librarySystem.soap;

import javax.xml.ws.Endpoint;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;

public class SOAPServiceServer {
	SOAPService server=new SOAPService();
	public SOAPService publishService(){
		try{
			Endpoint.publish("http://"+Constants.HOSTANAME+":"+University.CONCORDIA.getSoapPort()+"/ws/LibraryService", server);	    
		}catch(Exception e){
			e.printStackTrace();
		}
		return server;
	}
}
