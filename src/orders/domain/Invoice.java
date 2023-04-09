package orders.domain;

import java.util.List;

public class Invoice {
	private int invoiceNo;
	private String invoiceDate;
	private double totalAmount;
	
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public int getInvoiceNo() {
		return invoiceNo;
	}
	
	public String invoiceDate() {
		return invoiceDate;
	}

}
