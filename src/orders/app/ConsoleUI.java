package orders.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import orders.domain.*;

public class ConsoleUI {
	private Scanner scanner;
	private Controller controller;

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
			System.out.println("\n");
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
				break;
			}
			System.out.println();
		} while (choice != 7);
	}
	
	
	//WRITE THE FULL CODE FUNCTION HERE
	
	//CREATE USER PROFILE 
	
	public void createUserProfile() {
		
	}
	
	
	//VIEW MENU
	
	public void viewMenu() {
		
	}
	
	//CREATE ORDER
	
	public void createOrder() {
		
	}
	
	//VIEW ORDER
	
	
	public void viewOrder() {
		
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
					System.out.println("Item Code\tName\t\tQtt\tRemarks");
					System.out.println("-----------------------------------------------");
					for(int j=0; j<count; j++) {
						aOrder = orders.get(j);
						
						if(orderIdList.get(i).equals(aOrder.getOrderId())) {
							System.out.printf("%s\t\t%s\t%d\t%s\n",aOrder.getItemCode(),aOrder.getFoodName()
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
						System.out.printf("%s\t\t%s\t%d\t%s\n",aOrder.getItemCode(),aOrder.getFoodName()
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
}
