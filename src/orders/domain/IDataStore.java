package orders.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IDataStore {
	//Create user
	public boolean checkUserName(String username);
	public boolean verifyLoign(String username,String password);
	public void createUser(String userName,String password);
	//Check Order
	public ArrayList<String[]>  openOrderFile();
	public void addOrderToList();
	public int getNumberOfOrders();
	public List<Order> getAllOrders();
	public List<String> getOrderIdList();
	//View Menu
	public ArrayList<String[]> readMenu();
	public void printMenu();
	public void printKeywordMenu(String keywords);
	//Create Order
	public void createOrder();
	public void createTempOrder(String itemCode,int quantity,String remark);
	//View order
	public void printOrder(String orderId);
	public void updateOrder(String orderId, String itemCode, int qtt, String remarks);
	public void deleteOrder(String orderId, String itemCode) ;
	public void addOrder(String orderId, String itemCode,int qtt, String remarks);
	//View invoice
	public ArrayList<ArrayList<String>> getOrderListWithByOrderNum(String orderNum) ;
	public double calculateTotalOrderPrice(ArrayList<ArrayList<String>> orderList);
	public boolean checkUserMembership(String membership);
	public double getDiscountPrice(double total);
	public double computeDiscountedTotal(double total, double discount);
}
