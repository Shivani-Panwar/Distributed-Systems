package com.librarySystem.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.librarySystem.constant.Constants;

public class Client {
	
	public  void requestToServer(String message){
		DatagramSocket socket = null;
		//byte[] message= .getBytes();
		
		try {
			socket=new DatagramSocket();
			byte[] messagetosend=message.getBytes();
			DatagramPacket request=new DatagramPacket(messagetosend,messagetosend.length,InetAddress.getByName(Constants.HOSTANAME),Constants.PORT);
			socket.send(request);
			
			byte[] buffer=new byte[4000];
			DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
			socket.receive(reply);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(socket!=null){
				socket.close();
			}
		}
		
		
	}

}
