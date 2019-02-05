package com.librarySystem.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.librarySystem.constant.Constants;

public class Server {

	public void replyToClient() throws IOException {
		// server is listening on port 5056
		// ServerSocket ss = new ServerSocket(Constants.OWN_SERVER_UDP_PORT);
		DatagramSocket ds = new DatagramSocket(Constants.OWN_SERVER_UDP_PORT);

		byte[] buffer = new byte[4000];
		// running infinite loop for getting
		// client request
		while (true) {
			// DatagramSocket s = null;
			try {
				// socket object to receive incoming client requests
				// s = ds.receive();
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				ds.receive(request);
				// obtaining input and out streams
				// DataInputStream dis = new
				// DataInputStream(s.getInputStream());
				// DataOutputStream dos = new
				// DataOutputStream(s.getOutputStream());
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
