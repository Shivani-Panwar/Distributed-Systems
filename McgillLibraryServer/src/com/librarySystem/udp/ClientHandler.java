package com.librarySystem.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.librarySystem.constant.Constants;
import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.utility.Utilities;

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
			
			received = in.getData().toString();

			String action = Utilities.getActionFromMessage(received);

			// write on output stream based on the
			// answer from the client
			switch (action) {

			case Constants.FIND_ITEM_ACTION:
				bytes = Utilities.getReplyStringFromList(library.findItem(Utilities.getUserIdFromMessage(received)
						, Utilities.getItemFromMessage(received))).getBytes();
				out= new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				//dos.writeUTF(Utilities.getReplyStringFromList(library.findItem(Utilities.getUserIdFromMessage(received)
				//		, Utilities.getItemFromMessage(received))));
				break;

			case Constants.BORROW_ITEM_ACTION:
				bytes = String.valueOf(library.borrowItem(Utilities.getUserIdFromMessage(received)
						, Utilities.getItemFromMessage(received), Utilities.getDaysFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				//dos.writeUTF(String.valueOf(library.borrowItem(Utilities.getUserIdFromMessage(received)
				//		, Utilities.getItemFromMessage(received), Utilities.getDaysFromMessage(received))));
				break;
				
			case Constants.ADD_TO_QUEUE_ACTION:
				bytes = "".getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;
				
			case Constants.RETURN_ITEM_ACTION:
				bytes= String.valueOf(library.returnItem(Utilities.getUserIdFromMessage(received)
						, Utilities.getItemFromMessage(received))).getBytes();
				out = new DatagramPacket(bytes,bytes.length, in.getAddress(),in.getPort());
				break;
				
			default:
				bytes = "Invalid input".getBytes();
				out = new DatagramPacket(bytes, bytes.length, in.getAddress(), in.getPort());
				break;
			}
			
			ds.send(out);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
