package orders.domain;


import java.util.ArrayList;
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
	
	public boolean checkUserName(String username)
	{
		return dataLists.checkUserName(username);
	}
	public boolean verifyLoign(String username,String password)
	{
		return dataLists.verifyLoign(username, password);
	}
	public void createUser(String userName,String password)
	{
		dataLists.createUser(userName, password);
	}
	
	
	//VIEW MENU
	public void printMenu()
	{
		dataLists.printMenu();
	}
	
	public void printKeywordMenu(String keywords) {
		dataLists.printKeywordMenu(keywords);
	}

	
	//CREATE ORDER
	public void createTempOrder(String itemCode,int quantity,String remark)
	{
		dataLists.createTempOrder(itemCode,quantity,remark);
	}
	
	public void createOrder()
	{
		dataLists.createOrder();
	}
	
	
	//VIEW ORDER
	
	public void addOrderToList() {
		dataLists.addOrderToList();
	}
	public void printOrder(String orderId) {
		dataLists.printOrder(orderId);
	}
	
	public void updateOrder(String orderId, String itemCode, int qtt, String remarks) {
		dataLists.updateOrder(orderId, itemCode, qtt, remarks);
	}
	
	public void deleteOrder(String orderId,String itemCode)	{
		dataLists.deleteOrder(orderId, itemCode);
	}
	
	public void addOrder(String orderId, String itemCode,int qtt, String remarks) {
		dataLists.addOrder(orderId, itemCode, qtt, remarks);
	}
	
	//VIEW INVOICE
	public ArrayList<ArrayList<String>> getOrderListWithByOrderNum(String orderNum) {
		return dataLists.getOrderListWithByOrderNum(orderNum);
	}
	public double calculateTotalOrderPrice(ArrayList<ArrayList<String>> orderList) {
		return dataLists.calculateTotalOrderPrice(orderList);
	}
	public boolean checkUserMembership(String membership) {
		return dataLists.checkUserMembership(membership);
	}
	public double getDiscountPrice(double total) {
		return dataLists.getDiscountPrice(total);
	}
	public double computeDiscountedTotal(double total, double discount) {
		return dataLists.computeDiscountedTotal(total, discount);
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
