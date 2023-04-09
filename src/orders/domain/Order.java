package orders.domain;

import java.util.List;

public class Order {

	private int orderNo;
	private Customer customer;
	
	public Order(Customer customer) {
		this.customer= customer;
	}
	public int getOrderNo() {
		return orderNo;
	}
}
