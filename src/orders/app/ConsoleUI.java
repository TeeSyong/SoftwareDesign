package orders.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import orders.domain.*;

public class ConsoleUI {
	private Scanner scanner;
	private Controller controller;
	private String opt;

	public ConsoleUI() {
		scanner = new Scanner(System.in);
		
	}
	public void setController(Controller controller)
	{
		this.controller= controller;
	}

	public void start() {
		int choice;
		do {
			System.out.println("Do you want to:");
			System.out.println("1. CREATE USER PROFILE");
			System.out.println("2. VIEW MENU");
			System.out.println("3. CREATE ORDER");
			System.out.println("4. VIEW ORDER");
			System.out.println("5. VIEW INVOICE ");
			System.out.println("6. CHECK ORDER");
			System.out.println("7. Exit");
			System.out.print("Enter your choice (1-7): ");
			choice = scanner.nextInt();
			// Clear ENTER key after integer input
			String skip = scanner.nextLine();
			while (choice < 1 || choice > 7) {
				System.out.println("Invalid choice.");
				System.out.print("Enter your choice (1-7): ");
				choice = scanner.nextInt();
				// Clear ENTER key after integer input
				skip = scanner.nextLine();
				System.out.println("\n");
			}
			switch (choice) {
			case 1:
				createUserProfile();
				break;
			case 2:
				viewMenu();
				break;
			case 3:
				createOrder();
				break;
			case 4:
				viewOrder();
				break;
			case 5:
				viewInvoice();
				break;
			case 6:
				checkOrder();
				break;
			case 7:
				System.out.println("Terminating system...");
				break;
			}
			System.out.println();
		} while (choice != 7);
	}
	
	
	//WRITE THE FULL CODE FUNCTION HERE
	
	//CREATE USER PROFILE 
	
	public void createUserProfile() {

		
		String opt = null;
		
		do
		{
			System.out.println();
			System.out.print("Enter username: ");
			String username = scanner.nextLine().toUpperCase();
			System.out.print("Enter password: ");
			String password = scanner.nextLine().toUpperCase();
			
			if (controller.checkUserName(username))
			{	
				System.out.println();
				System.out.println("Current username is already taken.");
				System.out.print("Do you want to create another user profile? (Y/N): ");

				opt = scanner.nextLine().toUpperCase();

			}
			else
			{	
				System.out.println();
				controller.createUser(username,password);
				opt = "N";
			}	

			}while(opt.equals("Y"));

		if (opt.equals("N"))
		{	
			System.out.println();
			System.out.println("Press Enter to return to main menu");
			scanner.nextLine();
		}
		else
		{
			System.out.println("Error Option");
		}
	}
	
	
	//VIEW MENU
	
	public void viewMenu() {
		controller.printMenu();
		
		boolean searchChoiceInvalid;
		boolean searchAgain;
		do {
			searchChoiceInvalid = false;
			searchAgain = false;
			
			System.out.print("Do you want to search items in menu? (Y/N): ");
			String searchChoice = scanner.nextLine().toUpperCase();
			
			if(searchChoice.equals("Y")) {
				System.out.print("Enter keyword or phrases to search: ");
				String keywords = scanner.nextLine().toLowerCase();
				System.out.println();
				controller.printKeywordMenu(keywords);
				searchAgain = true;
			}
			
				
			else if(searchChoice.equals("N")) {
				break;
				
			} else {
				System.out.println("Input error. Please enter again.");
				searchChoiceInvalid = true;
			}
		} while(searchChoiceInvalid || searchAgain);
		
		boolean orderChoiceInvalid;
		do {
			orderChoiceInvalid = false;
			System.out.print("\nDo you want to make order? (Y/N): ");
			String orderChoice = scanner.nextLine().toUpperCase();
			
			if(orderChoice.equals("Y")) {
				createOrder();
			}else if(orderChoice.equals("N")) {
				System.out.print("Press Enter to return to main menu");
				scanner.nextLine();
			}else {
				System.out.println("Input error. Please enter again.");
				orderChoiceInvalid = true;
			}
		} while(orderChoiceInvalid);
	}
	
	//CREATE ORDER
	
	public void createOrder() {
		
	}
	
	//VIEW ORDER
	public void viewOrder() {		

		String orderId = getOrderIdInput();
		//controller.openOrderFile();
		controller.printOrder(orderId);
		boolean invalidChoice = false;
		String choice;
		System.out.print("Do you want to modify your order? (Y/N): ");
		do {
			choice = scanner.next().toUpperCase();
			if(choice.equals("Y")) {	
				boolean invalidOperation = false;
				do {
					System.out.println("1) Modify item");
					System.out.println("2) Delete item");
					System.out.println("3) Add item");
					System.out.print("Please select an operation that you would like to do: ");
					int operation = scanner.nextInt(); 
					scanner.nextLine(); //skip
					
					switch(operation) {
					case 1:
						String codeToModify, remarksToModify;
						int qttToModify;
						
						
						codeToModify = getItemCodeInput();
						System.out.print("Enter quantity           : ");
						qttToModify = scanner.nextInt();
						scanner.nextLine(); //skip
						System.out.print("Enter remarks (- if none): ");
						remarksToModify = scanner.nextLine();
						
						controller.updateOrder(orderId, codeToModify, qttToModify, remarksToModify);
						controller.printOrder(orderId);
						
						
						break;
					case 2:
						String codeToDel =getItemCodeInput();
						
						controller.deleteOrder(orderId, codeToDel);
						controller.printOrder(orderId);
						
						break;
					case 3:
						controller.printMenu();
						
						String codeToAdd = getItemCodeInput();
						System.out.print("Enter quantity           : ");
						int qttToAdd = scanner.nextInt();
						scanner.nextLine(); //skip
						System.out.print("Enter remarks (- if none): ");
						String remarksToAdd = scanner.nextLine();
						
						controller.addOrder(orderId,codeToAdd,qttToAdd,remarksToAdd);
						controller.printOrder(orderId);
						
					
						break;
					default:
						System.out.println("Invalid input. Please select again");
						invalidOperation = true;
						break;
					}
					System.out.print("Do you want to modify your order? (Y/N): ");
					choice = scanner.next().toUpperCase();
				} while (invalidOperation || choice.equals("Y"));

			} else if(choice.equals("N")) {
				System.out.println("Press Enter to return to main menu");
				scanner.nextLine();
			} else {
				System.out.println("Invalid input. Please select again");
				invalidChoice = true;
			}

		} while(invalidChoice);
		

	}
	//VIEW INVOICE
	public void viewInvoice() {
		controller.viewinvoice();
	}
	
	
	//CHECK ORDER
	
	public void checkOrder() {
		
		System.out.println("You want to:");
		System.out.println("1. Check all orders");
		System.out.println("2. Search order");
		System.out.print("Enter your choice (1-2): ");
		int choice = scanner.nextInt();
		String skip = scanner.nextLine();
		System.out.println("\n");
		while(choice < 1 || choice > 2) {
			System.out.println("Invalid choice.");
			System.out.print("Enter your choice (1-2): ");
			choice = scanner.nextInt();
			skip = scanner.nextLine();
			System.out.println("\n");
		}
		
		controller.openOrderFile();
		int count = controller.getNumberOfOrders();
		List<Order> orders = controller.getAllOrders();
		List<String> orderIdList = controller.getOrderIdList();
		
		Order aOrder;
		if(choice == 1) {
			if(count==0)
				System.out.println("No orders to display");
			else {
				
				for(int i=0; i<orderIdList.size();i++) {
					System.out.println("Order ID: " +orderIdList.get(i)+"\n");
					System.out.println("Item Code\tName\t\t\tQtt\tRemarks");
					System.out.println("-----------------------------------------------");
					for(int j=0; j<count; j++) {
						aOrder = orders.get(j);
						
						if(orderIdList.get(i).equals(aOrder.getOrderId())) {
							System.out.printf("%s\t\t%-20s\t%d\t%s\n",aOrder.getItemCode(),aOrder.getFoodName()
									, aOrder.getQuantity(), aOrder.getRemark());
							
							//System.out.println(aOrder.getItemCode() + "\t\t" + aOrder.getFoodName() +"\t"
							//		+ "\t" + aOrder.getQuantity() + "\t" + aOrder.getRemark());
						}
						
					}
					System.out.println("\n");
				}
			}
		}
		else if (choice == 2) {
			boolean loop = false;
			do {
				
				System.out.println("Order ID\n--------");
				for(int i=0;i<orderIdList.size();i++) {
					System.out.println(orderIdList.get(i));
				}
				System.out.println("\n");
				System.out.print("Enter Order ID to search: ");
				String orderID = scanner.nextLine();
				System.out.println("\n");
				while(!orderIdList.contains(orderID)) {
					System.out.println("Order ID do not exist.");
					System.out.print("Enter Order ID to search: ");
					orderID = scanner.nextLine();
					System.out.println("\n");
				}
				
				System.out.println("Order ID: " +orderID +"\n");
				System.out.println("Item Code\tName\t\tQtt\tRemarks");
				System.out.println("-----------------------------------------------");
				for(int j=0; j<count; j++) {
					aOrder = orders.get(j);
					
					if(orderID.equals(aOrder.getOrderId())) {
						System.out.printf("%s\t%25s\t%d\t%s\n",aOrder.getItemCode(),aOrder.getFoodName()
								, aOrder.getQuantity(), aOrder.getRemark());
					}
				}
				System.out.println("\n");
				System.out.print("Search order ID again?(Y/N): ");
				String opt = scanner.nextLine();
				System.out.println("\n");
				if(opt.toUpperCase().equals("Y"))
					loop = true;
				else
					loop = false;
				
			}while(loop);
			
		}
		orders.clear();
		
	}
	
	public String getOrderIdInput() {
		List<Order> orders = controller.getAllOrders();
		String orderId;
		boolean isFound = false;
		do {
			System.out.print("Enter order number: ");
			orderId = scanner.nextLine();
			for(int i=0;i<orders.size();i++)
			{
				if(orderId.equals(orders.get(i).getOrderId())) {
					isFound=true;
					break;
				}
			}
			
			if(!isFound) {
				System.out.println("No orders ID found");	
			}
		}while(!isFound);
		return orderId;
		
	}
	
	public String getItemCodeInput() {
		List<Order> orders = controller.getAllOrders();
		String itemCode;
		boolean isFound = false;
		do {
			System.out.print("Enter item code: ");
			itemCode = scanner.nextLine();
			for(int i=0;i<orders.size();i++)
			{
				if(!(itemCode.equals(orders.get(i).getItemCode()))) {
					isFound=true;
					break;
				}
			}
			
			if(!isFound) {
				System.out.println("No item code found");	
			}
		}while(!isFound);
		return itemCode;
		
	}
}
