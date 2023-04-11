package orders.domain;





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
	
	
	
	//CREATE ORDER
	
	
	
	//VIEW ORDER
	
	
	
	//VIEW INVOICE
	public void viewinvoice() {
		viewInvoice.operation();
	}
		
	//CHECK ORDER
	public void checkOrder() {
		
	}
	
	
	
}
