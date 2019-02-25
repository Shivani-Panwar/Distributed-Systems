package com.librarySystem.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.librarySystem.constant.Constants;
import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.utility.Utilities;

/**
 * This class checks the request that will be sent to the other servers and
 * creates a message that will be sent using UDP.
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class ClientHandler extends Thread {
	final DatagramPacket in;
	final DatagramSocket ds;

	// Constructor
	public ClientHandler(DatagramSocket s2, DatagramPacket request) {
		this.ds = s2;
		this.in = request;
	}

	@Override
	public void run() {
		String received;
		try {
			final DatagramPacket out;
			byte[] bytes;

			LibraryInterface library = new LibraryImpl();

			received = new String(in.getData());

			String action = Utilities.getActionFromMessage(received);

			// write on output stream based on the
			// answer from the client
			switch (action) {

			case Constants.FIND_ITEM_ACTION:
				bytes = Utilities.getReplyStringFromList(library.findItem(Utilities.getUserIdFromMessage(received),
						Utilities.getItemFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;

			case Constants.BORROW_ITEM_ACTION:
				bytes = String
						.valueOf(library.borrowItem(Utilities.getUserIdFromMessage(received),
								Utilities.getItemFromMessage(received), Utilities.getDaysFromMessage(received)))
						.getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;

			case Constants.ADD_TO_QUEUE_ACTION:
				bytes = String.valueOf(library.addToQueue(Utilities.getItemFromMessage(received),
						Utilities.getUserIdFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;

			case Constants.RETURN_ITEM_ACTION:
				bytes = String.valueOf(library.returnItem(Utilities.getUserIdFromMessage(received),
						Utilities.getItemFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;
				
			case Constants.CHECK_IF_AVAILABLE_ACTION:
				bytes = String.valueOf(library.checkAvailability(Utilities.getItemFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;
				
			case Constants.CAN_BORROW_ACTION:
				bytes = String.valueOf(library.checkAvailability(Utilities.getItemFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;
				
			case Constants.CAN_RETURN_ACTION:
				bytes = String.valueOf(library.checkAvailability(Utilities.getItemFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;

			default:
				bytes = "Invalid input".getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;
			}

			ds.send(out);

		} catch (IOException e) {
			Utilities.errorLog(e.toString());
		}
	}
}
