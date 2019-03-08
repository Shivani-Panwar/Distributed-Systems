package com.librarySystem.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;

/**
 * This class contains utilities that the client uses.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Utilities {
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
	public static University getUniversity(String userId) {
		if (University.CONCORDIA.getCode().equals(userId.substring(0, 3))) {
			return University.CONCORDIA;
		} else if (University.MCGILL.getCode().equals(userId.substring(0, 3))) {
			return University.MCGILL;
		} else {
			return University.MONTREAL;
		}
	}
	
	/**
	 * This method logs all the exceptions that are thrown.
	 * 
	 * @param error - The error message
	 */
	public static void errorLog(String error){
		String FilePath = Constants.CLIENT_LOG_PATH+"Error_Log";
		try {
			File logFile = new File(FilePath);
			//System.out.println(logFile.createNewFile());
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = " Error Message : " + error + " Time : "
					+ dateFormat.format(new Date()) + "\n";
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	/**
	 * This method is used to check that the suffix of the university code are integers only.
	 * @param regex to check the string entered contains only integers
	 * @param string to be checked
	 * @return
	 */
	public static boolean matchesOrNot(String regex, String string){
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(string);
		if(matcher.find()) {
			return true;
		}
		return false;
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
		return message.split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR)[0].trim();
	}
	
	/**
	 * This method is used to get the userID from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static String getUserIdFromMessage(String message){
		return message.split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR)[1].trim();
	}
	
	/**
	 * This method is used to get the item name from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static String getItemFromMessage(String message){
		return message.split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR)[2].trim();
	}
	
	/**
	 * This method is used to get the number of days from the message that is sent using UDP.
	 * @param message
	 * @return
	 */
	public static int getDaysFromMessage(String message){
		return Integer.valueOf(message.split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR)[3].trim());
	}
	
	/**
	 * This method is used to get the item from the reply that is received from the server
	 * @param message
	 * @return
	 */
	public static ArrayList<Item> getItemsFromReply(String message){
		ArrayList<Item> items = new ArrayList<>();
		if(message != null){
			String[] list = message.split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR
					+Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR);
			for(int i = 0; i < list.length; i++){
				String[] s = list[i].split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR);
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
