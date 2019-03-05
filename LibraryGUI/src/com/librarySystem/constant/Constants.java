package com.librarySystem.constant;

/**
 * 
 * This method defines all the constant values used throughout the project
 * @author Shivani
 * @version 1.0
 *
 */
public class Constants {
	
	public static final DSImplementation DS_IMPLEMENTATION = DSImplementation.CORBA;
	public static final String HOSTANAME = "localhost";
	public static final int PORT = 9999;
	public static final int CORBA_PORT = 1050;
	public static final String ORBINITIALPORT_KEY = "-ORBInitialPort";
	public static final String ORBINITIALHOST_KEY = "-ORBInitialHost";
	public static final String SERVER_LOG_PATH = "D:\\Logs\\ServerLogs\\";
	public static final String CLIENT_LOG_PATH = "D:\\Logs\\UserLogs\\";
	public static final String BORROWED_FROM_OTHER="Item successfully borrowed from external Library!!";
	public static final String BORROWED_FROM_OWN="Item successfully borrowed from the library!!";
	public static final String BORROW_FAIL_OWN="Item cannot be borrowed from the library!!";
	public static final String BORROW_FAIL_ITEM_NOT_FOUND="The item does not exist in the inventory";
	public static final String BORROW_FAIL_ALREADY_BORROWED="User has already borrowed the item";
	public static final String ESCAPED_ESCAPE_OPERATOR = "\\";
	public static final String SERVER_MESSAGE_SEPERATOR = "|";
	public static final String SERVER_MESSAGE_DOUBLE_SEPERATOR = "||";
	
}
