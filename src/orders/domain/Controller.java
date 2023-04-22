package orders.domain;


import java.util.List;


public class Controller {
	private ViewInvoice viewInvoice = new ViewInvoice();
	private IDataStore dataLists;

	public Controller() {
		this.dataLists = null;
	}
	
	public void setDataLists(IDataStore dataLists)
	{
		this.dataLists = dataLists;
	}
	
	//THIS CLASS IS FOR CONTROLLER PURPOSE NOT WHOLE FUNCTION
	
	//CREATE USER PROFILE 
	
	
	
	
	//VIEW MENU
	public void printMenu()
	{
		dataLists.printMenu();
	}
	
	public void printKeywordMenu(String keywords) {
		dataLists.printKeywordMenu(keywords);
	}

	
	//CREATE ORDER
	
	
	
	//VIEW ORDER
	
	
	
	//VIEW INVOICE
	public void viewinvoice() {
		viewInvoice.operation();
	}
		
	//CHECK ORDER
	public void checkOrder() {
		
	}
	
	public void openOrderFile() {
		dataLists.openOrderFile();
	}
	
	public int getNumberOfOrders() {
		return dataLists.getNumberOfOrders();
	}

	public List<Order> getAllOrders(){
		return dataLists.getAllOrders();
	}
	
	public List<String> getOrderIdList(){
		return dataLists.getOrderIdList();
	}
}
