package orders.domain;

import java.io.*; 

import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class ViewInvoice
{
	private String orderNum;
	private String membership;
	private double total = 0;
	private double discountedTotal = 0;
	private double discount =0;
	private ArrayList<ArrayList<String>> itemsList = new ArrayList();

    public void operation()
    {
       
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter order number:");
		orderNum = scanner.nextLine();
		
		System.out.print("Are you a member? (Y/N):");
		membership = scanner.nextLine();
		
		System.out.println();

//		Read From file
		try 
		{
			File myObj = new File("textfile.txt");
			Scanner myReader = new Scanner(myObj);
			// asumption : data format "123	BG03	Beef Burger 	1	-	10.00	10.00"
			while (myReader.hasNextLine()) 
			{	
				String data = myReader.nextLine();
				String[] Input = data.split("\t");
				ArrayList<String> datalist = new ArrayList<>();
				if (Input.length > 0 && Input[0].equals("123")) 
				{
					// Add remaining data to ArrayList
					for (String input:Input )
					{
						datalist.add(input);
					}
				}
				itemsList.add(datalist);
			}

				myReader.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}

        sumValue();
        checkMembership();
        
        discountedTotal = total - discount;
        
        System.out.println("Item Code\tName\t\t\tQtt\t\tRemarks\t\tPrice(RM)\tTotal Price(RM)");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		for (ArrayList<String> items : itemsList) 
		{
			for(int i=1 ; i<items.size(); i++)
			{
				 System.out.print(items.get(i)+"\t\t");
			}
			System.out.println();
		}


		System.out.println();
		System.out.println(String.format("Subtotal(RM)\t :%.2f",total));
		System.out.println(String.format("Member discount\t :%.2f",discount));
		System.out.println(String.format("Total(RM)\t :%.2f",discountedTotal));
		System.out.println(String.format("Rounded total(RM):%.2f",Math.ceil(discountedTotal/0.10)*0.10));
        // assumption : 10 % offer will be given to member
    }

    public void sumValue()
    {
        for (ArrayList<String> items : itemsList)
		{
			total += Double.parseDouble(items.get(6));
		}
    }
    public void checkMembership()
    {
        	if (membership.equals("y") || membership .equals("Y"));
			discount = total * 0.1;
    }

}