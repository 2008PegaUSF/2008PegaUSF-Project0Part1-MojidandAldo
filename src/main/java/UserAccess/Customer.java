package UserAccess;

import java.io.Serializable;

public class Customer implements Serializable {
	
	/*
	 */
	private static String name1;
	private static String password;
	private static String name2;
	private static String username1;
	private static String username2;
	private static float balance;
	
	// When customer does not give any information
	public Customer() {
		name1 = "User";
		username1 = "user";
		password = "password";
		
	}
	
	// When the customer opens one account with themselves
	public Customer(String Username,String password, String name, float balance) {
		this.username1 = Username;
		this.password = password;
		this.name1 = name;
		this.balance = balance;
	}
	
	// When the customer opens an account with another person (Joint Account)
	public Customer(String Username1,String password, String name1, String Username2, String name2, float balance) {
		this.username1 = Username1;
		this.username2 = Username2;
		this.password = password;
		this.name1 = name1;
		this.name2 = name2;
		this.balance = balance;
	}
	
	//Getters and Setters for Names, Usernames, and Balance
	public static String getName1() {
		return name1;
	}

	public static String getName2() {
		return name2;
	}


	public static String getUsername1() {
		return username1;
	}

	public static String getUsername2() {
		return username2;
	}
	
	public static String getPassword() {
		return password;
	}

	public static float getBalance() {
		return balance;
	}
	
	private void setBalance(float balance) {
		this.balance = balance;
	}
	
	// Returns the value of the deposit to the associated account
	public float deposit(float deposit) {
		if(deposit < 0) {
			System.out.println("Cannot withdraw, in deposit mode");
			return getBalance();
		}
		float newBalance = getBalance() + deposit;
		setBalance(newBalance);
		return getBalance();
	}
	
	public float withdraw(float withdraw) {
		if(withdraw > getBalance()) {
			System.out.println("Cannot complete. Overwithdrawn");
			return getBalance();
		}
		float newBalance = getBalance() - withdraw;
		setBalance(newBalance);
		return getBalance();
	}
	
	
	public float transfer(Customer cTransferTo, float funds) {
		if(funds > getBalance()) {
			System.out.println("Cannot transfer more than balance.");
			return getBalance();
		}
		
		withdraw(funds);
		System.out.println("Funds transfered to " + cTransferTo.getName1());
		return cTransferTo.deposit(funds);
	}

	@Override
	public String toString() {
		if(username2 != null) {
			return name1 + " \nUsername: " + username1 + "\n" + name2 + " \nUsername: "
					+ username2 + "Balance: $" + balance;
		}
		return name1 + " \nUsername: " + username1 + "Balance: $" + balance;
	}
	
	

}
