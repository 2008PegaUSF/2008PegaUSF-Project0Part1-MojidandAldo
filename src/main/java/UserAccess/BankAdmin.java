package UserAccess;

import java.util.ArrayList;
import java.util.Scanner;

public class BankAdmin {

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
		c += "Projected Balance in Account: " + customer.Accounts[1] + "\n";
		

		boolean quit = false;
		
		do {
			System.out.println("(1)Approve \n (2)Deny Account? \n (0) To Exit");
			int decision = in.nextInt();
			
			
			switch(decision) {
			case 1:
				System.out.println("Account Approved!");
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
	
	
	public void addAccount(Customer c, Scanner in) {
		System.out.println("How much funds do you want to add to this account?");
		float newAccount = in.nextFloat();
		System.out.println("Thank you, pending approval from Bank");
		c.checkAccounts = 1;
		c.Accounts[0] = c.getBalance();
		c.Accounts[1] = newAccount;
 		
	}
	
	// Calls Customer and Withdraw amount to be able to know what to withdraw
	// Similar to the Customer method, but since it's BankAdmin looks for specific Customer.
	public float withdraw(Customer c, float withdraw) {
		if(withdraw > c.getBalance()) {
			System.out.println("Cannot complete. Overwithdrawn");
			return c.getBalance();
		}
		float newBalance = c.getBalance() - withdraw;
		c.setBalance(newBalance);
		return c.getBalance();
	}
	
	// Similar to Withdraw, Deposit parameters follow the same 
	public float deposit(Customer c, float deposit) {
		if(deposit < 0) {
			System.out.println("Cannot withdraw, in deposit mode");
			return c.getBalance();
		}
		float newBalance = c.getBalance() + deposit;
		c.setBalance(newBalance);
		return c.getBalance();
	}
	
	// Missing Transfer Method
	public float transfer(Customer c,Customer cTransferTo, float funds) {
		if(funds > c.getBalance()) {
			System.out.println("Cannot transfer more than balance.");
			return c.getBalance();
		}
		
		c.withdraw(funds);
		System.out.println("Funds transfered to " + cTransferTo.getName1());
		return cTransferTo.deposit(funds);
	}
	
	
	// Cancels account, should be set up to remove the object(Customer) from the ArrayList
	public void cancel(ArrayList<Customer> cList, Customer customer) {
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).equals(customer)) {
				cList.remove(i);
			}
		}
	}
		
}
