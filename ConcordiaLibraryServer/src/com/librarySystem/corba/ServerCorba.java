package com.librarySystem.corba;

import java.io.IOException;
import java.util.logging.LogManager;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.librarySystem.constant.Constants;

public class ServerCorba {

	public CorbaToLibraryInterface startServer(String[] args) {
		CorbaToLibraryInterface libraryObj = new CorbaToLibraryInterface();
		try {
			// create and initialize the ORB //// get reference to rootpoa &amp;
			// activate the POAManager
			LogManager.getLogManager().reset();
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			
			libraryObj.setORB(orb);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(libraryObj);
			Library href = LibraryHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			NameComponent path[] = ncRef.to_name(Constants.UNIVERSITY.getCode());
			ncRef.rebind(path, href);

			System.out.println(Constants.UNIVERSITY + " Server ready and waiting ...");

			// wait for invocations from clients
			Thread handler = new CorbaClientHandler(orb);
			handler.start();
		}

		catch (Exception e) {
			if(e instanceof org.omg.CORBA.COMM_FAILURE){
				if(e.getCause().getMessage().equals(Constants.CORBA_CONNECT_REFUSE_EXCEPTION)){
					try {
						Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"orbd -ORBInitialPort "+Constants.CORBA_PORT+"\"");
						Thread.sleep(5000);
						this.startServer(args);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		return libraryObj;
	}
}
