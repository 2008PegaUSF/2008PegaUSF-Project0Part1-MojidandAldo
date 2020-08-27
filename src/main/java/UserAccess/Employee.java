package UserAccess;

import java.util.Scanner;

public class Employee {
	
	// If User is already a customer, the employee pulls up their information
	public String getCustomerInfo(Customer customer) {
		String c = null;
		try {
		c += "Customer's name: " + customer.getUsername1() + " \n";
		c += "Balance: " + customer.getBalance();
		return c;
		} catch(Exception e) {
			c += "This user does not have an account here.";
			return c;
		}
	}
	
	// If the user is not a customer, Employee calls up their newly created username from Customer
	// And Approves or Denies
	// Returning true will be approving the account, and false will be denying it
	public boolean Decision(Customer customer, Scanner in) {
		String c = null;
		c += "Customer's name: " + customer.getName1() + "\n";
		c += "Projected Balance in Account: " + customer.getBalance() + "\n";
		

		boolean quit = false;
		
		do {
			System.out.println("(1)Approve \n (2)Deny Account? \n (0) To Exit");
			int decision = in.nextInt();
			
			
			switch(decision) {
			case 1:
				System.out.println("Account Approved!");
				// Move Customer into ArrayList of actual Customers
				return true;
			case 2: 
				System.out.println("Account Denied.");
				return false;
				
			default:
				System.out.println("Please select to approve or deny the customer");
			}
		} while (!quit);
		return false;
	}
	
}
