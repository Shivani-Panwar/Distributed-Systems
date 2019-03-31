package com.librarySystem.constant;

/**
 * This class defines all the constant values that are being used throughout the project
 * @author Shivani
 * @version 1.0
 *
 */
public class Constants {
	
	public static final University UNIVERSITY = University.MONTREAL;
	public static final DSImplementation DS_IMPLEMENTATION = DSImplementation.WEBSERVICE;
	public static final String HOSTANAME = "localhost";
	public static final int RMI_PORT = 9999;
	public static final int CORBA_PORT = 1050;
	public static final String ORBINITIALPORT_KEY = "-ORBInitialPort";
	public static final String ORBINITIALHOST_KEY = "-ORBInitialHost";
	public static final String LIBRARY_DISK_PATH = "D:\\LibrarySystem\\";
	public static final String SERVER_LOG_PATH = "D:\\LibrarySystem\\ServerLogs\\";
	public static final String CLIENT_LOG_PATH = "D:\\LibrarySystem\\UserLogs\\";
	public static final String ESCAPED_ESCAPE_OPERATOR = "\\";
	public static final String SERVER_MESSAGE_SEPERATOR = "|";
	public static final String SERVER_MESSAGE_DOUBLE_SEPERATOR = "||";
	public static final String FIND_ITEM_ACTION = "FIND_ITEM";
	public static final String BORROW_ITEM_ACTION = "BORROW_ITEM";
	public static final String ADD_TO_QUEUE_ACTION = "ADD_TO_QUEUE";
	public static final String RETURN_ITEM_ACTION="RETURN_ITEM";
	public static final String CHECK_IF_AVAILABLE_ACTION="CHECK_IF_AVAILABLE";
	public static final String CAN_BORROW_ACTION="CAN_BORROW";
	public static final String CAN_RETURN_ACTION="CAN_RETURN";
	public static final String BORROWED_FROM_OTHER="Item successfully borrowed from external Library!!";
	public static final String BORROWED_FROM_OWN="Item successfully borrowed from the library!!";
	public static final String BORROW_FAIL_OWN="Item cannot be borrowed from the library!!";
	public static final String BORROW_FAIL_ITEM_NOT_FOUND="The item does not exist in the inventory";
	public static final String BORROW_FAIL_NO_OF_DAYS="Item cannot be borrowed due to invalid number of days";
	public static final String BORROW_FAIL_ALREADY_BORROWED="User has already borrowed the item";
	public static final String CORBA_CONNECT_REFUSE_EXCEPTION = "Connection refused: connect";
	
}
