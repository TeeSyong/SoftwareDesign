package orders.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IDataStore {
	public int getNumberOfOrders();
	public List<Order> getAllOrders();
	public List<String> getOrderIdList();
	public void openOrderFile();
	public void printMenu();
	public void printKeywordMenu(String keywords);
	public void printOrder(String orderId);
	public void getOrderUpdateIdx(String orderId, String itemCode, int qtt, String remarks);
	public ArrayList<String[]>  openOrderFileV2();
	public void addOrderToList();
}
