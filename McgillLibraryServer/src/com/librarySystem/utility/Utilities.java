package com.librarySystem.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.librarySystem.constant.Constants;

/**
 * This class contains the utilities that the server class can use.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Utilities {

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * This method is used to create the log files in .txt format that each
	 * server will maintain. These files have details of the user who invoked a
	 * method on the server, the action that was performed, the reply sent to
	 * the user and the date and time of the action.
	 * 
	 * @param serverID
	 *            -The ID associated with the server for which the log file is
	 *            being created.
	 * @param actionPerformed
	 *            -The action that is being requested from the server.
	 * @param memberID
	 *            -The ID of the user that is performing any action on the
	 *            server.
	 * @param reply
	 *            -The reply the server sends to the user.
	 * @throws IOException
	 */
	public static void serverLog(String serverID, String actionPerformed, String memberID, String reply) {
		String FilePath = Constants.SERVER_LOG_PATH + serverID + "_Log";
		try {
			File logFile = new File(FilePath);
			System.out.println(logFile.createNewFile());
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = "Client ID : " + memberID + " - Action Performed : " + actionPerformed + " Message : " + reply
					+ " Time : " + dateFormat.format(new Date());
			System.out.println(toWrite);
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to create the log files in .txt format that will be
	 * maintained for each user. These files have details of the user of what
	 * method was invoked on the server, the reply sent to the user and the date
	 * and time of the action.
	 * 
	 * @param memberID
	 *            -The ID of the user that is performing any action on the
	 *            server.
	 * @param actionPerformed
	 *            -The action that is being requested from the server.
	 * @param reply
	 *            -The reply the server sends to the user.
	 * @throws IOException
	 */
	public static void clientLog(String memberID, String actionPerformed, String reply) {
		String FilePath = Constants.CLIENT_LOG_PATH + memberID + "_Log";
		try {
			File logFile = new File(FilePath);
			System.out.println(logFile.createNewFile());
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));
			String toWrite = "Action Performed : " + actionPerformed + " Message : " + reply + " Time : "
					+ dateFormat.format(new Date()) + "";
			System.out.println(toWrite);
			wr.newLine();
			wr.write(toWrite);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used by the server to initiate the RMI registry.
	 * 
	 * @param PortNumber
	 *            - The port number on which the registry is to be initiated
	 * @throws RemoteException
	 *             - If there is no existing registry at the port an new
	 *             registry is created.
	 */
	@SuppressWarnings("unused")
	public static void initRegistry(int PortNumber) throws RemoteException {
		try {
			Registry register = LocateRegistry.getRegistry(PortNumber);
			register.list();
		} catch (RemoteException e) {
			System.out.println("Cannot register to the port");
			Registry register = LocateRegistry.createRegistry(PortNumber);
			System.out.println("New Registery is created");
		}
	}
}
