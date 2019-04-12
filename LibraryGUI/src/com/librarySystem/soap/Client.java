package com.librarySystem.soap;

import com.librarySystem.constant.University;
import com.librarySystem.utility.Utilities;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.WebServiceException;

import com.librarySystem.constant.Constants;

public class Client {
	public SOAPService getLibrary(University university) throws MalformedURLException {
		SOAPService obj = null;
		WebServiceException e1=null;
		URL url=null;
		if(university.equals(University.MONTREAL)){
			url =new URL("http://"+Constants.HOSTANAME+":"+University.MONTREAL.getSoapPort()+"/ws/LibraryService?wsdl");
		}else if(university.equals(University.MCGILL)){
			url =new URL("http://"+Constants.HOSTANAME+":"+University.MCGILL.getSoapPort()+"/ws/LibraryService?wsdl");
					
		}else{
			url =new URL("http://"+Constants.HOSTANAME+":"+University.CONCORDIA.getSoapPort()+"/ws/LibraryService?wsdl");
			
		}
		SOAPServiceService service=new SOAPServiceService(url,e1);
		try {
			
			obj = service.getSOAPServicePort();
		//	registry = LocateRegistry.getRegistry(Constants.PORT);
			//obj = (LibraryInterface) registry.lookup(registryURL);
		} catch (Exception e) {
			System.out.println("The server is not running!!");
			Utilities.errorLog(e.getMessage());
		}
		return obj;
	}

}
