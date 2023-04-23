package orders.app;

import java.io.IOException;

import orders.domain.Customer;

public class Try {
	public static void main(String [] args) throws IOException
	{
		Customer cs = new Customer ();
		 cs.checkUserName("Lyan");
		 cs.verifyLoign("abc", "12345");
		 cs.createUser("hello", "meow2");
	}
}
