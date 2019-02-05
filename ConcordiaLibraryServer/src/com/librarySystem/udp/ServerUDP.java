package com.librarySystem.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.librarySystem.constant.Constants;


/**
 * This method starts the UPD server so that the servers can interact with each other.
 * @author Shivani
 * @version 1.0
 *
 */
public class ServerUDP {

	/**
	 * This method defines how theserver will be started for the UDP connection.
	 * @throws IOException
	 */
	public void startServer() throws IOException {
		// server is listening on port
		DatagramSocket ds = new DatagramSocket(Constants.UNIVERSITY.getUdpPort());

		byte[] buffer = new byte[4000];
		// running infinite loop for getting
		// client request
		while (true) {
			try {
				// socket object to receive incoming client requests
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				ds.receive(request);
				
				// create a new thread object
				Thread t = new ClientHandler(ds, request);

				// Invoking the start() method
				t.start();

			} catch (Exception e) {
				ds.close();
				e.printStackTrace();
			}
		}

	}

}
