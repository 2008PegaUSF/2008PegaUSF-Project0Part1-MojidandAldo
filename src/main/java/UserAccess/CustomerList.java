package UserAccess;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

	List<Customer> cList = new ArrayList<Customer>();
	
	public List<Customer> getCustList() {
		
		
		return cList;
	}
	
	public List<Customer> loadList() {
		return cList;
	}
	
	public Customer getCustomer(List<Customer> cList) {
		Customer c = new Customer();
		//while(cList.next()) {
			// goes through list to find the Customer
		//}
		
		return c;
	}
}
