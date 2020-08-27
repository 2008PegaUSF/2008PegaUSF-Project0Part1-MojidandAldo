package UserAccess;

public class BankAdmin extends Customer {

	static String username;
	
	public BankAdmin(String username) {
		this.username = username;
	}
	
	// Calls Customer and Withdraw amount to be able to know what to withdraw
	// Similar to the Customer method, but since it's BankAdmin looks for specific Customer.
	public float withdraw(Customer customer, float withdraw) {
		if(withdraw > customer.balance ) {
			return -1;
		}
		customer.balance -= withdraw;
		
		return customer.balance;
		
	}
	
	// Similar to Withdraw, Deposit parameters follow the same 
	public float deposit(Customer customer, float deposit) {
		if(deposit > 0) {
			customer.balance += deposit;
		} else if (deposit < 0){ // if deposit is negative, returns the value of the original balance
			System.out.println("Cannot deposit negative values");
			return customer.balance;
		}
		return customer.balance;
	}
	
	// Missing Transfer Method
	
	// Cancels account, should be set up to remove the object(Customer) from the ArrayList
	public void cancel(Customer customer) {
		
	}
	
	// Creating a new Account; Should the method be here or in Bank App?
	
}
