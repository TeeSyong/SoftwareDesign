package orders.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DataLists implements IDataStore {

	private List<Item> items;

	private List<Order> orders;

	private List<String> orderIdList;

	public DataLists() {

		items = new ArrayList<Item>();
		orders = new ArrayList<Order>();
		orderIdList = new ArrayList<String>();
	}

	// -------------------------------------------------------------------------------
	// Put your text file under file folder (src->order->file)
	// Write your file location here for further reference
	// cz - "C:\\Github folder\\SoftwareDesign\\src\\orders\\file\\*.txt"
	// sy -
	// "D:\\Users\\Desktop\\SoftwareDesign\\SoftwareDesign\\src\\orders\\file\\menu.txt"
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
		String fileName = "order.txt";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(
					new File("D:\\Users\\Desktop\\SoftwareDesign\\SoftwareDesign\\src\\orders\\file\\menu.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}

		while (inputStream.hasNextLine()) {
			String singleLine = inputStream.nextLine();
			String[] tokens = singleLine.split(",");
			linesRead.add(tokens);
		}

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
		boolean isPrintedBurger= false;
		boolean isPrintedSides = false;
		boolean isPrintedDrinks = false;
		System.out.println("MENU");
		System.out.println("-------------");


		for (int i = 0; i < linesRead.size(); i++) {
			
			if(!isPrintedBurger) {
				if (linesRead.get(i).get("Code").charAt(0) == 'B') { 
					System.out.println("BURGERS");
					System.out.println("--------");
					System.out.println("Item Code\t Name\t\t Price");
					System.out.println("-------------------------------------------");
					isPrintedBurger=true;
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

		int bestMatchScore = 0;
		for (int i = 0; i < linesRead.size(); i++) {
			String item = linesRead.get(i).get("Name");
			String cleanedItem = item.toLowerCase().trim();
			int matchScore = 0;
			int keywordIndex = 0;
			for (int j = 0; j < cleanedItem.length(); j++) {
				if (cleanedItem.charAt(j) == keyword.charAt(keywordIndex)) {
					matchScore++;
					keywordIndex++;
					if (keywordIndex == keyword.length()) {
						break;
					}
				}
			}
			if (matchScore > bestMatchScore) {
				bestMatchScore = matchScore;
				matchesItem.clear();
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

}
