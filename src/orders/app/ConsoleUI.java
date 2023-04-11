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
			String skip = scanner.next();
			while (choice < 1 || choice > 7) {
				System.out.println("Invalid choice.");
				System.out.print("Enter your choice (1-7): ");
				choice = scanner.nextInt();
				// Clear ENTER key after integer input
				skip = scanner.next();
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
		int choice = scanner.nextInt();
		String skip = scanner.nextLine();
		
		if(choice == 1) {
			
		}
		else if (choice ==2) {
			
		}
		else {
			System.out.println("Invalid input");
		}
			
		
		
	}
}
