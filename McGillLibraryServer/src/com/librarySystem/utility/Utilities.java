package com.librarySystem.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;

/**
 * This class contains the utilities that the server class can use.
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class Utilities {

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * This method is used to create the log files in .txt format that each
	 * server will maintain. These files have details of the user who invoked a
	 * method on the server, the action that was performed, the reply sent to
	 * the user and the date and time of the action.
	 * 
	 * @param serverID
	 *            -The ID associated with the server for which the log file is
	 *            being created.
	 * @param actionPerformed
	 *            -The action that is being requested from the server.
	 * @param memberID
	 *            -The ID of the user that is performing any action on the
	 *            server.
	 * @param reply
	 *            -The reply the server sends to the user.
	 * @throws IOException
	 */
	public static void serverLog(String serverID, String actionPerformed, String memberID, String reply) {
		String FilePath = Constants.SERVER_LOG_PATH + serverID + "_Log";
		try {
			File logFile = new File(FilePath);
			//System.out.println(logFile.createNewFile());
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = "Client ID : " + memberID + " - Action Performed : " + actionPerformed + " Message : " + reply
					+ " Time : " + dateFormat.format(new Date())+"\n";
			System.out.println(toWrite);
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			Utilities.errorLog(e.getMessage());
		}

	}

	/**
	 * This method is used to create the log files in .txt format that will be
	 * maintained for each user. These files have details of the user of what
	 * method was invoked on the server, the reply sent to the user and the date
	 * and time of the action.
	 * 
	 * @param memberID
	 *            -The ID of the user that is performing any action on the
	 *            server.
	 * @param actionPerformed
	 *            -The action that is being requested from the server.
	 * @param reply
	 *            -The reply the server sends to the user.
	 * @throws IOException
	 */
	public static void clientLog(String memberID, String actionPerformed, String reply) {
		String FilePath = Constants.CLIENT_LOG_PATH + memberID + "_Log";
		try {
			File logFile = new File(FilePath);
			//System.out.println(logFile.createNewFile());
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = "Action Performed : " + actionPerformed + " Message : " + reply + " Time : "
					+ dateFormat.format(new Date()) + "\n";
			System.out.println(toWrite);
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			Utilities.errorLog(e.getMessage());
		}
	}
	
	/**
	 * This method logs all the exceptions that are thrown.
	 * 
	 * @param error - The error message
	 */
	public static void errorLog(String error){
		String FilePath = "Error_Log";
		try {
			File logFile = new File(FilePath);
			//System.out.println(logFile.createNewFile());
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = " Error Message : " + error + " Time : "
					+ dateFormat.format(new Date()) + "\n";
			System.out.println(toWrite);
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * This method is used by the server to initiate the RMI registry.
	 * 
	 * @param PortNumber
	 *            - The port number on which the registry is to be initiated
	 * @throws RemoteException
	 *             - If there is no existing registry at the port an new
	 *             registry is created.
	 */
	@SuppressWarnings("unused")
	public static void initRegistry(int PortNumber) throws RemoteException {
		try {
			Registry register = LocateRegistry.getRegistry(PortNumber);
			register.list();
		} catch (RemoteException e) {
			System.out.println("Cannot register to the port");
			Registry register = LocateRegistry.createRegistry(PortNumber);
			System.out.println("New Registery is created");
		}
	}

	/**
	 * This method is used to check the code that is prefixed before the item
	 * IDs and the user IDs
	 * 
	 * @param str
	 *            - The string that is to be checked
	 * @param clientCheck
	 *            - true if code is to be checked for a user, false if the code
	 *            is to be checked for item
	 * @param code
	 *            - the code with which the string will be compared
	 * @param isManager
	 *            - true when the user is a manager, false in all other cases
	 * @return
	 */
	public static boolean CodeCheck(String str, boolean clientCheck, String code, boolean isManager) {

		str = (clientCheck) ? str.substring(0, 4) : str.substring(0, 3);
		if (clientCheck) {
			if (!str.equals(code)) {
				if (isManager) {
					if (str.endsWith("M")) {
						return true;
					}
				} else {
					if (str.endsWith("U")) {
						return true;
					}
				}
			}
		} else {
			if (str.equals(code)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * This method is used to find the university corresponding to the user ID.
	 * @param userId
	 * @return
	 */
	public static University getUniversity(String userId){
		if(University.CONCORDIA.getCode().equals(userId.substring(0, 3))){
			return University.CONCORDIA;
		} else if(University.MCGILL.getCode().equals(userId.substring(0, 3))){
			return University.MCGILL;	
		} else {
			return University.MONTREAL;
		}
	}
	
	/**
	 * This method is used to find the names of the library apart from the users library.
	 * @return
	 */
	public static ArrayList<University> getRemoteLibraryNames(){
		University[] libraries = University.class.getEnumConstants();
		ArrayList<University> list = new ArrayList<University>(Arrays.asList(libraries));
		list.remove(Constants.UNIVERSITY);
		return list;
	}
	
	/**
	 * This method defines how the UDP messages from servers will be separated.
	 * @param action - Action being  performed by the server.
	 * @param userID
	 * @param item
	 * @param days
	 * @return
	 */
	public static String getServerMessageString(String action, String userID, String item, int days){	
		return new StringBuilder().append(action)
				.append(Constants.SERVER_MESSAGE_SEPERATOR)
				.append(userID)
				.append(Constants.SERVER_MESSAGE_SEPERATOR)
				.append(item)
				.append(Constants.SERVER_MESSAGE_SEPERATOR)
				.append(String.valueOf(days))
				.toString();
			
	}
	
	/**
	 * This method is used to get the action being performed from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static String getActionFromMessage(String message){
		return message.split(Constants.SERVER_MESSAGE_SEPERATOR)[0];
	}
	
	/**
	 * This method is used to get the userID from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static String getUserIdFromMessage(String message){
		return message.split(Constants.SERVER_MESSAGE_SEPERATOR)[1];
	}
	
	/**
	 * This method is used to get the item name from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static String getItemFromMessage(String message){
		return message.split(Constants.SERVER_MESSAGE_SEPERATOR)[2];
	}
	
	/**
	 * This method is used to get the number of days from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static int getDaysFromMessage(String message){
		return Integer.valueOf(message.split(Constants.SERVER_MESSAGE_SEPERATOR)[2]);
	}
	
	/**
	 * This method is used to get the item from the reply that is received from the server
	 * @param message
	 * @return
	 */
	public static ArrayList<Item> getItemsFromReply(String message){
		ArrayList<Item> items = new ArrayList<>();
		if(message != null){
			String[] list = message.split(Constants.SERVER_MESSAGE_DOUBLE_SEPERATOR);
		
			for(int i = 0; i < list.length; i++){
				String[] s = list[i].split(Constants.SERVER_MESSAGE_SEPERATOR);
				if(s.length == 3) {
					Item item = new Item(s[0].trim(), s[1].trim(), Integer.valueOf(s[2].trim()));
					items.add(item);
				}
			}
		}
		return items;
	}
	
	/**
	 * This message is used to get the String reply from the server message.
	 * @param list
	 * @return
	 */
	public static String getReplyStringFromList(ArrayList<Item> list){
		int i = 0;
		StringBuilder builder = new StringBuilder();
		for(Item item : list){
			builder.append(item.getID())
			.append(Constants.SERVER_MESSAGE_SEPERATOR)
			.append(item.getName())
			.append(Constants.SERVER_MESSAGE_SEPERATOR)
			.append(String.valueOf(item.getQuantity()));
			if(i < list.size()-1){
				builder.append(Constants.SERVER_MESSAGE_DOUBLE_SEPERATOR);
			}
		}
		return builder.toString();	
	}
}