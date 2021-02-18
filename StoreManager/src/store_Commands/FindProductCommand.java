package store_Commands;

import Model.Product;
import Model.Store;
import interfaces.Command;

public class FindProductCommand implements Command<Product> {

	private Store store;
	private String productID;
	
	public FindProductCommand(Store store , String productID) {
		// TODO Auto-generated constructor stub
		this.store = store;
		this.productID = productID;
	}
	
	
	@Override
	public Product execute() {
		return store.findProduct(productID);
	}

}
