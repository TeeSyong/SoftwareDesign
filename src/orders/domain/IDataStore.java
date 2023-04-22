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
}
