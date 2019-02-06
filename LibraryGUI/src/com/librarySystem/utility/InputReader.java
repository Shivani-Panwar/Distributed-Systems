package com.librarySystem.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
	
	private static BufferedReader input;
	
	public static BufferedReader getReader(){
		if(input != null){
			return input;
		}
		
		input = new BufferedReader(new InputStreamReader(System.in));
		return input;
	}
	
	public static void closeReader(){
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
