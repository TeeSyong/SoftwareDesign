package orders.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataLists implements IDataStore {

	private List<Item> items;

	private List<Order> orders;

	private List<Order> tempOrders;

	private List<String> orderIdList;

	public DataLists() {

		items = new ArrayList<Item>();
		orders = new ArrayList<Order>();
		orderIdList = new ArrayList<String>();
		tempOrders = new ArrayList<Order>();

	}

	// -------------------------------------------------------------------------------
	// Put your text file under file folder (src->order->file)
	// Write your file location here for further reference
	// cz - "C:\\Github folder\\SoftwareDesign\\src\\orders\\file\\*.txt"
	// sy
	// -"D:\\Users\\Desktop\\SoftwareDesign\\SoftwareDesign\\src\\orders\\file\\menu.txt"
	// yl - "C:\\Users\\Huawei\\Desktop\\Software Testing & Software Design
	// Eclipse\\Software Design
	// Practical\\SoftwareDesign\\src\\orders\\file\\orders.txt"
	// bong - C:\Users\bongq\Documents\GitHub\SoftwareDesign\src\orders\file\txt
	// allex -
	// -------------------------------------------------------------------------------

	// create User
	// function used in create user profile to check whether the username exists or
	// not
	public boolean checkUserName(String username) {

		try {
			String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\user.txt";
			File myFile = new File(fileName);
			Scanner reader = new Scanner(myFile);

			while (reader.hasNextLine()) {
				String existUser = reader.nextLine();
				String[] tokens = existUser.split(",");

				if (tokens[1].equals(username)) {
					return true;
				}
			}

		} catch (FileNotFoundException ex) {
			System.out.println("An error occurred.");
		}
		return false;
	}

	// function used in create user profile to verify the username and password
	// input by the user
	public boolean verifyLoign(String username, String password) {

		try {
			String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\user.txt";
			File myFile = new File(fileName);
			Scanner reader = new Scanner(myFile);
			while (reader.hasNextLine()) {
				String existUser = reader.nextLine();
				String[] tokens = existUser.split(",");

				if (tokens[1].equals(username) && tokens[2].equals(password)) {
					System.out.println("user login successful");
					return true;
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("An error occurred.");
		}
		return false;
	}

	// function used in create user profile to create the user which write into txt
	// file
	public void createUser(String userName, String password) {
		try {
			int min = 10000000;
			int max = 100000000;
			int memberID = (int) (Math.random() * (max - min + 1) + min);
			String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\user.txt";

			File myFile = new File(fileName);
			FileWriter writer = new FileWriter(fileName, true);
			Scanner reader = new Scanner(myFile);
			String user = null;
			while (reader.hasNextLine()) {
				String existUser = reader.nextLine();
				String[] tokens = existUser.split(",");

				if (tokens[0].equals(Integer.toString(memberID))) {
					memberID = (int) (Math.random() * (max - min + 1) + min);
				}
			}
			user = Integer.toString(memberID).concat(",").concat(userName).concat(",").concat(password);

			writer.write(user);
			writer.write("\n");
			writer.close();
			System.out.println("User profile created successfully.");
			System.out.println("Your member ID is:" + memberID);
		} catch (IOException ex) {
			System.out.println("An error occurred.");
		}
	}

	public void openOrderFile() {
		ArrayList<String[]> linesRead = new ArrayList<String[]>();
		String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\order.txt";
		;
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}

		while (inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split(",");
			linesRead.add(tokens);
		}

		Order anOrder;
		for (String[] strArray : linesRead) {
			anOrder = new Order(strArray[0], strArray[1], strArray[2], Integer.parseInt(strArray[3]), strArray[4]);
			orders.add(anOrder);
		}
		inputStream.close();
	}

	public int getNumberOfOrders() {
		return orders.size();
	}

	public List<Order> getAllOrders() {
		return orders;
	}

	// filter duplicated orderId to get order id list
	public List<String> getOrderIdList() {
		Order aOrder;
		int count = getNumberOfOrders();
		for (int i = 0; i < count; i++) {
			aOrder = orders.get(i);
			if (!orderIdList.contains(aOrder.getOrderId())) {
				orderIdList.add(aOrder.getOrderId());
			}
		}
		return orderIdList;
	}

	// View Menu Code
	public ArrayList<String[]> readMenu() {
		ArrayList<String[]> linesRead = new ArrayList<String[]>();
		String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\menu.txt";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}

		while (inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split(",");
			linesRead.add(tokens);
		}
		inputStream.close();

		return linesRead;
	}

	public ArrayList<HashMap<String, String>> menuInDictionary() {
		ArrayList<String[]> linesRead = readMenu();
		ArrayList<HashMap<String, String>> MenuArr = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < linesRead.size(); i++) {
			HashMap<String, String> MenuDict = new HashMap<String, String>();

			MenuDict.put("Code", linesRead.get(i)[0]);
			MenuDict.put("Name", linesRead.get(i)[1]);
			MenuDict.put("Price", linesRead.get(i)[2]);
			MenuArr.add(MenuDict);
		}

		return MenuArr;

	}

	public void printMenu() {
		ArrayList<HashMap<String, String>> linesRead = menuInDictionary();
		boolean isPrintedBurger = false;
		boolean isPrintedSides = false;
		boolean isPrintedDrinks = false;
		System.out.println("MENU");
		System.out.println("-------------");

		for (int i = 0; i < linesRead.size(); i++) {

			if (!isPrintedBurger) {
				if (linesRead.get(i).get("Code").charAt(0) == 'B') {
					System.out.println("BURGERS");
					System.out.println("--------");
					System.out.println("Item Code\t Name\t\t Price");
					System.out.println("-------------------------------------------");
					isPrintedBurger = true;
				}
			}
			if (!isPrintedSides) {
				if (linesRead.get(i).get("Code").charAt(0) == 'S') {
					System.out.println("SIDES");
					System.out.println("--------");
					System.out.println("Item Code\t Name\t\t Price");
					System.out.println("-------------------------------------------");
					isPrintedSides = true;
				}
			}

			if (!isPrintedDrinks) {
				if (linesRead.get(i).get("Code").charAt(0) == 'D') {
					System.out.println("DRINKS");
					System.out.println("--------");
					System.out.println("Item Code\t Name\t\t Price");
					System.out.println("-------------------------------------------");
					isPrintedDrinks = true;
				}
			}
			System.out.print(linesRead.get(i).get("Code") + "\t\t");
			System.out.print(linesRead.get(i).get("Name") + "\t");
			System.out.println(linesRead.get(i).get("Price") + "\t ");

		}
	}

	public ArrayList<HashMap<String, String>> findKeywords(String keyword) {
		ArrayList<HashMap<String, String>> linesRead = menuInDictionary();
		ArrayList<HashMap<String, String>> matchesItem = new ArrayList<HashMap<String, String>>();

		int keywordLength = keyword.length();
		int minimumMatchScore = keywordLength < 2 ? keywordLength : 2;
		for (int i = 0; i < linesRead.size(); i++) {
			String item = linesRead.get(i).get("Name");
			String cleanedItem = item.toLowerCase().trim().replace(" ", "");
			int matchScore = 0;
			int keywordIndex = 0;
			for (int j = 0; j < cleanedItem.length(); j++) {
				if (cleanedItem.charAt(j) == keyword.charAt(keywordIndex)) {
					matchScore++;
					keywordIndex++;
					if (keywordIndex == keywordLength) {
						break;
					}
				}
			}
			if (matchScore > minimumMatchScore) {
				matchesItem.add(linesRead.get(i));
			}
		}
		return matchesItem;
	}

	public void printKeywordMenu(String keywords) {
		ArrayList<HashMap<String, String>> linesRead = findKeywords(keywords);
		System.out.println("MENU");
		System.out.println("-------------");
		System.out.println("Item Code\t Name\t\t Price");
		System.out.println("-------------------------------------------");

		for (int i = 0; i < linesRead.size(); i++) {
			System.out.print(linesRead.get(i).get("Code") + "\t\t");
			System.out.print(linesRead.get(i).get("Name") + "\t");
			System.out.println(linesRead.get(i).get("Price") + "\t ");

		}
	}

	// create order
	public void createTempOrder(String itemCode, int quantity, String remark) {
		String menuFile = System.getProperty("user.dir") + "\\src\\orders\\file\\menu.txt";
		File myMenu = new File(menuFile);

		String orderId, foodName = null;

		Scanner reader;
		try {

			reader = new Scanner(myMenu);
			while (reader.hasNextLine()) {
				String menu = reader.nextLine();
				String[] tokens = menu.split(",");
				if (tokens[0].equals(itemCode)) {
					foodName = tokens[1];
				}
			}

			Order anOrder = new Order(itemCode, foodName, quantity, remark);
			tempOrders.add(anOrder);
			System.out.println("\nItem Code\tName\t\tQty\t\tRemarks");
			System.out.println("----------------------------------------");
			for (int i = 0; i < tempOrders.size(); i++) {
				System.out.println(tempOrders.get(i).getItemCode() + "\t\t" + tempOrders.get(i).getFoodName() + "\t"
						+ tempOrders.get(i).getQuantity() + "\t\t" + tempOrders.get(i).getRemark());
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	public void createOrder() {
		try {
			String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\order.txt";
			File myFile = new File(fileName);
			FileWriter writer;
			writer = new FileWriter(fileName, true);

			int highestIdNum = 0;
			String orderId = null;
			ArrayList<Integer> id = new ArrayList<Integer>();

			Scanner reader;
			reader = new Scanner(myFile);
			while (reader.hasNextLine()) {
				String order = reader.nextLine();
				String[] tokens = order.split(",");
				int idNum = Integer.parseInt(tokens[0].replace("OD", ""));
				id.add(idNum);
				highestIdNum = Collections.max(id);
			}

			if (highestIdNum + 1 < 10) {
				orderId = "OD0".concat(Integer.toString(highestIdNum + 1));
			} else {
				orderId = "OD".concat(Integer.toString(highestIdNum + 1));
			}
			Order anOrder;
			for (int i = 0; i < tempOrders.size(); i++) {
				anOrder = new Order(orderId, tempOrders.get(i).getItemCode(), tempOrders.get(i).getFoodName(),
						tempOrders.get(i).getQuantity(), tempOrders.get(i).getRemark());
				orders.add(anOrder);
				String order = orderId.concat(",").concat(tempOrders.get(i).getItemCode()).concat(",")
						.concat(tempOrders.get(i).getFoodName()).concat(",")
						.concat(Integer.toString(tempOrders.get(i).getQuantity())).concat(",")
						.concat(tempOrders.get(i).getRemark());
				writer.write(order);
				writer.write("\n");

			}
			writer.close();

			System.out.println("Order successfully added to system.");
			System.out.println("Your order number is " + orderId);

		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	// View Order Code

	// For Yee Lin view order use purpose :)
	public ArrayList<String[]> openOrderFileV2() {
		ArrayList<String[]> linesRead = new ArrayList<String[]>();
		String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\order.txt";
		
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}

		while (inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split(",");
			linesRead.add(tokens);
		}
		inputStream.close();
		return linesRead;
	}

	public void addOrderToList() {
		ArrayList<String[]> linesRead = new ArrayList<String[]>();
		linesRead = openOrderFileV2();
		Order anOrder;
		for (String[] strArray : linesRead) {
			anOrder = new Order(strArray[0], strArray[1], strArray[2], Integer.parseInt(strArray[3]), strArray[4]);
			orders.add(anOrder);
		}
	}

	public void printOrder(String orderId) {
		int count = this.getNumberOfOrders();
		Order aOrder;
		System.out.println("Item Code\tName\tQtt\tRemarks");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < count; i++) {
			aOrder = orders.get(i);

			if (orderId.equals(aOrder.getOrderId())) {
				System.out.printf("%s\t\t%-20s\t%d\t%s\n", aOrder.getItemCode(), aOrder.getFoodName(),
						aOrder.getQuantity(), aOrder.getRemark());
			}
		}
		System.out.println("\n");
	}

	public void writeOrderFile() {
		// write into order.txt text file; modifyOrderFile()

		String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\order.txt";
		PrintWriter outputFile = null;
		try {
			outputFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {

			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		for (int k = 0; k < orders.size(); k++) {
			outputFile.println(
					orders.get(k).getOrderId() + "," + orders.get(k).getItemCode() + "," + orders.get(k).getFoodName()
							+ "," + orders.get(k).getQuantity() + "," + orders.get(k).getRemark());
		}
		outputFile.close();

	}

	public void updateOrder(String orderId, String itemCode, int qtt, String remarks) {

		// update item
		Order aOrder = null;

		for (int i = 0; i < orders.size(); i++) {
			aOrder = orders.get(i);

			if (orderId.equals(aOrder.getOrderId()) && itemCode.equals(aOrder.getItemCode())) {
				aOrder.setQuantity(qtt);
				aOrder.setRemark(remarks);
			}
		}
		writeOrderFile();
	}

	public void deleteOrder(String orderId, String itemCode) {
		// delete item

		for (int i = 0; i < orders.size(); i++) {

			if (orderId.equals(orders.get(i).getOrderId()) && itemCode.equals(orders.get(i).getItemCode())) {
				orders.remove(i);
			}
		}
		writeOrderFile();
	}

	public void addOrder(String orderId, String itemCode, int qtt, String remarks) {
		ArrayList<HashMap<String, String>> MenuArr = menuInDictionary();
		String itemName = null;
		for (int i = 0; i < MenuArr.size(); i++) {
			if (itemCode.equals(MenuArr.get(i).get("Code"))) {
				itemName = MenuArr.get(i).get("Name");
			}
		}
		// add item
		Order aOrder = new Order(orderId, itemCode, itemName, qtt, remarks);
		orders.add(aOrder);
		writeOrderFile();
	}

	// view invoice code
	public void readFromFile(ArrayList<ArrayList<String>> itemsList, String orderNum) {
		String fileName = System.getProperty("user.dir") + "\\src\\orders\\file\\order.txt";
		try {
			File myObj = new File(fileName); 
			Scanner myReader = new Scanner(myObj);
			String[] Input = new String[7];
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				
				Input = data.split("\t");
				
				String itemCode = Input[1];
				int qtt = Integer.parseInt(Input[3]);
				
				//read menu.txt
				ArrayList<String[]> menuLinesRead = readMenu();
				
				//get unit price based on item code
				// menuLinesRead = ["BG01","Chicken Burger","9.00"],[],[]
				double unitPrice = 0;
				for(int i = 0; i < menuLinesRead.size(); i++) {
					if(itemCode.equals(menuLinesRead.get(i)[0])) {
						unitPrice = Double.parseDouble(menuLinesRead.get(i)[2]);
					}
				}
				
				//calculate total price (unit price * quantity)
				double totalPrice = unitPrice * qtt;
				
				//addPriceToInput(String[] Input);
				
				Input[6] = Double.toString(unitPrice);
				Input[7] = Double.toString(totalPrice);
			}
				
				
			ArrayList<String> datalist = new ArrayList<>();
			if (Input.length > 0 && Input[0].equals(orderNum)) {
				// Add remaining data to ArrayList
				for (String input : Input) {
					datalist.add(input);
				}
			}
				itemsList.add(datalist);
			
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	

	public double sumValue(ArrayList<ArrayList<String>> itemsList) {
		double total = 0;
		for (ArrayList<String> items : itemsList) {
			total += Double.parseDouble(items.get(6));
		}
		return total;
	}

	public double checkMembership(String membership, double total) {
		double discount = 0;
		if (membership.equals("y") || membership.equals("Y")) {
			discount = total * 0.1;
			// assumption : 10 % offer will be given to member
		}
		return discount;
	}
	
	public double computeDiscountedTotal(double total, double discount) {
		return total - discount;
	}
}
