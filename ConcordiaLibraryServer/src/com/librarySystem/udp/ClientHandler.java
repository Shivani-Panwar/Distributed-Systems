package com.librarySystem.udp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.librarySystem.constant.Constants;
import com.librarySystem.dao.LibraryImpl;
import com.librarySystem.dao.LibraryInterface;
import com.librarySystem.utility.Utilities;

public class ClientHandler extends Thread {
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;

	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		String received;
		try {

			LibraryInterface library = new LibraryImpl();
			
			received = dis.readUTF();

			String action = Utilities.getActionFromMessage(received);

			// write on output stream based on the
			// answer from the client
			switch (action) {

			case Constants.FIND_ITEM_ACTION:
				dos.writeUTF(Utilities.getReplyStringFromList(library.findItem(Utilities.getUserIdFromMessage(received)
						, Utilities.getItemFromMessage(received))));
				break;

			case Constants.BORROW_ITEM_ACTION:
				dos.writeUTF(String.valueOf(library.borrowItem(Utilities.getUserIdFromMessage(received)
						, Utilities.getItemFromMessage(received), Utilities.getDaysFromMessage(received))));
				break;
				
			case Constants.ADD_TO_QUEUE_ACTION:
				dos.writeUTF("");
				break;

			default:
				dos.writeUTF("Invalid input");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// closing resources
			this.dis.close();
			this.dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
