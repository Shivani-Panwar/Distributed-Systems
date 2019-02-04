package com.librarySystem.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.librarySystem.constant.Constants;

/**
 * 
 * @author Shivani
 * @version 1.0
 *
 */
public class Server {

	public void replyToClient() throws IOException {
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
