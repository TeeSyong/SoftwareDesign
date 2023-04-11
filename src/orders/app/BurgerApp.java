package orders.app;

import orders.domain.*;

public class BurgerApp {
	public static void main(String[] args) {
		IDataStore dataLists = new DataLists();
		Controller controller = new Controller();
		controller.setDataLists(dataLists);
		ConsoleUI userInterface = new ConsoleUI();
		userInterface.setController(controller);
		userInterface.start();
	}
}