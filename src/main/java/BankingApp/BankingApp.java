package BankingApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import UserAccess.BankAdmin;
import UserAccess.Customer;
import UserAccess.CustomerList;
import UserAccess.Employee;

public class BankingApp {

	// Locally the BankAdmin and the Employee Logins will be stored, with
	// the password being the same for both
	static String Ausername = "auser";
	static String Eusername = "euser";
	static String Epassword = "password";	
	
	public static void main(String[] args) {
		ArrayList<Customer> cList = new ArrayList<Customer>();
		Customer c4 = new Customer("NewUser","password","New",1999);
		Customer c5 = new Customer("Newish","password","NEWP",1939);
		cList.add(c4);
		cList.add(c5);
		for(Customer c: cList) {
			System.out.println(c);
		}
		/*
		 *  
		 *  
		 *  Storing Customer's info through File Write and Read Methods
			*/	 

		
		Scanner in = new Scanner(System.in);
		boolean quit = false;
		System.out.println("Welcome to the Banking App");
		
		do {
			System.out.println("Before logging in, are you:");
			System.out.println("(1)Customer \n(2)Employee \n(3)Administrator \n(0) To Exit Application");
			int decision = in.nextInt();
			
			
			switch(decision) {
			case 0: // Exit Option
				quit = true;
				break;
			case 1: // Customer Option
				System.out.println("Customer Login");
				customerLogin(in,cList);
				break;
			case 2: // Employee Option
				employeeLogin(in,cList);
				break;
			case 3: // Admin option
				adminLogin(in,cList);
				break;
			default:
				System.out.println("Please choose Customer or Employee or exit app.");
				break;
			}
		} while(!quit);
			System.out.println("Closing Application");
			System.out.println("Bye!");
			in.close();
			//saveData(cList);
	}
			
	
	public static void customerLogin(Scanner in, ArrayList<Customer> cList) {
		System.out.println("Please Enter your Username");
		String user = in.next();
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).getUsername1().contains(user)) {
				System.out.println("Enter your password");
				String password = in.next();
				if(cList.get(i).getPassword().contains(password)) {
					customerScreen(in,cList.get(i), cList);
					break;
					
				} else {
					System.out.println("Incorrect Password");
					break;
				}
			} else {
				System.out.println("Not in Username");
				break;

			}
		}
	
	}
	
	public static void customerScreen(Scanner in, Customer c, ArrayList<Customer> cList) {
		System.out.println("Welcome to the Customer Screen");
		System.out.println("Here is your information");
		System.out.println(c.toString());
		boolean quit = false;
		do{
			System.out.println("What would you like to do today?");
			
			System.out.println("(1) Deposit\n(2) Withdraw\n(3) Transfer\n(4) Apply for a New Account\n(0) To Log Out");
			int decision = in.nextInt();
			switch(decision) {
			case 0: 
				System.out.println("Exiting");
				quit = true;
				break;
			case 1:
				System.out.println("DEPOSIT SCREEN");
				System.out.println("How much would you like to deposit?");
				float deposit = in.nextFloat();
				c.deposit(deposit);
				break;
			case 2:
				System.out.println("WITHDRAW SCREEN");
				System.out.println("How much would you like the withdraw?");
				float withdraw = in.nextFloat();
				c.withdraw(withdraw);
				break;
			case 3:
				System.out.println("TRANSFER SCREEN");
				System.out.println("To which user would you like to transfer funds?");
				System.out.println("Enter Username of that other Customer");
				String tUser = (String) in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().contains(tUser)) {
						System.out.println("How much funds?");
						float transfer = in.nextFloat();
						c.transfer(cList.get(i), transfer);						
					} else {
						System.out.println("Could not find username");
						break;
					}
					break;
				}
			case 4 : // Apply for a new Account;
				System.out.println("NEW ACCOUNT APPLICATION");
				c.addAccount(in);
				break;
			default:
				System.out.println("Please Select one of the Options Please");
			}
		} while(quit == false);
	}

	public static void employeeLogin(Scanner in ,ArrayList<Customer> cList) {
		System.out.println("***Employee Login***");
		System.out.println("Please Enter your Username");
		String employee = in.next();
		if(employee.equals(Eusername)) {
			System.out.println("Enter your password");
			String password = in.next();
			if(password.equals(Epassword)) {
				employeeScreen(in,cList);
				
			} else {
				System.out.println("Incorrect password");			
			}
		} else {
			System.out.println("Not in Username");
		}
	}
	
	public static void employeeScreen(Scanner in, ArrayList<Customer> cList) {
		System.out.println("Welcome to the Employee Screen");
		Employee e = new Employee();
		boolean quit = false;
		do{
			System.out.println("What would you like to do today?");
			
			System.out.println("(1) View a Customer's Account \n(2) Approve or Deny a Customer's Account \n(0) To Log Out");
			int decision = in.nextInt();
			switch(decision) {
			case 0: 
				System.out.println("Exiting");
				quit = true;
				break;
			case 1:
				System.out.println("Which account would you like to view?");
				System.out.println("Enter Username");
				String user1 = (String) in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().contains(user1)) {
						System.out.println(cList.get(i).toString());
						break;
					} else {
						System.out.println("User not found");
						break;
					}
				}
				break;
			case 2:
				System.out.println("Which pending account would you like to view?");
				System.out.println("Username");
				String user2 = (String) in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().contains(user2)) {
						if(cList.get(i).pendingAccounts() > 0) {
							e.Decision(cList.get(i), in);
							break;
						} else {
							System.out.println("No Pending Accounts");
							break;
						}
					}
				}
				break;
			default:
				System.out.println("Please Select one of the Options");
				break;
			}
		} while(!quit);
	}

	public static void adminLogin(Scanner in, ArrayList<Customer> cList) {
		System.out.println("***Admin Login***");
		System.out.println("Please Enter your Username");
		String admin = in.next();
		if(admin.equals(Ausername)) {
			System.out.println("Enter your password");
			String password = in.next();
			if(password.equals(Epassword)) {
				adminScreen(in,cList);
			} else {
				System.out.println("Incorrect password");
			}
		} else {
			System.out.println("Not in Username");
		}
	}
	
	public static void adminScreen(Scanner in, ArrayList<Customer> cList) {
		BankAdmin a = new BankAdmin();
		System.out.println("Welcome to the Admin Screen");
		boolean quit = false;
		do{
			System.out.println("What would you like to do today?");
			
			System.out.println("(1)View a Customer's Account \n(2)Edit a Customer's Account \n"
					+ "(3)Cancel a Customer's Account "
					+ "\n(4)Approve or Deny a Customer's Account \n(0) To Log Out");
			int decision = in.nextInt();
			switch(decision) {
			case 0: 
				System.out.println("Exiting");
				quit = true;
				break;
			case 1:
				System.out.println("Which account would you like to view?");
				System.out.println("Enter Username");
				String user1 = (String) in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().equals(user1)) {
						System.out.println(cList.get(i).toString());
						break;
					} else {
						System.out.println("User not found");
					}
				}
				break;
				
			case 2:
				System.out.println("Which account would you like to edit?");
				System.out.println("Username");
				String editUser = (String) in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().equals(editUser)) {
						customerScreen(in,cList.get(i),cList);
					}
				}
				break;
			case 3:
				System.out.println("Which account would you like to cancel?");
				System.out.println("Username");
				String user = in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().equals(user)) {
						a.cancel(cList, cList.get(i));
					}
				}
				System.out.println("Cancelling Account");
				break;
			case 4:
				System.out.println("Which pending account would you like to view?");
				System.out.println("Username");
				String user2 = (String) in.next();
				for(int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getName1().equals(user2)) {
						if(cList.get(i).pendingAccounts() > 0) {
							a.Decision(cList.get(i), in);
						} else {
							System.out.println("No Pending Accounts");
							break;
						}
					}
				}
				break;
			default:
				System.out.println("Please Select One of the Options");
			}
		} while(!quit);
	}
	
	public static void saveData(ArrayList<Customer> cList) {	

		try {
			FileOutputStream fileOut = new FileOutputStream("CustomerData.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(cList);
			out.close();
			fileOut.close();
			System.out.println("Serialized data");
			
	
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadData() {
		ArrayList<Customer> deserialized = new ArrayList<Customer>();
		try {
			FileInputStream fileIn = new FileInputStream("CustomerData.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserialized = (ArrayList<Customer>) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
