import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import UserAccess.Customer;

public class Project0P1Tests {

	// Calls deposit function with regular positive amount
	@Test
	public void testDeposit() {
		Customer c = new Customer();
		Assertions.assertEquals(20,c.deposit(20));
	}
	
	// Calls deposit function when there is a negative value to deposit
	@Test 
	public void testNegDeposit() {
		Customer c = new Customer();
		Assertions.assertEquals(0,c.deposit(-20));
	}
	
	// Calls withdraw function
	@Test
	public void testWithdraw() {
		Customer c = new Customer("aldocaballero","password", "Aldo",1200);
		Assertions.assertEquals(1000, c.withdraw(200));
	}
	
	// Calls withdraw function, tests overwithdraw case
	// Should return the original balance value.
	@Test
	public void testOverWithdraw() {
		Customer c = new Customer("aldocaballero","password", "Aldo",1200);
		Assertions.assertEquals(1200, c.withdraw(1300));
	}
	
}
