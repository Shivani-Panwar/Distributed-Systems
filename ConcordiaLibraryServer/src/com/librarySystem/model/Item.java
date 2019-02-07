package com.librarySystem.model;

import java.io.Serializable;

/**
 * This class represents the item and states its ID, name and quantity.
 * It will be used in the HashMap as the value.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8299587761233943244L;
	private String ID;
	private String name;
	private int quantity;
	
	public Item(){
		
	}
	
	public Item (String ID, String name, int quantity){
		this.ID = ID;
		this.name = name;
		this.quantity = quantity;
	}
	
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		this.ID = iD;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
