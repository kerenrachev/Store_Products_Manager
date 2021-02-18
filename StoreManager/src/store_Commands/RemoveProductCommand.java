package store_Commands;

import Model.Store;
import interfaces.Command;

public class RemoveProductCommand implements Command<Integer> {
	
	private Store store;
	private String productID;
	
	public RemoveProductCommand(Store store , String productID) {
		this.store = store;
		this.productID = productID;
	}

	@Override
	public Integer execute() {
		return store.removeProduct(productID);
	}

}
