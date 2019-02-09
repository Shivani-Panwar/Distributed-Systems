package com.librarySystem.constant;

/**
 * This class defines all the constant values that are being used throughout the project
 * @author Shivani
 * @version 1.0
 *
 */
public class Constants {
	
	public static final University UNIVERSITY = University.MONTREAL;
	public static final String HOSTANAME = "localhost";
	public static final int PORT = 9999;
	public static final String SERVER_LOG_PATH = "D:\\Logs\\ServerLogs\\";
	public static final String CLIENT_LOG_PATH = "D:\\Logs\\UserLogs\\";
	public static final String SERVER_MESSAGE_SEPERATOR = "|";
	public static final String SERVER_MESSAGE_DOUBLE_SEPERATOR = "||";
	public static final String FIND_ITEM_ACTION = "FIND_ITEM";
	public static final String BORROW_ITEM_ACTION = "BORROW_ITEM";
	public static final String ADD_TO_QUEUE_ACTION = "ADD_TO_QUEUE";
	public static final String RETURN_ITEM_ACTION="RETURN_ITEM";
	public static final String BORROWED_FROM_OTHER="Item successfully borrowed from external Library!!";
	public static final String BORROWED_FROM_OWN="Item successfully borrowed from Concordia library";
	public static final String BORROW_FAIL_OWN="Item cannot be borrowed from Concordia Library";
	public static final String BORROW_FAIL_ITEM_NOT_FOUND="The item does not exist in the inventory";
	
}
