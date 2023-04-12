package orders.domain;

import java.util.List;

public interface IDataStore {
	public int getNumberOfOrders();
	public List<Order> getAllOrders();
	public List<String> getOrderIdList();
	public void openOrderFile();
}
