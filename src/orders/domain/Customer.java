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

	//function used in create user profile to check whether the username exists or not 
	public void checkUserName(){

	}

	//function used in create user profile to verify the username and password input by the user
	public void verifyLoign()
	{

	}

	//function used in create user profile to create the user which write into txt file
	public void createUser(String userName,String password)
	{

	}

}
