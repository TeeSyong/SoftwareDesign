package orders.app;

import java.io.File;
import java.io.FileNotFoundException;
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

	public void setController(Controller controller) {
		this.controller = controller;
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

	// WRITE THE FULL CODE FUNCTION HERE

	// CREATE USER PROFILE

	public void createUserProfile() {

		String opt = null;

		do {
			System.out.println();
			System.out.print("Enter username: ");
			String username = scanner.nextLine().toUpperCase();
			System.out.print("Enter password: ");
			String password = scanner.nextLine().toUpperCase();

			if (controller.checkUserName(username)) {
				System.out.println();
				System.out.println("Current username is already taken.");
				System.out.print("Do you want to create another user profile? (Y/N): ");

				opt = scanner.nextLine().toUpperCase();

			} else {
				System.out.println();
				controller.createUser(username, password);
				opt = "N";
			}

		} while (opt.equals("Y"));

		if (opt.equals("N")) {
			System.out.println();
			System.out.println("Press Enter to return to main menu");
			scanner.nextLine();
		} else {
			System.out.println("Error Option");
		}
	}

	// VIEW MENU

	public void viewMenu() {
		controller.printMenu();

		boolean searchChoiceInvalid;
		boolean searchAgain;
		do {
			searchChoiceInvalid = false;
			searchAgain = false;

			System.out.print("Do you want to search items in menu? (Y/N): ");
			String searchChoice = scanner.nextLine().toUpperCase();

			if (searchChoice.equals("Y")) {
				System.out.print("Enter keyword or phrases to search: ");
				String keywords = scanner.nextLine().toLowerCase();
				System.out.println();
				controller.printKeywordMenu(keywords);
				searchAgain = true;
			}

			else if (searchChoice.equals("N")) {
				break;

			} else {
				System.out.println("Input error. Please enter again.");
				searchChoiceInvalid = true;
			}
		} while (searchChoiceInvalid || searchAgain);
	}

	// CREATE ORDER

	public void createOrder() {
		System.out.println("CREATE ORDER \r\n" + "\r\n" + "--------------------------------- ");
		String opt = "Y";
		controller.printMenu();
		
		boolean isValid = false;
		
		do {
			String itemCode = getItemCodeInputFromMenu();
			System.out.print("Enter quantity           : ");
			int quantity = scanner.nextInt();
			scanner.nextLine(); // consume newline leftOver
			System.out.print("Enter remarks (- if none): ");
			String remark = scanner.nextLine();

			controller.createTempOrder(itemCode, quantity, remark);

			do {
				System.out.print("\nAdd next item? (Y/N): ");
				opt = scanner.nextLine().toUpperCase();
				
				if(opt.equals("Y") || opt.equals("N")) {
					isValid = true;
				} else {
					System.out.println("Invalid input. Please enter again");
				}
			} while(!isValid);				
		} while (opt.equals("Y"));
		
		boolean invalid; 
		do {
			invalid = false;
			System.out.println("1) Confirm Order");
			System.out.println("2) Cancel Order");
			System.out.print("Please Enter Your Option: ");
			int createOrder = scanner.nextInt();
			if (createOrder == 1) {
				controller.createOrder();
				System.out.println("Press Enter to return to main menu ");
			}
	
			else if (createOrder == 2){
				controller.cancelOrder();
				System.out.println("Press Enter to return to main menu ");
				scanner.nextLine();
			}
			
			else {
				System.out.println("Invalid input. Please enter again\n");
				invalid = true;
			}
		} while(invalid);
	}

	// VIEW ORDER
	public void viewOrder() {
		List<String> orderIdList = controller.getOrderIdList();
		System.out.println("Order ID\n--------");
		for (int i = 0; i < orderIdList.size(); i++) {
			System.out.println(orderIdList.get(i));
		}
		System.out.println("\n");

		String orderId = getOrderIdInput();
		controller.printOrder(orderId);
		boolean invalidChoice = false;
		String choice;
		System.out.print("Do you want to modify your order? (Y/N): ");
		do {
			choice = scanner.next().toUpperCase();
			if (choice.equals("Y")) {
				boolean invalidOperation = false;
				do {
					System.out.println("1) Modify item");
					System.out.println("2) Delete item");
					System.out.println("3) Add item");
					System.out.print("Please select an operation that you would like to do: ");
					int operation = scanner.nextInt();
					scanner.nextLine(); // skip

					switch (operation) {
					case 1:
						String codeToModify, remarksToModify;
						int qttToModify;

						codeToModify = getItemCodeInputFromOrder();
						System.out.print("Enter quantity           : ");
						qttToModify = scanner.nextInt();
						scanner.nextLine(); // skip
						System.out.print("Enter remarks (- if none): ");
						remarksToModify = scanner.nextLine();

						controller.updateOrder(orderId, codeToModify, qttToModify, remarksToModify);
						controller.printOrder(orderId);

						break;
					case 2:
						String codeToDel = getItemCodeInputFromOrder();

						controller.deleteOrder(orderId, codeToDel);
						controller.printOrder(orderId);

						break;
					case 3:
						controller.printMenu();

						String codeToAdd = getItemCodeInputFromMenu();
						System.out.print("Enter quantity           : ");
						int qttToAdd = scanner.nextInt();
						scanner.nextLine(); // skip
						System.out.print("Enter remarks (- if none): ");
						String remarksToAdd = scanner.nextLine();

						controller.addOrder(orderId, codeToAdd, qttToAdd, remarksToAdd);
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

			} else if (choice.equals("N")) {
				System.out.println("Press Enter to return to main menu");
				scanner.nextLine();
			} else {
				System.out.println("Invalid input. Please select again");
				invalidChoice = true;
			}

		} while (invalidChoice);

	}

	// VIEW INVOICE
	public void viewInvoice() {
		double total,discount=0,discountedTotal=0;
		List<String> orderIdList = controller.getOrderIdList();
		System.out.println("Order ID\n--------");
		for (int i = 0; i < orderIdList.size(); i++) {
			System.out.println(orderIdList.get(i));
		}
		System.out.println("\n");

		String orderId = getOrderIdInput();
		
		String option;
		
		ArrayList<ArrayList<String>> orderList=controller.getOrderListWithByOrderNum(orderId);
		total=controller.calculateTotalOrderPrice(orderList);

		boolean hasMembership = false;
		do {
				System.out.print("Are you a member? (Y/N):");
				option = scanner.nextLine().toLowerCase();
				if (option.equals("y")) {
					boolean membershipValid =checkMemberIdInput();
				
					if(membershipValid) {
						 discount=controller.getDiscountPrice(total);
						 discountedTotal=controller.computeDiscountedTotal(total, discount);
						 hasMembership=true;
					}
					
				}else if(option.equals("n")) {
					break;
				}else {
					System.out.println("Invalid Input,Please enter Y/N");
				}
		} while (!hasMembership);

		
		System.out.println("Item Code\tName\t\t\tQtt\t\tRemarks\t\tPrice(RM)\tTotal Price(RM)");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		for (ArrayList<String> orders : orderList) {
			for (int i = 1; i < orders.size(); i++) {
				System.out.print(orders.get(i) + "\t\t");
			}
			System.out.println();
		}
		if(hasMembership) {
			System.out.println();
			System.out.println(String.format("Subtotal(RM)\t :%.2f", total));
			System.out.println(String.format("Member discount\t :%.2f", discount));
			System.out.println(String.format("Total(RM)\t :%.2f", discountedTotal));
			System.out.println(String.format("Rounded total(RM):%.2f", Math.ceil(discountedTotal / 0.10) * 0.10));
		}else {
			System.out.println();
			System.out.println(String.format("Subtotal(RM)\t :%.2f", total));
			System.out.println(String.format("Rounded total(RM):%.2f", Math.ceil(total)));
		}
		
		System.out.println("Press Enter to return to main menu");
		scanner.nextLine();
		

	}

	// CHECK ORDER

	public void checkOrder() {

		System.out.println("You want to:");
		System.out.println("1. Check all orders");
		System.out.println("2. Search order");
		System.out.print("Enter your choice (1-2): ");
		int choice = scanner.nextInt();
		String skip = scanner.nextLine();
		System.out.println("\n");
		while (choice < 1 || choice > 2) {
			System.out.println("Invalid choice.");
			System.out.print("Enter your choice (1-2): ");
			choice = scanner.nextInt();
			skip = scanner.nextLine();
			System.out.println("\n");
		}
		
		controller.openOrderFile();
		boolean AddedOrder;
		if(AddedOrder=false) {
			controller.addOrderToList();
			AddedOrder=true;
		}

		int count = controller.getNumberOfOrders();
		List<Order> ordersOriginal = controller.getAllOrders();
		List<Order> orders = new ArrayList<Order>(ordersOriginal);
		List<String> orderIdList = controller.getOrderIdList();

		Order aOrder;
		if (choice == 1) {
			if (count == 0)
				System.out.println("No orders to display");
			else {

				for (int i = 0; i < orderIdList.size(); i++) {
					System.out.println("Order ID: " + orderIdList.get(i) + "\n");
					System.out.println("Item Code\tName\t\t\tQtt\t\tRemarks");
					System.out.println("-------------------------------------------------------------------");
					for (int j = 0; j < count; j++) {
						aOrder = orders.get(j);

						if (orderIdList.get(i).equals(aOrder.getOrderId())) {
							System.out.printf("%s\t\t%s\t%d\t\t%s\n", aOrder.getItemCode(), aOrder.getFoodName(),
									aOrder.getQuantity(), aOrder.getRemark());

							// System.out.println(aOrder.getItemCode() + "\t\t" + aOrder.getFoodName() +"\t"
							// + "\t" + aOrder.getQuantity() + "\t" + aOrder.getRemark());
						}

					}
					System.out.println("\n");
				}
			}
		} else if (choice == 2) {
			boolean loop = false;
			do {

				System.out.println("Order ID\n--------");
				for (int i = 0; i < orderIdList.size(); i++) {
					System.out.println(orderIdList.get(i));
				}
				System.out.println("\n");
				String orderID=getOrderIdInput();

				System.out.println("Order ID: " + orderID + "\n");
				System.out.println("Item Code\tName\t\tQtt\t\tRemarks");
				System.out.println("-------------------------------------------------------------------");
				for (int j = 0; j < count; j++) {
					aOrder = orders.get(j);

					if (orderID.equals(aOrder.getOrderId())) {
						System.out.printf("%s\t\t%s\t%d\t\t%s\n", aOrder.getItemCode(), aOrder.getFoodName(),
								aOrder.getQuantity(), aOrder.getRemark());
					}
				}
				System.out.println("\n");
				
				boolean validInput;
				do {
					validInput = true;
					System.out.print("Search order ID again?(Y/N): ");
					String opt = scanner.nextLine();
					System.out.println("\n");
					if (opt.toUpperCase().equals("Y"))
						loop = true;
					else if (opt.toUpperCase().equals("N"))
						loop = false;
					else {
						System.out.println("Invalid input. Please enter again");
						validInput = false;
					}
				}while(!validInput);
			} while (loop);

		}
		//orders.clear();

	}

	public String getOrderIdInput() {
		List<Order> orders = controller.getAllOrders();
		String orderId;
		boolean isFound = false;
		do {
			System.out.print("Enter order number: ");
			orderId = scanner.nextLine().toUpperCase();
			for (int i = 0; i < orders.size(); i++) {
				if (orderId.equals(orders.get(i).getOrderId())) {
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				System.out.println("No orders ID found");
			}
		} while (!isFound);
		return orderId;

	}

	public String getItemCodeInputFromOrder() {
		List<Order> orders = controller.getAllOrders();
		String itemCode;
		boolean same = false;
		do {
			System.out.print("Enter item code          : ");
			itemCode = scanner.nextLine();
			for (int i = 0; i < orders.size(); i++) {
				if (itemCode.equals(orders.get(i).getItemCode())) {
					same = true;
					break;
				}
			}

			if (!same) {
				System.out.println("No item code found");
			}
		} while (!same);
		return itemCode;

	}
	
	public String getItemCodeInputFromMenu() {
		//[["BG01","Chicken Burger","9.00"],[],[]]
		ArrayList<String[]> menu = controller.readMenu();
		String itemCode;
		boolean same = false;
		do {
			System.out.print("Enter item code          : ");
			itemCode = scanner.nextLine().toUpperCase();
			for (int i = 0; i < menu.size(); i++) {
				if (itemCode.equals(menu.get(i)[0])) {
					same = true;
					break;
				}
			}

			if (!same) {
				System.out.println("No item code found");
			}
		} while (!same);
		return itemCode;
		
	}

	public boolean checkMemberIdInput() {
		String membership;
		boolean isValid = false;
		do {
			System.out.print("Enter membership id (Enter 0 to return) : ");
			membership = scanner.nextLine();
			if (membership.equals("0")) {
				break;
			}
			isValid = controller.checkUserMembership(membership);

			if (!isValid) {
				System.out.println("No membership code found");
			}
		} while (!isValid);
		return isValid;
	}
}
