package orders.domain;

import java.util.List;

public class Order {

	private int quantity;
	private String foodName,orderId,itemCode,remark;
	private Customer customer;
	private Item item;
	
	
	public Order(Customer customer) {
		this.customer= customer;
	}
	
	
	public Order(String orderId, String itemCode, String foodName, int quantity, String remark ) {
		this.orderId = orderId;
		this.itemCode = itemCode;
		this.foodName = foodName;
		this.quantity = quantity;
		this.remark = remark;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	
	public String getFoodName() {
		return foodName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
