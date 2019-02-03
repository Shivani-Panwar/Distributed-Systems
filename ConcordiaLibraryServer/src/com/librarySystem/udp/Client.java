package com.librarySystem.udp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

public class Client {

	private String requestToServer(String message, int universityPort) {
		try {
			// getting localhost ip
			InetAddress ip = InetAddress.getByName(Constants.HOSTANAME);

			// establish the connection with server port
			Socket s = new Socket(ip, universityPort);

			// obtaining input and out streams
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());

			dos.writeUTF(message);

			// printing date or time as requested by client
			String received = dis.readUTF();

			// closing resources
			dis.close();
			dos.close();
			s.close();
			return received;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Item> findItemsOnRemoteLibraries(String userID, String itemName){
		
		String message = Utilities.getServerMessageString(Constants.FIND_ITEM_ACTION, userID, itemName, 0);
		
		ArrayList<Item> items = new ArrayList<>();
		
		items.addAll(Utilities.getItemsFromReply(requestToServer(message, Constants.OTHER_SERVER_UDP_PORT_I)));
		items.addAll(Utilities.getItemsFromReply(requestToServer(message, Constants.OTHER_SERVER_UDP_PORT_II)));
		
		return items;
	}
	
	public boolean borrowItemFromRemoteLibrary(String userID, String itemID, int days){
	
		String message = Utilities.getServerMessageString(Constants.BORROW_ITEM_ACTION, userID, itemID, days);
		
		return Boolean.valueOf(requestToServer(message,
				(University.MCGILL.getCode().equals(Utilities.getUniversity(userID)) ? 
						Constants.OTHER_SERVER_UDP_PORT_I : Constants.OTHER_SERVER_UDP_PORT_II)));
		
	}
	
	public boolean addItemtoQueueToRemoteLibrary(String userID, String itemID){
		
		String message = Utilities.getServerMessageString(Constants.ADD_TO_QUEUE_ACTION, userID, itemID, 0);
		
		return Boolean.valueOf(requestToServer(message,
				(University.MCGILL.getCode().equals(Utilities.getUniversity(userID)) ? 
						Constants.OTHER_SERVER_UDP_PORT_I : Constants.OTHER_SERVER_UDP_PORT_II)));
		
	}

}
