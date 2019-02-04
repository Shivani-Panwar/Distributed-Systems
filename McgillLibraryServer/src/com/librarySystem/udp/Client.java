package com.librarySystem.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

public class Client {

	private String requestToServer(String message, int universityPort) {
		DatagramSocket socket = null;
		try {

			socket = new DatagramSocket();

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
			String received = reply.getData().toString();

			return received;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
		return null;
	}

	public ArrayList<Item> findItemsOnRemoteLibraries(String userID, String itemName) {

		String message = Utilities.getServerMessageString(Constants.FIND_ITEM_ACTION, userID, itemName, 0);

		ArrayList<Item> items = new ArrayList<>();

		items.addAll(Utilities.getItemsFromReply(requestToServer(message, Constants.OTHER_SERVER_UDP_PORT_I)));
		items.addAll(Utilities.getItemsFromReply(requestToServer(message, Constants.OTHER_SERVER_UDP_PORT_II)));

		return items;
	}

	public boolean borrowItemFromRemoteLibrary(String userID, String itemID, int days) {

		String message = Utilities.getServerMessageString(Constants.BORROW_ITEM_ACTION, userID, itemID, days);

		return Boolean.valueOf(
				requestToServer(message, (University.CONCORDIA.getCode().equals(Utilities.getUniversity(userID))
						? Constants.OTHER_SERVER_UDP_PORT_I : Constants.OTHER_SERVER_UDP_PORT_II)));

	}

	public boolean addItemtoQueueToRemoteLibrary(String userID, String itemID) {

		String message = Utilities.getServerMessageString(Constants.ADD_TO_QUEUE_ACTION, userID, itemID, 0);

		return Boolean.valueOf(
				requestToServer(message, (University.CONCORDIA.getCode().equals(Utilities.getUniversity(userID))
						? Constants.OTHER_SERVER_UDP_PORT_I : Constants.OTHER_SERVER_UDP_PORT_II)));

	}

	public boolean returnItemToRemoteLibrary(String userID, String itemID) {

		String message = Utilities.getServerMessageString(Constants.RETURN_ITEM_ACTION, userID, itemID, 0);
		return Boolean
				.valueOf(requestToServer(message, (University.MCGILL.getCode().equals(Utilities.getUniversity(itemID))
						? Constants.OTHER_SERVER_UDP_PORT_I : Constants.OTHER_SERVER_UDP_PORT_II)));
	}

}
