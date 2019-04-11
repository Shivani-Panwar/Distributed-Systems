package com.librarySystem.soap;

import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.utility.Utilities;

public class SOAPToLibraryInterface {

private static LibraryInterface library;
	
	static{
		try{
			library = new LibraryImpl();
		} catch (Exception e) {
			Utilities.errorLog(e.toString());
		}
	}
	
}
