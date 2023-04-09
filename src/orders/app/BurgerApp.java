package orders.app;

import orders.domain.Controller;

public class BurgerApp {
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		ConsoleUI userInterface = new ConsoleUI();
		userInterface.setController(controller);
		userInterface.start();
	}
}
