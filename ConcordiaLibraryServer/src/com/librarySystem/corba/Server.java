package com.librarySystem.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.librarySystem.constant.Constants;

public class Server {

	public void startServer(String[] args) {
		try {
			// create and initialize the ORB //// get reference to rootpoa &amp;
			// activate the POAManager
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			CorbaToLibraryInterface libraryObj = new CorbaToLibraryInterface();
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
			for (;;) {
				orb.run();
			}
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println(Constants.UNIVERSITY + " Server Exiting ...");

	}
}
