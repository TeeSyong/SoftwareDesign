package orders.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IDataStore {
	public boolean checkUserName(String username);
	public boolean verifyLoign(String username,String password);
	public void createUser(String userName,String password);
	public int getNumberOfOrders();
	public List<Order> getAllOrders();
	public List<String> getOrderIdList();
	public void openOrderFile();
	public void printMenu();
	public void printKeywordMenu(String keywords);
	public ArrayList<String[]>  openOrderFileV2();
	public void createOrder();
	public void cancelOrder();
	public void createTempOrder(String itemCode,int quantity,String remark);
	public void addOrderToList();
	public void printOrder(String orderId);
	public void updateOrder(String orderId, String itemCode, int qtt, String remarks);
	public void deleteOrder(String orderId, String itemCode) ;
	public void addOrder(String orderId, String itemCode,int qtt, String remarks);
	
}
