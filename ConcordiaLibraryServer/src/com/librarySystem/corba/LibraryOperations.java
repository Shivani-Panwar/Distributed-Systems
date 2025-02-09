package com.librarySystem.corba;


/**
* corba/LibraryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* Sunday, 3 March, 2019 1:18:42 AM IST
*/

public interface LibraryOperations 
{
  boolean addItem (String managerID, String itemID, String itemName, int quantity);
  boolean removeItem (String managerID, String itemID, int quantity);
  String listItemAvailability (String managerID);
  String borrowItem (String userID, String itemID, int numberOfDays);
  String findItem (String userID, String itemName);
  boolean returnItem (String userID, String itemID);
  boolean addToQueue (String itemID, String userID);
  boolean exchangeItem (String userID, String oldItem, String newItem);
  boolean checkAvailability (String itemID);
  boolean checkBorrowPossible (String userID, String itemID);
  boolean checkReturnPossible (String userID, String itemID);
  void shutdown ();
} // interface LibraryOperations
