package UserAccess;

import java.io.Serializable;
import java.util.Scanner;

public class Customer implements Serializable {
	
	/*
	 */
	private  String name1;
	private String password;
	private String name2;
	public String username1;
	private String username2;
	public float balance;
	protected int checkAccounts;
	protected float[] Accounts = new float[2];
	
	// When customer does not give any information
	public Customer() {
		name1 = "User";
		username1 = "user";
		password = "password";
		balance = 0;
		
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
	
	public void addAccount(Scanner in) {
		System.out.println("How much funds do you want to add to this account?");
		float newAccount = in.nextFloat();
		System.out.println("Thank you, pending approval from Bank");
		checkAccounts++;
		Accounts[0] = balance;
		Accounts[1] = newAccount;
 		
	}
	
	public float getBalance() {
		return balance;
	}
	
	public String getUsername1() {
		return username1;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName1() {
		return name1;
	}

	
	protected void setBalance(float balance) {
		this.balance = balance;
	}
	
	// Returns the value of the deposit to the associated account
	public float deposit(float deposit) {
		if(deposit < 0) {
			System.out.println("Cannot withdraw, in deposit mode");
			return balance;
		}
		float newBalance = balance + deposit;
		setBalance(newBalance);
		return balance;
	}
	
	public float withdraw(float withdraw) {
		if(withdraw > balance) {
			System.out.println("Cannot complete. Overwithdrawn");
			return balance;
		}
		float newBalance = balance - withdraw;
		setBalance(newBalance);
		return balance;
	}
	
	
	public float transfer(Customer cTransferTo, float funds) {
		if(funds > balance) {
			System.out.println("Cannot transfer more than balance.");
			return balance;
		}
		
		withdraw(funds);
		System.out.println("Funds transfered to " + cTransferTo.name1);
		return cTransferTo.deposit(funds);
	}
	
	// Pending Accounts
	public int pendingAccounts() {
		return checkAccounts;
	}

	@Override
	public String toString() {
		if(username2 != null) {
			return name1 + " \nUsername: " + username1 + "\n" + name2 + " \nUsername: "
					+ username2 + "\nBalance: $" + balance;
		}
		return name1 + " \nUsername: " + username1 + "\nBalance: $" + balance;
	}

	
	

}
