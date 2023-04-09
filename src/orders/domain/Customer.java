package orders.domain;

public class Customer {
	private String address;
	private int userId;
	private String password;
	private String registryDate;
	
	public int getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRegistryDate() {
		return registryDate;
	}

}
