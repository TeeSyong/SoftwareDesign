package orders.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DataLists implements IDataStore{

	private List<Item> items;
	
	private List<Order> orders;
	
	private List<String> orderIdList;
	
	public DataLists(){
		
		items = new ArrayList<Item>();
		orders = new ArrayList<Order>();
		orderIdList = new ArrayList<String>();
	}
	
	
	
	
	
	
	// -------------------------------------------------------------------------------
	// Put your text file under file folder (src->order->file)
	// Write your file location here for further reference
	// cz - "C:\\Github folder\\SoftwareDesign\\src\\orders\\file\\*.txt"
	// sy - 
	// yl - 
	// bong - 
	// alex - 
	// -------------------------------------------------------------------------------
	public void openOrderFile() {
		ArrayList<String[]> linesRead = new ArrayList<String[]>();
		String fileName = "order.txt";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File("C:\\Github folder\\SoftwareDesign\\src\\orders\\file\\order.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening the file "+fileName);
			System.exit(0);
		}
		
		while(inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split(",");
			linesRead.add(tokens);
		}
		
		Order anOrder;
		for(String[] strArray : linesRead) {
			anOrder = new Order(strArray[0],strArray[1],strArray[2],
					Integer.parseInt(strArray[3]),strArray[4]);
			orders.add(anOrder);
		}
	}
	
	
	public int getNumberOfOrders() {
		return orders.size();
	}
	
	public List<Order> getAllOrders() {
		return orders;
	}
	
	// filter duplicated orderId to get order id list
	public List<String> getOrderIdList(){
		Order aOrder;
		int count = getNumberOfOrders(); 
		for(int i=0; i<count; i++) {
			aOrder = orders.get(i);
			if (! orderIdList.contains(aOrder.getOrderId())) {
				orderIdList.add(aOrder.getOrderId());
			}
		}
		return orderIdList;
	}
	
	
	
}
