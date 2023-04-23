package orders.domain;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
	private String address;
	private int userId;
	private String password;
	private String registryDate;
	
	public int getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRegistryDate() {
		return registryDate;
	}



}
