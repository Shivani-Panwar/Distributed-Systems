package com.librarySystem.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import com.librarySystem.constant.University;

public class Client {

	private String[] args;

	public Client(String[] args) {
		this.args = args;
	}

	public Library getLibrary(University university) {

		try {
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			Library libraryObj = (Library) LibraryHelper.narrow(ncRef.resolve_str(university.getCode()));
			return libraryObj;
		} catch (Exception e) {
			System.out.println("Corba Client exception: " + e);
			e.printStackTrace();
		}
		return null;
	}
}
