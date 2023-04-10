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
				//create user profile
				break;
			case 2:
				//view menu
				break;
			case 3:
				//create order
				break;
			case 4:
				//view order
				break;
			case 5:
				controller.viewinvoice();
				break;
			case 6:
				//check order
				break;
			case 7:
				break;
			}
			System.out.println();
		} while (choice != 7);
	}
	
	
	//WRITE THE FULL CODE FUNCTION HERE
	
	//CREATE USER PROFILE 
	
	
	
	
	//VIEW MENU
	
	
	
	//CREATE ORDER
	
	
	
	//VIEW ORDER
	
	
	
	//VIEW INVOICE
	
	
	
	//CHECK ORDER
}
