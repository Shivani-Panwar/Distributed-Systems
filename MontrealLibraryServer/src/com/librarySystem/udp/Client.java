package com.librarySystem.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

/**
 * This class defines the methods that require server to server interaction using UDP.
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class Client {

	/**
	 * This method is used to send data to the  othe rservers using UDP.
	 * 
	 * @param message - Message that is to be sent via UDP connection.
	 * @param universityPort - Port to which the data is being sent.
	 * @return
	 */
	private String requestToServer(String message, int universityPort) {
		DatagramSocket socket = null;
		try {

			socket = new DatagramSocket();
			socket.setSoTimeout(10000);

			// getting localhost ip
			InetAddress ip = InetAddress.getByName(Constants.HOSTANAME);

			// establish the connection with server port
			byte[] messagetosend = message.getBytes();
			DatagramPacket request = new DatagramPacket(messagetosend, messagetosend.length, ip, universityPort);
			// Send the message to the server
			socket.send(request);

			byte[] buffer = new byte[4000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

			// Recieve message from server
			socket.receive(reply);
			String received = new String(reply.getData());

			return received.trim();
		} catch (Exception e) {
			Utilities.errorLog(e.getMessage());
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
		return null;
	}

	/**
	 * This method is used to get all the items with a specified item name in other servers.
	 * 
	 * @param userID - ID of the user who has requested the list.
	 * @param itemName - Name of item that is to be searched.
	 * @return
	 */
	public ArrayList<Item> findItemsOnRemoteLibraries(String userID, String itemName) {

		String message = Utilities.getServerMessageString(Constants.FIND_ITEM_ACTION, userID, itemName, 0);

		ArrayList<Item> items = new ArrayList<>();

		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		for(University library : remoteLibraries){
			items.addAll(Utilities.getItemsFromReply(requestToServer(message, library.getUdpPort())));
		}
		

		return items;
	}

	/**
	 * This method is used when a user has requested to borrow an item that is not present in his own library.
	 * 
	 * @param userID
	 * @param itemID
	 * @param days
	 * @return
	 */ 
	public String borrowItemFromRemoteLibrary(String userID, String itemID, int days) {

		String message = Utilities.getServerMessageString(Constants.BORROW_ITEM_ACTION, userID, itemID, days);

		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		return (requestToServer(message, (remoteLibraries.get(0).equals(Utilities.getUniversity(itemID))
						? remoteLibraries.get(0).getUdpPort() : remoteLibraries.get(1).getUdpPort())));

	}

	/**
	 * This method is used to add the user to a wait list for an item when the library fails to provide the user with the requested item.
	 * 
	 * @param userID
	 * @param itemID
	 * @return
	 */
	public boolean addItemtoQueueToRemoteLibrary(String userID, String itemID) {

		String message = Utilities.getServerMessageString(Constants.ADD_TO_QUEUE_ACTION, userID, itemID, 0);

		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		return Boolean
				.valueOf(requestToServer(message, (remoteLibraries.get(0).equals(Utilities.getUniversity(itemID))
						? remoteLibraries.get(0).getUdpPort() : remoteLibraries.get(1).getUdpPort())));

	}

	/**
	 * This method is used when the user wants to return an item which does not belong to his own library.
	 * 
	 * @param userID
	 * @param itemID
	 * @return
	 */
	public boolean returnItemToRemoteLibrary(String userID, String itemID) {
		String message = Utilities.getServerMessageString(Constants.RETURN_ITEM_ACTION, userID, itemID, 0);
		
		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		return Boolean
				.valueOf(requestToServer(message, (remoteLibraries.get(0).equals(Utilities.getUniversity(itemID))
						? remoteLibraries.get(0).getUdpPort() : remoteLibraries.get(1).getUdpPort())));
	}
	
	/**
	 * This method is used to find if an item is available in a library.
	 * @param itemID
	 * @return
	 */
	public boolean checkIfAvailable(String itemID){
		String message = Utilities.getServerMessageString(Constants.CHECK_IF_AVAILABLE_ACTION, "", itemID, 0);

		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		return Boolean
				.valueOf(requestToServer(message, (remoteLibraries.get(0).equals(Utilities.getUniversity(itemID))
						? remoteLibraries.get(0).getUdpPort() : remoteLibraries.get(1).getUdpPort())));
	}
	
	/**
	 * This method is used to check if the user can borrow an item.
	 * @param userID
	 * @param itemID
	 * @return
	 */
	public boolean canBorrow(String userID,String itemID){
		String message = Utilities.getServerMessageString(Constants.CAN_BORROW_ACTION, userID, itemID, 0);

		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		return Boolean
				.valueOf(requestToServer(message, (remoteLibraries.get(0).equals(Utilities.getUniversity(itemID))
						? remoteLibraries.get(0).getUdpPort() : remoteLibraries.get(1).getUdpPort())));
	}
	
	/**
	 * This method is used to check if the user can return an item.
	 * @param userID
	 * @param itemID
	 * @return
	 */
	public boolean canReturn(String userID,String itemID){
		String message = Utilities.getServerMessageString(Constants.CAN_RETURN_ACTION, userID, itemID, 0);

		ArrayList<University> remoteLibraries = Utilities.getRemoteLibraryNames();
		return Boolean
				.valueOf(requestToServer(message, (remoteLibraries.get(0).equals(Utilities.getUniversity(itemID))
						? remoteLibraries.get(0).getUdpPort() : remoteLibraries.get(1).getUdpPort())));
	}

}
