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
import java.util.Date;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;

/**
 * This class contains the utilities that the server class can use.
 * 
 * @author shivani
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
		String FilePath = "D:\\Logs\\ServerLogs\\" + serverID + "_Log";
		try {
			File logFile = new File(FilePath);
			logFile.createNewFile();
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = "Client ID : " + memberID + " - Action Performed : " + actionPerformed + " Message : "
					+ reply + " Time : " + dateFormat.format(new Date());
			System.out.println(toWrite);
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
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
		String FilePath = "D:\\Logs\\UserLogs\\" + memberID + "_Log";
		try {
			File logFile = new File(FilePath);
			logFile.createNewFile();
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = "Action Performed : " + actionPerformed + " Message : " + reply + " Time : "
					+ dateFormat.format(new Date()) + "";
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
	 * @param str
	 * @param clientCheck
	 * @param code
	 * @param isManager
	 * @return
	 */
	public static boolean CodeCheck(String str, boolean clientCheck, String code, boolean isManager) {

		str = (clientCheck) ? str.substring(0, 4) : str.substring(0, 3);
		if (clientCheck) {
			if (str.equals(code)) {
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
	
	public static University getUniversity(String userId){
		if(University.CONCORDIA.getCode().equals(userId.substring(0, 3))){
			return University.CONCORDIA;
		} else if(University.MCGILL.getCode().equals(userId.substring(0, 3))){
			return University.MCGILL;	
		} else {
			return University.MONTREAL;
		}
	}
	
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
	
	public static String getActionFromMessage(String message){
		return message.split(Constants.SERVER_MESSAGE_SEPERATOR)[0];
	}
	
	public static String getUserIdFromMessage(String message){
		return message.split(Constants.SERVER_MESSAGE_SEPERATOR)[1];
	}
	
	public static String getItemFromMessage(String message){
		return message.split(Constants.SERVER_MESSAGE_SEPERATOR)[2];
	}
	
	public static int getDaysFromMessage(String message){
		return Integer.valueOf(message.split(Constants.SERVER_MESSAGE_SEPERATOR)[2]);
	}
	
	public static ArrayList<Item> getItemsFromReply(String message){
		ArrayList<Item> items = new ArrayList<>();
		String[] list = message.split(Constants.SERVER_MESSAGE_DOUBLE_SEPERATOR);
		for(int i = 0; i < list.length; i++){
			String[] s = list[i].split(Constants.SERVER_MESSAGE_SEPERATOR);
			if(s.length == 3) {
				Item item = new Item(s[0].trim(),s[1].trim(),Integer.valueOf(s[2].trim()));
				items.add(item);
			}
		}
		return items;
	}
	
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
